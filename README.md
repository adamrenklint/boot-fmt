# boot-fmt

Boot task to automatically fix Clojure(Script) syntax formatting with cljfmt

[![CircleCI](https://circleci.com/gh/adamrenklint/boot-fmt.svg?style=svg)](https://circleci.com/gh/adamrenklint/boot-fmt)

```clojure
[adamrenklint/boot-fmt "1.2.1"] ;; latest release
```

## Usage

```
$ boot fmt
Formatting boot_fmt.clj
```

## Using custom `:indents` rules

See example of [custom indentation rules](https://github.com/weavejester/cljfmt#indentation-rules) in [build.boot](https://github.com/adamrenklint/boot-fmt/blob/master/build.boot#L29)

## License

Copyright (c) 2017 [Adam Renklint](http://adamrenklint.com)

Distributed under the [MIT license](https://github.com/adamrenklint/boot-fmt/blob/master/LICENSE)
