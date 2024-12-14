
do_install:append () {
    # Create symlinks to enable tedge sm-plugin interface
    install -d ${D}${TEDGE_CONFIG_DIR}/sm-plugins
    ln --relative --symbolic ${D}${bindir}/tedge-nodered-plugin ${D}${TEDGE_CONFIG_DIR}/sm-plugins/nodered-flows
    ln --relative --symbolic ${D}${bindir}/tedge-nodered-plugin ${D}${TEDGE_CONFIG_DIR}/sm-plugins/nodered-project
}

FILES:${PN} += "\
    ${TEDGE_CONFIG_DIR}/sm-plugins/nodered-flows \
    ${TEDGE_CONFIG_DIR}/sm-plugins/nodered-project \
"
