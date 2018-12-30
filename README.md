# TITE

> A Kotlin DSL for gorgeous RxJava2 tests

[![CircleCI](https://circleci.com/gh/ubiratansoares/tite/tree/master.svg?style=svg&circle-token=188c963e29f181c85e6c2559ca31ac3935fedc7e)](https://circleci.com/gh/ubiratansoares/tite/tree/master) 
![Packagist](https://img.shields.io/packagist/l/doctrine/orm.svg?style=flat-square) [![](https://jitpack.io/v/ubiratansoares/tite.svg)](https://jitpack.io/#ubiratansoares/tite)


Tite is a simple DSL on top of existing RxJava2 testing APIs. This DSL proposes a nice way to perform assertions, while leveraging 100% on existing testing machinery of current RxJava.

## Overview

Powered by Tite, tests for your RxJava-based APIs will look like this

```kotlin

val words = Observable.just("Adenor", "Leonardo", "Bacchi")

given(words) {

    beforeChecksAwait {
        countingTime until 3.seconds
    }

    assertThatSequence {
        should be completed
        should be terminated
        should notBe broken
        should be subscribed
    }

    verifyForEmissions {
        items match sequenceOf("Adenor", "Leonardo", "Bacchi")
        firstItem shouldBe "Adenor"
        never emmits "Parreira"
        never emmits "Felipão"
    }
    
    verifyWhenError {
        fails byError NumberFormatException
        fails withErrorMessage "exception msg"
        fails byType FileAlreadyExistsException::class
        fails byMatching { it -> it == CredentialsAccessError }
    }
}

```

instead of this

```kotlin

val words = Observable.just("Adenor", "Leonardo", "Bacchi")

words.test()
    .assertComplete()
    .assertTerminated()
    .assertNoErrors()
    .assertValueSequence(listOf("Adenor", "Leonardo", "Bacchi"))
    // ... more verifications
    
```

Don't get me wrong here : fluent, chainable API style is quite great for Java ... but we can do better with Kotlin! 

## Quick Setup

Grab this dependency to your Gradle project using [Jitpack](https://jitpack.io) 

For Android projects

```groovy
dependencies {
	implementation 'com.github.ubiratansoares:tite:<latest_version>'
}
```

For non-Android Gradle projects

```
dependencies {
	compile 'com.github.ubiratansoares:tite:<latest_version>'
}
```

Current version of this library (`0.1.1`) matches

- Kotlin `1.3.11`
- RxJava `2.2.4`

## Documentation

You can check the full API documentation, including samples for DSL grammar [here](https://ubiratansoares.github.io/tite).

You may want to check the latests [releases](https://github.com/ubiratan/tite/releases) for this project as well.

## Contributing

1. Fork this repository
2. Create your branch
3. Code your contributions
4. Open a PR !!! 🚀

## About

`Adenor Leonardo Bacchi`, a.k.a Tite, is a physical education professional, teacher, and legendary soccer team coach. 

He was Brazil team's coach at FIFA's World Cup (Russia / 2018), as well the main leader behind the greatests achievements of Sport Club Corinthians Paulista - the greatest soccer team in Brazil - in the last decade. ⚽

## License

```
The MIT License (MIT)

Copyright (c) 2018 Ubiratan Soares

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```