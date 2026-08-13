[![Automatic version updates](https://github.com/zopencommunity/lxmlport/actions/workflows/bump.yml/badge.svg)](https://github.com/ZOSOpenTools/lxmlport/actions/workflows/bump.yml)

# lxml

XML and XSLT processing for Python, built on libxml2 and libxslt.

# Installation and Usage

Use the zopen package manager ([QuickStart Guide](https://zopen.community/#/Guides/QuickStart)) to install:
```bash
zopen install lxml
```

# Building from Source

1. Clone the repository:
```bash
git clone https://github.com/zopencommunity/lxmlport.git
cd lxmlport
```
2. Build using zopen:
```bash
zopen build -vv
```

See the [zopen porting guide](https://zopen.community/#/Guides/Porting) for more details.

Or from the zopen wheel index:

```bash
export PIP_EXTRA_INDEX_URL="https://repo.zopen.community/pypi/wheels/simple/"
export PIP_CONSTRAINT="https://repo.zopen.community/pulp/content/constraints/zopen-constraints.txt"
python3 -m venv --system-site-packages .venv && . .venv/bin/activate
pip install lxml
```

`--system-site-packages` matters on z/OS: several packages the interpreters
bundle cannot be installed from PyPI, and a plain venv hides them.

# Documentation

Upstream documentation is at [lxml.de](https://lxml.de/).

Unlike the recent Rust-backed Python ports, lxml is **compiled on z/OS**. It is
a C extension over `libxml2` and `libxslt`, both already in the zopen catalog,
so there is no cross-compile step and no prebuilt wheel: the port is built once
per interpreter (3.12, 3.13 and 3.14).

# Troubleshooting

`zlib`, `libiconv` and `zoslib` are dependencies even though lxml never calls
into them. zopen ships static archives, so anything linking `libxml2` must also
name what `libxml2` itself depends on. Omitting them fails at bind time with
unresolved `__open_ascii`, `libiconv_open` and similar, which reads like a
broken `libxml2` rather than a missing dependency.

The link flags come from `xml2-config` and `xslt-config` rather than being
written into the buildenv, so they stay correct when a dependency is rebuilt.

# Contributing
Contributions are welcome! Please follow the [zopen contribution guidelines](https://github.com/zopencommunity/meta/blob/main/CONTRIBUTING.md).
