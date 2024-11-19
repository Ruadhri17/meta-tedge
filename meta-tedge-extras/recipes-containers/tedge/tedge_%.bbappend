# Add a dedicate mosquitto mqtt listener to allow communication from containers
TEDGE_CONTAINER_MQTT_LISTENER_IP ?= "172.17.0.1"
TEDGE_CONTAINER_MQTT_LISTENER_PORT ?= "1884"

do_install:append () {
    # Add dedicated listener for the docker traffic
    if [ -n "${TEDGE_CONTAINER_MQTT_LISTENER_IP}" ] && [ -n "${TEDGE_CONTAINER_MQTT_LISTENER_PORT}" ]; then
        cat <<EOT > ${D}${TEDGE_CONFIG_DIR}/mosquitto-conf/tedge-networkcontainer.conf
listener ${TEDGE_CONTAINER_MQTT_LISTENER_PORT} ${TEDGE_CONTAINER_MQTT_LISTENER_IP}
allow_anonymous true
require_certificate false
EOT
    fi
}

FILES:${PN} += " \
    ${TEDGE_CONFIG_DIR}/mosquitto-conf/tedge-networkcontainer.conf \
"
