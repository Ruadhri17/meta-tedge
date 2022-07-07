# Auto-Generated by cargo-bitbake 0.3.16-alpha.0
#
inherit cargo

# If this is git based prefer versioned ones if they exist
# DEFAULT_PREFERENCE = "-1"

# how to get rust-hello could be as easy as but default to a git checkout:
# SRC_URI += "crate://crates.io/rust-hello/0.1.0"
SRC_URI += "git://git@github.com/Bravo555/rust-hello.git;protocol=ssh;nobranch=1;branch=main"
SRCREV = "cea2427835ad99c4b6653163229b83457a26d029"
S = "${WORKDIR}/git"
CARGO_SRC_DIR = "crates/hello"
PV:append = ".AUTOINC+cea2427835"

# please note if you have entries that do not begin with crate://
# you must change them to how that package can be fetched
SRC_URI += " \
    file://0001-Cargo.toml-do-not-abort-on-panic.patch \
"



# FIXME: update generateme with the real MD5 of the license file
LIC_FILES_CHKSUM = " \
    "

SUMMARY = "test"
HOMEPAGE = "test"
LICENSE = "CLOSED"

# includes this file if it exists but does not fail
# this is useful for anything you may want to override from
# what cargo-bitbake generates.
include rust-hello-${PV}.inc
include rust-hello.inc
