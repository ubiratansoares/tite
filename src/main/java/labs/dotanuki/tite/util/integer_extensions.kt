package labs.dotanuki.tite.util

import java.util.concurrent.TimeUnit


val Int.microseconds: Pair<Long, TimeUnit>
    get() = this.toLong() to TimeUnit.MICROSECONDS

val Int.miliseconds: Pair<Long, TimeUnit>
    get() = this.toLong() to TimeUnit.MILLISECONDS

val Int.seconds: Pair<Long, TimeUnit>
    get() = this.toLong() to TimeUnit.SECONDS

val Int.minutes: Pair<Long, TimeUnit>
    get() = this.toLong() to TimeUnit.MINUTES