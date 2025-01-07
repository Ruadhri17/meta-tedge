SRCREV = "bbfef48d4beb6bb23a8828c6c953078f3272dfe1"
S = "${WORKDIR}/git"

SRC_URI += "\
file://0003-Cargo.toml-do-not-strip.patch \
"

TEDGE_EXCLUDE = "c8y-firmware-plugin"

require tedge.inc
