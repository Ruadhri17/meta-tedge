inherit ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}
inherit ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'update-rc.d', '', d)}

SYSTEMD_AUTO_ENABLE ?= "enable"
SYSTEMD_SERVICE:${PN} = "tedge-container-plugin.service"

do_install:append () {
    # Service files
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}${systemd_system_unitdir}
        install -m 0755 ${S}/src/${GO_IMPORT}/packaging/services/systemd/tedge-container-plugin.service ${D}${systemd_system_unitdir}
        
	elif ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${S}/src/${GO_IMPORT}/packaging/services/init.d/service.init -T ${D}${sysconfdir}/init.d/tedge-container-plugin
    fi

    # configuration
    install -m 0755 -d ${D}${sysconfdir}/tedge-container-plugin
    install -m 0755 ${S}/src/${GO_IMPORT}/packaging/config.toml ${D}${sysconfdir}/tedge-container-plugin/

    # Rename the binary
    mv ${D}${bindir}/tedge-container-plugin ${D}${bindir}/tedge-container

    # Create symlinks to enable tedge sm-plugin interface
    install -d ${D}${TEDGE_CONFIG_DIR}/sm-plugins
    ln --relative --symbolic ${D}${bindir}/tedge-container ${D}${TEDGE_CONFIG_DIR}/sm-plugins/container
    ln --relative --symbolic ${D}${bindir}/tedge-container ${D}${TEDGE_CONFIG_DIR}/sm-plugins/container-group
}

FILES:${PN} += "\
    ${systemd_system_unitdir}/* \
    ${sysconfdir}/init.d/* \
    ${sysconfdir}/init.d/* \
    ${sysconfdir}/tedge-container-plugin \
    ${sysconfdir}/tedge-container-plugin/config.toml \
    ${TEDGE_CONFIG_DIR}/sm-plugins/container \
    ${TEDGE_CONFIG_DIR}/sm-plugins/container-group \
"
