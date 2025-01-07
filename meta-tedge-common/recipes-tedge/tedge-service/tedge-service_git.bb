SRC_URI += "git://git@github.com/thin-edge/tedge-services.git;protocol=https;branch=main"
SRCREV= "${AUTOREV}"
S = "${WORKDIR}/git"

PV = "0.1.0+git${SRCPV}"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit allarch
inherit ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}
inherit ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'update-rc.d', '', d)}

TEDGE_CONFIG_DIR ?= "/etc/tedge"

do_install () {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}${systemd_system_unitdir}
        for service in ${S}/services/systemd/system/*; do
            install -m 0644 $service ${D}${systemd_system_unitdir}
        done 
	elif ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        for service in ${S}/services/sysvinit-yocto/init.d/*; do
            install -m 0755 $service ${D}${sysconfdir}/init.d
        done 
    fi

    # Install for every init manager but systemd as it is natively supported by thin-edge
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'false', 'true', d)}; then
        install -m 0644 ${S}/services/system.toml ${D}${TEDGE_CONFIG_DIR}

        install -d ${D}${sysconfdir}/tedgectl
        install -m 0644 ${S}/services/tedgectl_env ${D}${sysconfdir}/tedgectl/env

        install -d ${D}${bindir}
        install -m 0755 ${S}/services/tedgectl ${D}${bindir}
    fi

    # Remove c8y-firmware-plugin as this is deprecated
    rm -f ${D}${systemd_system_unitdir}/c8y-firmware-plugin.service
    rm -f ${D}${sysconfdir}/init.d/c8y-firmware-plugin
}

PACKAGES += "tedge-agent tedge-mapper-c8y tedge-mapper-aws tedge-mapper-az tedge-mapper-collectd"

FILES:${PN} += "\
    ${sysconfdir}/init.d/* \
    ${TEDGE_CONFIG_DIR}/system.toml \
    ${sysconfdir}/tedgectl/env \
    ${bindir}/tedgectl \
"

FILES:tedge-agent += "\
    ${systemd_system_unitdir}/tedge-agent.service \
"
FILES:tedge-mapper-c8y += "\
    ${systemd_system_unitdir}/tedge-mapper-c8y.service \
    ${systemd_system_unitdir}/c8y-remote-access-plugin@.service \
    ${systemd_system_unitdir}/c8y-remote-access-plugin.socket \
"
FILES:tedge-mapper-aws += "\
    ${systemd_system_unitdir}/tedge-mapper-aws.service \
"
FILES:tedge-mapper-az += "\
    ${systemd_system_unitdir}/tedge-mapper-az.service \
"
FILES:tedge-mapper-collectd += "\
    ${systemd_system_unitdir}/tedge-mapper-collectd.service \
"

SYSTEMD_PACKAGES = "tedge-agent tedge-mapper-c8y tedge-mapper-aws tedge-mapper-az tedge-mapper-collectd"
SYSTEMD_SERVICE:tedge-agent = "tedge-agent.service"
SYSTEMD_SERVICE:tedge-mapper-c8y = "tedge-mapper-c8y.service c8y-remote-access-plugin@.service c8y-remote-access-plugin.socket"
SYSTEMD_SERVICE:tedge-mapper-aws = "tedge-mapper-aws.service"
SYSTEMD_SERVICE:tedge-mapper-az = "tedge-mapper-az.service"
SYSTEMD_SERVICE:tedge-mapper-collectd = "tedge-mapper-collectd.service"

ALLOW_EMPTY:${PN} = "1"