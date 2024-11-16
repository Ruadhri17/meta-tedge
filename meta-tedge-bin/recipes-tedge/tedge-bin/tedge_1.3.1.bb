# Architecture variables
ARCH_REPO_CHANNEL = "release"
ARCH_VERSION = "1.3.1"
SRC_URI[aarch64.md5sum] = "0006c47d48ae214c236a07f863bb533b"
SRC_URI[armv6.md5sum] = "1892147ece0872665e323d00d3fd79e1"
SRC_URI[armv7.md5sum] = "8208b9e107805d4459a9b99ca195a766"
SRC_URI[x86_64.md5sum] = "c8b256a1cdef070b7d6bd1a2102f7460"
SRC_URI[riscv64.md5sum] = "d87b064d5054e43cad6a2476a2fbc871"

# Init manager variables
INIT_REPO_CHANNEL = "community"
INIT_VERSION = "0.6.0"
SRC_URI[openrc.md5sum] = "515bfcb3ff01cb6929dda6c6922eb7a7"
SRC_URI[systemd.md5sum] = "579cf149b89256fb83c7eb44aa45ccb1"
SRC_URI[sysvinit.md5sum] = "493f1a2e66fa3e154c558b138e5da6bf"

require tedge.inc
