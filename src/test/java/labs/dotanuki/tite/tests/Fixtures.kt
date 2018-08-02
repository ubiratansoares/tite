package labs.dotanuki.tite.tests

import io.reactivex.*
import io.reactivex.subjects.PublishSubject

internal object Fixtures {

    fun thatEmmits() = Observable.just(1, 2, 3)

    fun thatHasNoValues() = Maybe.empty<Any>()

    fun thatFail() = Single.error<Any>(IllegalStateException("Fail"))

    fun thatFailsWith(error: Throwable) = Flowable.error<Any>(error)

    fun neverStarts() = Completable.never()

    fun neverCompletes() = PublishSubject.create<Int>()

}