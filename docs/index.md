# TITE

> A Kotlin DSL for gorgeous RxJava2 tests

## Motivation

RxJava2 gives us a handy testing API : besides `TestSubscriber` and `TestObserver`, you also have a built-in `test()` API method available for every available reactive type, that shortcuts the retrieval of a **TestObserver** or **TestSubscriber** instance and streamlines your testing flow.  

```

val letters = Observable.just("A","B","C")

letters
	.test()
	.assertValueCount(3)
	.assertComplete()


```

Both **TestSubscriber** and **TestObserver** assertions let you get your tests done; but after using these APIs for a while, you may figure out they are not perfect ... 

Nevertheless, Kotlin empowers developers with a new world of possibilities, including a new field of DSL grammars using language features like **infix notation** for functions representations, **lambdas extensions** over instance types and others.

With this in mind, Tite was born. It aims to apply some grammar over 
**TestSubscriber** and **TestObserver** APIs while improving these APIs discoverability as well.

## Source Code

Check it on [Github](https://github.com/ubiratansoares/tite)

## Setup

For now, Tite is available through Jitpack. To add it into your project :

- Setup Jitpack as a Maven repository

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```	

- Add this library to your dependencies. For an Android project

```
dependencies {
	implementation 'com.github.ubiratansoares.tite:tite:$version'
}
```	

Check this project [releases](https://github.com/ubiratansoares/tite/releases) for the latest version.

This library will be available at Maven Central soon, I`m looking for some community feedback.

## DSL Design

Tite DSL assumes that intentions for testing some reactive-type are grouped, under the following DSL blocks

#### Conditions before performing verifications

```kotlin

val target = Observable.range(1, 5)

given(target) {
    beforeChecksAwait {
        untilEmissions reach 2 // will ignore first two items
    }
}

```


#### Verifying emmited values

```kotlin

val numbers = Observable.just(1, 2, 3)

given(numbers) {
    verifyForEmissions {
        items match sequenceOf(1, 2, 3)
    }
}

```

#### Verifying signaled error 

```kotlin

val expectedReason = IllegalStateException("Ouch!")
val broken = Flowable.error<Any>(reason)

given(broken) {
    verifyWhenError {
        fails byError expectedReason
    }
}


```

#### Verifying lifecycle of a sequence

```kotlin

val neverStarts = Completable.never()

given(neverStarts) {
    assertThatSequence {
        should notBe terminated
    }
}


```
#### Mixed verifications

Of course, you can mix all this blocks under the same unit test context, putting some structure over the *fluent-and-chainable* way of traditional Java

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
}


```


If you want to learn all convered RxJava2 testing APIs without exploring the DSL itself, please, refer to this DSL unit tests .

## Building

In order to hack-and-mess with this library, we recommend

- IntellijIDEA with Kotlin plugin `1.3.x` or newer

This library is builded with Gradle. The IDE should integrate seamlessly with this project via the default **Gradle wrapper**.

## Is this library used by someone / somewhere ?

Yes. At [Stone](https://stone.com.br) we are using it extensively into some new products (to be launched soon! 🚀), but this library concept (and practical field validation) is older than those products, mine own, and I decided to open-source it right now.

## Further Work

If you have an idea for this library, or question, please file an issue for it!

In the short term roadmap we have

- Publish it to Maven Central
- Cover 100% of RxJava testing APIs with DSL
- Thinking about TestScheduler DSLs as well

## License

This library is released under the [MIT license](https://tldrlegal.com/license/mit-license)

