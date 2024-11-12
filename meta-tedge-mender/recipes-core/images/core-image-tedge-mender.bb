require recipes-core/images/core-image-tedge.bb

IMAGE_INSTALL:append = " \
    tedge-state-scripts \
    tedge-firmware \
"
