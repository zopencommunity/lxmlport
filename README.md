# lxmlport

z/OS port of [lxml](https://lxml.de/) — XML and XSLT processing for Python.

Unlike the recent Rust-backed Python ports, lxml is **compiled on z/OS**. It is
a C extension over `libxml2` and `libxslt`, both of which zopen already
provides, so there is no cross-compile step and no prebuilt wheel: the standard
zopen Python build system compiles it once per interpreter (3.12, 3.13, 3.14).

## Dependencies

`libxml2` and `libxslt` are the libraries being wrapped. `zlib`, `libiconv` and
`zoslib` are needed because zopen ships static archives — anything linking
libxml2 must also name what libxml2 itself depends on, or the bind fails with
unresolved `__open_ascii`, `libiconv_open` and similar.

## Install

```sh
zopen install lxml
```

Or from the wheel index:

```sh
export PIP_EXTRA_INDEX_URL="https://repo.zopen.community/pypi/wheels/simple/"
export PIP_CONSTRAINT="https://repo.zopen.community/pulp/content/constraints/zopen-constraints.txt"
python3 -m venv --system-site-packages .venv && . .venv/bin/activate
pip install lxml
```

`--system-site-packages` matters on z/OS: several packages the interpreters
bundle cannot be installed from PyPI, and a plain venv hides them.
