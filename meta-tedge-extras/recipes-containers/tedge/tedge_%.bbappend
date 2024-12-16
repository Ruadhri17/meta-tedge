# Add a dedicate mosquitto mqtt listener to allow communication from containers
TEDGE_CONTAINER_MQTT_LISTENER_IP ?= "172.17.0.1"
TEDGE_CONTAINER_MQTT_LISTENER_PORT ?= "1884"

RDEPENDS:${PN} += " tedge-bootstrap"

do_install:append () {
    # Add dedicated listener for the docker traffic
    if [ -n "${TEDGE_CONTAINER_MQTT_LISTENER_IP}" ] && [ -n "${TEDGE_CONTAINER_MQTT_LISTENER_PORT}" ]; then

        # Note:
        # docker is not a socket activated service, meaning that it will
        # not be started when the device starts. This causes a problem
        # with the default mosquitto listener which is enabled specifically
        # for the docker network (gateway) to enable access to the tedge MQTT
        # broker from containers. As mosquitto starts, it fails to bind to
        # the container gateway network as it does not yet exist, thus leading
        # to continuous restarting of the mosquitto service, which makes it
        # impossible to onboard the device.
        #
        if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
            install -d "${D}${datadir}/tedge-bootstrap/scripts.d"
            cat <<EOT > "${D}${datadir}/tedge-bootstrap/scripts.d/90_docker-init"
#!/bin/sh
set -e
systemctl start docker.service --no-block || echo "Warning: Failed to start docker, continuing anyway" >&2
EOT

            chmod 0755 "${D}${datadir}/tedge-bootstrap/scripts.d/90_docker-init"
        fi

        cat <<EOT > ${D}${TEDGE_CONFIG_DIR}/mosquitto-conf/tedge-networkcontainer.conf
listener ${TEDGE_CONTAINER_MQTT_LISTENER_PORT} ${TEDGE_CONTAINER_MQTT_LISTENER_IP}
allow_anonymous true
require_certificate false
EOT
    fi
}

FILES:${PN} += " \
    ${TEDGE_CONFIG_DIR}/mosquitto-conf/tedge-networkcontainer.conf \
    ${datadir}/tedge-bootstrap/scripts.d/90_docker-init \
"
