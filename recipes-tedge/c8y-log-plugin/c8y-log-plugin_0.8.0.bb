# Auto-Generated by cargo-bitbake 0.3.16
#
inherit cargo

# If this is git based prefer versioned ones if they exist
# DEFAULT_PREFERENCE = "-1"

# how to get c8y_log_plugin could be as easy as but default to a git checkout:
# SRC_URI += "crate://crates.io/c8y_log_plugin/0.8.0"
SRC_URI += "git://github.com/thin-edge/thin-edge.io.git;protocol=https;nobranch=1;branch=${PV}"
SRCREV = "79e3bfa80f96e48546c26a1c9e78a0977632f6d9"
S = "${WORKDIR}/git"
CARGO_SRC_DIR = "plugins/c8y_log_plugin"

# please note if you have entries that do not begin with crate://
# you must change them to how that package can be fetched
SRC_URI += " \
    crate://crates.io/addr2line/0.17.0 \
    crate://crates.io/adler/1.0.2 \
    crate://crates.io/aho-corasick/0.7.19 \
    crate://crates.io/anyhow/1.0.65 \
    crate://crates.io/argh/0.1.9 \
    crate://crates.io/argh_derive/0.1.9 \
    crate://crates.io/argh_shared/0.1.9 \
    crate://crates.io/asn1-rs-derive/0.4.0 \
    crate://crates.io/asn1-rs-impl/0.1.0 \
    crate://crates.io/asn1-rs/0.5.1 \
    crate://crates.io/assert-json-diff/2.0.2 \
    crate://crates.io/assert_cmd/2.0.5 \
    crate://crates.io/assert_matches/1.5.0 \
    crate://crates.io/async-log-attributes/1.0.1 \
    crate://crates.io/async-log/2.0.0 \
    crate://crates.io/async-stream-impl/0.3.3 \
    crate://crates.io/async-stream/0.3.3 \
    crate://crates.io/async-trait/0.1.58 \
    crate://crates.io/atty/0.2.14 \
    crate://crates.io/autocfg/1.1.0 \
    crate://crates.io/backoff/0.4.0 \
    crate://crates.io/backtrace/0.3.66 \
    crate://crates.io/base64/0.13.0 \
    crate://crates.io/bit-set/0.5.3 \
    crate://crates.io/bit-vec/0.6.3 \
    crate://crates.io/bitflags/1.3.2 \
    crate://crates.io/block-buffer/0.10.3 \
    crate://crates.io/bstr/0.2.17 \
    crate://crates.io/buf_redux/0.8.4 \
    crate://crates.io/bumpalo/3.11.1 \
    crate://crates.io/byteorder/1.4.3 \
    crate://crates.io/bytes/1.2.1 \
    crate://crates.io/cache-padded/1.2.0 \
    crate://crates.io/cast/0.3.0 \
    crate://crates.io/cc/1.0.73 \
    crate://crates.io/cfg-if/0.1.10 \
    crate://crates.io/cfg-if/1.0.0 \
    crate://crates.io/clap/2.34.0 \
    crate://crates.io/clap/3.2.22 \
    crate://crates.io/clap_derive/3.2.18 \
    crate://crates.io/clap_lex/0.2.4 \
    crate://crates.io/colored/2.0.0 \
    crate://crates.io/concurrent-queue/1.2.4 \
    crate://crates.io/confy/0.4.0 \
    crate://crates.io/core-foundation-sys/0.8.3 \
    crate://crates.io/core-foundation/0.9.3 \
    crate://crates.io/cpufeatures/0.2.5 \
    crate://crates.io/crc32fast/1.3.2 \
    crate://crates.io/criterion-plot/0.4.5 \
    crate://crates.io/criterion/0.3.6 \
    crate://crates.io/crossbeam-channel/0.5.6 \
    crate://crates.io/crossbeam-deque/0.8.2 \
    crate://crates.io/crossbeam-epoch/0.9.11 \
    crate://crates.io/crossbeam-utils/0.8.12 \
    crate://crates.io/crypto-common/0.1.6 \
    crate://crates.io/csv-core/0.1.10 \
    crate://crates.io/csv/1.1.6 \
    crate://crates.io/ctor/0.1.26 \
    crate://crates.io/data-encoding/2.3.2 \
    crate://crates.io/der-parser/8.1.0 \
    crate://crates.io/difflib/0.4.0 \
    crate://crates.io/digest/0.10.5 \
    crate://crates.io/directories/2.0.2 \
    crate://crates.io/dirs-sys/0.3.7 \
    crate://crates.io/displaydoc/0.2.3 \
    crate://crates.io/doc-comment/0.3.3 \
    crate://crates.io/downcast/0.11.0 \
    crate://crates.io/easy_reader/0.5.2 \
    crate://crates.io/either/1.8.0 \
    crate://crates.io/encoding_rs/0.8.31 \
    crate://crates.io/env_logger/0.7.1 \
    crate://crates.io/env_logger/0.9.1 \
    crate://crates.io/event-listener/2.5.3 \
    crate://crates.io/fastrand/1.8.0 \
    crate://crates.io/filetime/0.2.18 \
    crate://crates.io/flate2/1.0.24 \
    crate://crates.io/float-cmp/0.9.0 \
    crate://crates.io/flume/0.10.14 \
    crate://crates.io/fnv/1.0.7 \
    crate://crates.io/form_urlencoded/1.1.0 \
    crate://crates.io/fragile/2.0.0 \
    crate://crates.io/freedesktop_entry_parser/1.3.0 \
    crate://crates.io/fs_extra/1.2.0 \
    crate://crates.io/futures-channel/0.3.25 \
    crate://crates.io/futures-core/0.3.25 \
    crate://crates.io/futures-executor/0.3.25 \
    crate://crates.io/futures-io/0.3.25 \
    crate://crates.io/futures-macro/0.3.25 \
    crate://crates.io/futures-sink/0.3.25 \
    crate://crates.io/futures-task/0.3.25 \
    crate://crates.io/futures-timer/3.0.2 \
    crate://crates.io/futures-util/0.3.25 \
    crate://crates.io/futures/0.3.25 \
    crate://crates.io/generic-array/0.14.6 \
    crate://crates.io/getrandom/0.2.7 \
    crate://crates.io/gimli/0.26.2 \
    crate://crates.io/glob/0.3.0 \
    crate://crates.io/h2/0.3.14 \
    crate://crates.io/half/1.8.2 \
    crate://crates.io/hamcrest2/0.3.0 \
    crate://crates.io/hashbrown/0.12.3 \
    crate://crates.io/headers-core/0.2.0 \
    crate://crates.io/headers/0.3.8 \
    crate://crates.io/heck/0.4.0 \
    crate://crates.io/hermit-abi/0.1.19 \
    crate://crates.io/http-body/0.4.5 \
    crate://crates.io/http/0.2.8 \
    crate://crates.io/httparse/1.8.0 \
    crate://crates.io/httpdate/1.0.2 \
    crate://crates.io/humantime/1.3.0 \
    crate://crates.io/humantime/2.1.0 \
    crate://crates.io/hyper-rustls/0.23.0 \
    crate://crates.io/hyper/0.14.20 \
    crate://crates.io/idna/0.3.0 \
    crate://crates.io/indexmap/1.9.1 \
    crate://crates.io/inotify-sys/0.1.5 \
    crate://crates.io/inotify/0.9.6 \
    crate://crates.io/instant/0.1.12 \
    crate://crates.io/ipnet/2.5.0 \
    crate://crates.io/itertools/0.10.5 \
    crate://crates.io/itoa/0.4.8 \
    crate://crates.io/itoa/1.0.4 \
    crate://crates.io/jackiechan/0.0.4 \
    crate://crates.io/jemalloc-sys/0.3.2 \
    crate://crates.io/jemallocator/0.3.2 \
    crate://crates.io/js-sys/0.3.60 \
    crate://crates.io/kqueue-sys/1.0.3 \
    crate://crates.io/kqueue/1.0.7 \
    crate://crates.io/lazy_static/1.4.0 \
    crate://crates.io/libc/0.2.135 \
    crate://crates.io/lock_api/0.4.9 \
    crate://crates.io/log/0.4.17 \
    crate://crates.io/maplit/1.0.2 \
    crate://crates.io/memchr/2.5.0 \
    crate://crates.io/memmap/0.7.0 \
    crate://crates.io/memoffset/0.6.5 \
    crate://crates.io/mime/0.3.16 \
    crate://crates.io/mime_guess/2.0.4 \
    crate://crates.io/minimal-lexical/0.2.1 \
    crate://crates.io/miniz_oxide/0.5.4 \
    crate://crates.io/mio/0.8.4 \
    crate://crates.io/mockall/0.11.3 \
    crate://crates.io/mockall_derive/0.11.3 \
    crate://crates.io/mockito/0.31.0 \
    crate://crates.io/multipart/0.18.0 \
    crate://crates.io/nanoid/0.4.0 \
    crate://crates.io/nanorand/0.7.0 \
    crate://crates.io/nix/0.24.2 \
    crate://crates.io/nom/7.1.1 \
    crate://crates.io/normalize-line-endings/0.3.0 \
    crate://crates.io/notify/5.0.0 \
    crate://crates.io/nu-ansi-term/0.46.0 \
    crate://crates.io/num-bigint/0.2.6 \
    crate://crates.io/num-bigint/0.4.3 \
    crate://crates.io/num-complex/0.2.4 \
    crate://crates.io/num-integer/0.1.45 \
    crate://crates.io/num-iter/0.1.43 \
    crate://crates.io/num-rational/0.2.4 \
    crate://crates.io/num-traits/0.2.15 \
    crate://crates.io/num/0.2.1 \
    crate://crates.io/num_cpus/1.13.1 \
    crate://crates.io/num_threads/0.1.6 \
    crate://crates.io/object/0.29.0 \
    crate://crates.io/oid-registry/0.6.0 \
    crate://crates.io/once_cell/1.15.0 \
    crate://crates.io/oorandom/11.1.3 \
    crate://crates.io/openssl-probe/0.1.5 \
    crate://crates.io/os_str_bytes/6.3.0 \
    crate://crates.io/overload/0.1.1 \
    crate://crates.io/parking_lot/0.12.1 \
    crate://crates.io/parking_lot_core/0.9.4 \
    crate://crates.io/path-clean/0.1.0 \
    crate://crates.io/pem/1.1.0 \
    crate://crates.io/percent-encoding/2.2.0 \
    crate://crates.io/pin-project-internal/1.0.12 \
    crate://crates.io/pin-project-lite/0.2.9 \
    crate://crates.io/pin-project/1.0.12 \
    crate://crates.io/pin-utils/0.1.0 \
    crate://crates.io/plotters-backend/0.3.4 \
    crate://crates.io/plotters-svg/0.3.3 \
    crate://crates.io/plotters/0.3.4 \
    crate://crates.io/pollster/0.2.5 \
    crate://crates.io/ppv-lite86/0.2.16 \
    crate://crates.io/predicates-core/1.0.3 \
    crate://crates.io/predicates-tree/1.0.5 \
    crate://crates.io/predicates/2.1.1 \
    crate://crates.io/pretty_env_logger/0.4.0 \
    crate://crates.io/proc-macro-error-attr/1.0.4 \
    crate://crates.io/proc-macro-error/1.0.4 \
    crate://crates.io/proc-macro2/0.4.30 \
    crate://crates.io/proc-macro2/1.0.47 \
    crate://crates.io/proptest/1.0.0 \
    crate://crates.io/quick-error/1.2.3 \
    crate://crates.io/quick-error/2.0.1 \
    crate://crates.io/quote/0.6.13 \
    crate://crates.io/quote/1.0.21 \
    crate://crates.io/rand/0.8.5 \
    crate://crates.io/rand_chacha/0.3.1 \
    crate://crates.io/rand_core/0.6.4 \
    crate://crates.io/rand_xorshift/0.3.0 \
    crate://crates.io/rayon-core/1.9.3 \
    crate://crates.io/rayon/1.5.3 \
    crate://crates.io/rcgen/0.9.3 \
    crate://crates.io/redox_syscall/0.2.16 \
    crate://crates.io/redox_users/0.4.3 \
    crate://crates.io/regex-automata/0.1.10 \
    crate://crates.io/regex-syntax/0.6.27 \
    crate://crates.io/regex/1.6.0 \
    crate://crates.io/remove_dir_all/0.5.3 \
    crate://crates.io/reqwest/0.11.12 \
    crate://crates.io/ring/0.16.20 \
    crate://crates.io/routerify/3.0.0 \
    crate://crates.io/roxmltree/0.14.1 \
    crate://crates.io/rpassword/5.0.1 \
    crate://crates.io/rumqttc/0.17.0 \
    crate://crates.io/rumqttd/0.11.0 \
    crate://crates.io/rustc-demangle/0.1.21 \
    crate://crates.io/rusticata-macros/4.1.0 \
    crate://crates.io/rustls-native-certs/0.6.2 \
    crate://crates.io/rustls-pemfile/0.2.1 \
    crate://crates.io/rustls-pemfile/0.3.0 \
    crate://crates.io/rustls-pemfile/1.0.1 \
    crate://crates.io/rustls/0.20.7 \
    crate://crates.io/rustversion/1.0.9 \
    crate://crates.io/rusty-fork/0.3.0 \
    crate://crates.io/ryu/1.0.11 \
    crate://crates.io/safemem/0.3.3 \
    crate://crates.io/same-file/1.0.6 \
    crate://crates.io/schannel/0.1.20 \
    crate://crates.io/scoped-tls/1.0.0 \
    crate://crates.io/scopeguard/1.1.0 \
    crate://crates.io/sct/0.7.0 \
    crate://crates.io/security-framework-sys/2.6.1 \
    crate://crates.io/security-framework/2.7.0 \
    crate://crates.io/segments/0.1.0 \
    crate://crates.io/serde/1.0.145 \
    crate://crates.io/serde_cbor/0.11.2 \
    crate://crates.io/serde_derive/1.0.145 \
    crate://crates.io/serde_json/1.0.87 \
    crate://crates.io/serde_urlencoded/0.7.1 \
    crate://crates.io/serial_test/0.8.0 \
    crate://crates.io/serial_test_derive/0.8.0 \
    crate://crates.io/sha-1/0.10.0 \
    crate://crates.io/sha1/0.10.5 \
    crate://crates.io/sharded-slab/0.1.4 \
    crate://crates.io/signal-hook-registry/1.4.0 \
    crate://crates.io/similar/2.2.0 \
    crate://crates.io/slab/0.4.7 \
    crate://crates.io/smallvec/1.10.0 \
    crate://crates.io/socket2/0.4.7 \
    crate://crates.io/spin/0.5.2 \
    crate://crates.io/spin/0.9.4 \
    crate://crates.io/stats_alloc/0.1.10 \
    crate://crates.io/strsim/0.10.0 \
    crate://crates.io/strum_macros/0.24.3 \
    crate://crates.io/syn/0.15.44 \
    crate://crates.io/syn/1.0.102 \
    crate://crates.io/synstructure/0.12.6 \
    crate://crates.io/tempfile/3.3.0 \
    crate://crates.io/termcolor/1.1.3 \
    crate://crates.io/termtree/0.2.4 \
    crate://crates.io/test-case-macros/2.2.2 \
    crate://crates.io/test-case/2.2.2 \
    crate://crates.io/textwrap/0.11.0 \
    crate://crates.io/textwrap/0.15.1 \
    crate://crates.io/thiserror-impl/1.0.37 \
    crate://crates.io/thiserror/1.0.37 \
    crate://crates.io/thread_local/1.1.4 \
    crate://crates.io/time-macros/0.2.4 \
    crate://crates.io/time/0.3.15 \
    crate://crates.io/tinytemplate/1.2.1 \
    crate://crates.io/tinyvec/1.6.0 \
    crate://crates.io/tinyvec_macros/0.1.0 \
    crate://crates.io/tokio-macros/1.8.0 \
    crate://crates.io/tokio-rustls/0.23.4 \
    crate://crates.io/tokio-stream/0.1.11 \
    crate://crates.io/tokio-test/0.4.2 \
    crate://crates.io/tokio-tungstenite/0.17.2 \
    crate://crates.io/tokio-util/0.7.4 \
    crate://crates.io/tokio/1.21.2 \
    crate://crates.io/toml/0.5.9 \
    crate://crates.io/tower-service/0.3.2 \
    crate://crates.io/tracing-attributes/0.1.23 \
    crate://crates.io/tracing-core/0.1.30 \
    crate://crates.io/tracing-log/0.1.3 \
    crate://crates.io/tracing-subscriber/0.3.16 \
    crate://crates.io/tracing/0.1.37 \
    crate://crates.io/try-lock/0.2.3 \
    crate://crates.io/try-traits/0.1.1 \
    crate://crates.io/tungstenite/0.17.3 \
    crate://crates.io/twoway/0.1.8 \
    crate://crates.io/typenum/1.15.0 \
    crate://crates.io/unicase/2.6.0 \
    crate://crates.io/unicode-bidi/0.3.8 \
    crate://crates.io/unicode-ident/1.0.5 \
    crate://crates.io/unicode-normalization/0.1.22 \
    crate://crates.io/unicode-width/0.1.10 \
    crate://crates.io/unicode-xid/0.1.0 \
    crate://crates.io/unicode-xid/0.2.4 \
    crate://crates.io/untrusted/0.7.1 \
    crate://crates.io/url/2.3.1 \
    crate://crates.io/users/0.11.0 \
    crate://crates.io/utf-8/0.7.6 \
    crate://crates.io/valuable/0.1.0 \
    crate://crates.io/value-bag/1.0.0-alpha.9 \
    crate://crates.io/version_check/0.9.4 \
    crate://crates.io/wait-timeout/0.2.0 \
    crate://crates.io/walkdir/2.3.2 \
    crate://crates.io/want/0.3.0 \
    crate://crates.io/warp/0.3.3 \
    crate://crates.io/wasi/0.11.0+wasi-snapshot-preview1 \
    crate://crates.io/wasm-bindgen-backend/0.2.83 \
    crate://crates.io/wasm-bindgen-futures/0.4.33 \
    crate://crates.io/wasm-bindgen-macro-support/0.2.83 \
    crate://crates.io/wasm-bindgen-macro/0.2.83 \
    crate://crates.io/wasm-bindgen-shared/0.2.83 \
    crate://crates.io/wasm-bindgen/0.2.83 \
    crate://crates.io/web-sys/0.3.60 \
    crate://crates.io/webpki-roots/0.22.5 \
    crate://crates.io/webpki/0.22.0 \
    crate://crates.io/which/4.3.0 \
    crate://crates.io/whoami/1.2.3 \
    crate://crates.io/winapi-i686-pc-windows-gnu/0.4.0 \
    crate://crates.io/winapi-util/0.1.5 \
    crate://crates.io/winapi-x86_64-pc-windows-gnu/0.4.0 \
    crate://crates.io/winapi/0.3.9 \
    crate://crates.io/windows-sys/0.36.1 \
    crate://crates.io/windows-sys/0.42.0 \
    crate://crates.io/windows_aarch64_gnullvm/0.42.0 \
    crate://crates.io/windows_aarch64_msvc/0.36.1 \
    crate://crates.io/windows_aarch64_msvc/0.42.0 \
    crate://crates.io/windows_i686_gnu/0.36.1 \
    crate://crates.io/windows_i686_gnu/0.42.0 \
    crate://crates.io/windows_i686_msvc/0.36.1 \
    crate://crates.io/windows_i686_msvc/0.42.0 \
    crate://crates.io/windows_x86_64_gnu/0.36.1 \
    crate://crates.io/windows_x86_64_gnu/0.42.0 \
    crate://crates.io/windows_x86_64_gnullvm/0.42.0 \
    crate://crates.io/windows_x86_64_msvc/0.36.1 \
    crate://crates.io/windows_x86_64_msvc/0.42.0 \
    crate://crates.io/winreg/0.10.1 \
    crate://crates.io/x509-parser/0.14.0 \
    crate://crates.io/xmlparser/0.13.5 \
    crate://crates.io/yasna/0.5.0 \
    crate://crates.io/zeroize/1.5.7 \
    crate://crates.io/zip/0.6.3 \
"



# FIXME: update generateme with the real MD5 of the license file
LIC_FILES_CHKSUM = " \
    file://LICENSE.txt;md5=175792518e4ac015ab6696d16c4f607e \
"

SUMMARY = "Thin-edge device log file retriever for Cumulocity"
HOMEPAGE = "https://thin-edge.io"
LICENSE = "Apache-2.0"

# includes this file if it exists but does not fail
# this is useful for anything you may want to override from
# what cargo-bitbake generates.
include c8y_log_plugin-${PV}.inc
include c8y_log_plugin.inc
