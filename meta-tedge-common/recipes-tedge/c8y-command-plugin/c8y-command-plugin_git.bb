SRC_URI += "git://git@github.com/thin-edge/c8y-command-plugin.git;protocol=https;branch=main"
SRCREV = "ef9a9e1d76c3a67ec56243658ce6a1b6b42799db"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=6c62433ad04bcb1d1c596bc77d409f42 \
"

inherit allarch

PV = "0.0.3+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS += " tedge"
RDEPENDS:${PN} += " tedge"

TEDGE_CONFIG_DIR ?= "/etc/tedge"

do_install () {
    install -d "${D}${bindir}"
    install -d "${D}${TEDGE_CONFIG_DIR}/operations/c8y"
    install -d "${D}${sysconfdir}/c8y-command-plugin"

    install -m 0644 "${S}/src/c8y_Command" "${D}${TEDGE_CONFIG_DIR}/operations/c8y/"
    install -m 0755 "${S}/src/c8y-command" "${D}${bindir}/"
    install -m 0755 "${S}/src/env" "${D}${sysconfdir}/c8y-command-plugin/"
}

FILES:${PN} += " \
    ${TEDGE_CONFIG_DIR}/operations/c8y/c8y_Command \
    ${bindir}/c8y-command \
    ${sysconfdir}/c8y-command-plugin/env \
"
