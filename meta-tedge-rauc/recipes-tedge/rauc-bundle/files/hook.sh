#!/bin/sh

LOG_FILE=/data/rauc-rootfs-hook.log

progress() {
    echo "$@" >&2
    echo "$@" >> "$LOG_FILE"
}

transfer_files() {
    target="$1"

    if [ -f "$LOG_FILE" ]; then
        # keep last n-1 logs
        mv "$LOG_FILE" "${LOG_FILE}.1"
    fi

    #
    # Retain ssh server keys from the current root
    #

    progress "Mounted slot: target=$target"

    # Save core services as presets
    progress "Saving presets"

    # Note: ||: is needed as this can fail if the file does not exist (even when using rm -f!)
    # This might be a combination of a busybox thing and/or the way the volume is mounted
    rm -f "${target}/etc/systemd/system-preset/98-thin-edge.preset" ||:
    mkdir -p "${target}/etc/systemd/system-preset"
    systemctl list-unit-files | grep enabled | grep -E "^tedge|^c8y|^mosquitto" | cut -d\  -f1 | while read -r UNIT_NAME; do
        progress "Adding $UNIT_NAME to service preset"
        echo "enable $UNIT_NAME" >> "${target}/etc/systemd/system-preset/98-thin-edge.preset"
    done

    # WORKAROUND: Copy systemd symlinks so that the agent is enable by default
    # Check if this causes a problem if the service does not exist in the new image
    if [ -e /etc/systemd/system/multi-user.target.wants/tedge-agent.service ]; then
        progress "Copying thin-edge.io services symlink to enable services on startup"

        if ls /etc/systemd/system/multi-user.target.wants/tedge* >/dev/null 2>&1; then
            cp -af /etc/systemd/system/multi-user.target.wants/tedge* "${target}/etc/systemd/system/multi-user.target.wants/" || {
                progress "Failed to copy tedge* services"
                exit 1
            }
        fi

        if ls /etc/systemd/system/multi-user.target.wants/c8y* >/dev/null 2>&1; then
            cp -af /etc/systemd/system/multi-user.target.wants/c8y* "${target}/etc/systemd/system/multi-user.target.wants/" || {
                progress "Failed to copy c8y* services"
                exit 1
            }
        fi
    fi

    # sudoers
    if [ -d /etc/sudoers.d ]; then
        # Don't clobber any existing files
        progress "Copying sudoers.d config"
        cp -an /etc/sudoers.d/* "${target}/etc/sudoers.d/"
    fi


    # tedge files
    if [ -d /etc/tedge ]; then
        progress "Copying /etc/tedge"
        if [ ! -d "${target}/etc/tedge" ]; then
            mkdir -p "${target}/etc/tedge"
            chown tedge:tedge "${target}/etc/tedge"
        fi

        if ls /etc/tedge/* >/dev/null 2>&1; then
            progress "Copying /etc/tedge configuration"

            # TODO: Check if using symlinks would be a better option rather than restoring the firmware_update workflow
            # The firmware_update.toml in an image should be left as-is and not overwritten by the current copy (as this allows updating the workflow)
            RESTORE_FIRMARE_WORKFLOW=0
            if [ -f "${target}/etc/tedge/operations/firmware_update.toml" ]; then
                progress "Backing up firmware_update.toml from new image"
                cp "${target}/etc/tedge/operations/firmware_update.toml" /tmp/firmware_update.toml.orig
                RESTORE_FIRMARE_WORKFLOW=1
            fi

            cp -RfaH /etc/tedge/* "${target}/etc/tedge"

            if [ "$RESTORE_FIRMARE_WORKFLOW" = 1 ]; then
                progress "Restoring firmware_update.toml from new image"
                cp /tmp/firmware_update.toml.orig "${target}/etc/tedge/operations/firmware_update.toml"
            fi
        fi

        # data files
        VAR_TEDGE=$(tedge config get data.path)
        NEW_VAR_TEDGE="${target}${VAR_TEDGE}"

        progress "Copying $VAR_TEDGE"
        if [ ! -d "$NEW_VAR_TEDGE" ]; then
            mkdir -p "$NEW_VAR_TEDGE"
            chown tedge:tedge "$NEW_VAR_TEDGE"
        fi
        if ls "$VAR_TEDGE"/* >/dev/null 2>&1; then
            progress "Copying $VAR_TEDGE files"
            cp -Rfa "$VAR_TEDGE"/* "$NEW_VAR_TEDGE"
        fi
    fi

    # copy default tedge plugin configuration (from readonly rootfs)
    if [ -d "${target}/etc/tedge-default/plugins" ] && [ -d "/data/tedge/plugins" ]; then
        progress "Copying default plugin configurations from ${target}/etc/tedge-default/plugins to /data/tedge/plugins/ (no clobber)"
        cp -npv "${target}/etc/tedge-default/plugins/"* "/data/tedge/plugins/" ||: >> "$LOG_FILE" 2>&1
    fi

    if [ -d "${target}/etc/tedge-default/mosquitto-conf" ] && [ -d "/data/tedge/mosquitto-conf" ]; then
        progress "Copying default mosquitto configurations from ${target}/etc/tedge-default/mosquitto-conf to /data/tedge/mosquitto-conf/ (no clobber)"
        cp -npv "${target}/etc/tedge-default/mosquitto-conf/"* "/data/tedge/mosquitto-conf/" ||:
    fi

    # ssh authorized keys
    if [ -d /home/root/.ssh ]; then
        if [ -f /home/root/.ssh/authorized_keys ]; then
            if [ ! -f "${target}/home/root/.ssh/authorized_keys" ]; then
                progress "Copying authorized_keys"
                mkdir -p "${target}/home/root/.ssh"
                chmod 700 "${target}/home/root/.ssh"

                if ls "/home/root/.ssh/"* >/dev/null 2>&1; then
                    progress "Copying /home/root/.ssh/* files"
                    cp -Rfa /home/root/.ssh/* "${target}/home/root/.ssh/"
                fi
            else
                progress "Found authorized_keys in new root, skipping copy"
            fi
        else
            progress "No authorized keys in current root, skipping copy"
        fi
    fi

    # ssh configuration
    SSH_SERVER_DIR=
    if [ -d "${target}/etc/ssh" ]; then
        SSH_SERVER_DIR=etc/ssh
    elif [ -d "${target}/etc/dropbear" ]; then
        SSH_SERVER_DIR=etc/dropbear
    fi

    if [ -n "$SSH_SERVER_DIR" ]; then
        keys=$(ls -l "${target}/${SSH_SERVER_DIR}"/*key* 2>/dev/null | wc -l)

        if [ "$keys" -eq 0 ]; then
            progress "Copying ssh server key (/$SSH_SERVER_DIR)"
            cp "/${SSH_SERVER_DIR}"/*key* "${target}/${SSH_SERVER_DIR}"
        else
            progress "Found ssh keys in new root, skipping copy"
        fi
    else
        progress "Failed to find a ssh server config on newroot partition"
        exit 1
    fi

    # Backup network manager connection config
    if [ -d "${target}/etc/NetworkManager/system-connections" ]; then
        networks=$(ls -l "/etc/NetworkManager/system-connections/"* 2>/dev/null | wc -l)

        if [ "$networks" -gt 0 ]; then
            cp /etc/NetworkManager/system-connections/* "${target}/etc/NetworkManager/system-connections"
            progress "Copied /etc/NetworkManager/system-connections"
        fi
    fi

    # Backup systemd network files
    if [ -d "${target}/etc/systemd/network" ]; then
        networks=$(ls -l "/etc/systemd/network/"*.network 2>/dev/null | wc -l)

        if [ "$networks" -gt 0 ]; then
            cp /etc/systemd/network/*.network "${target}/etc/systemd/network"
            progress "Copied /etc/systemd/network"
        fi
    else
        progress "Failed to find a /etc/systemd/network on newroot partition"
        exit 1
    fi

    progress "Finished"
}

case "$1" in
    install-check)
        if [ "$RAUC_MF_COMPATIBLE" != "$RAUC_SYSTEM_COMPATIBLE" ]; then
            echo "Compatible does not match!" 1>&2
            exit 10
        fi
        ;;

    slot-post-install)
        # only rootfs needs to be handled
        test "$RAUC_SLOT_CLASS" = "rootfs" || exit 0

        if [ -z "$RAUC_SLOT_MOUNT_POINT" ]; then
            progress "RAUC_SLOT_MOUNT_POINT env variable is not set"
            exit 1
        fi

        transfer_files "$RAUC_SLOT_MOUNT_POINT"
        ;;

    *)
        exit 1
        ;;
esac

exit 0
