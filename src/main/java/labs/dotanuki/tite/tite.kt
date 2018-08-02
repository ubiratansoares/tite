package labs.dotanuki.tite

import io.reactivex.*
import io.reactivex.observers.BaseTestConsumer
import labs.dotanuki.tite.checks.AwaitCriteria
import labs.dotanuki.tite.checks.EmissionsVerifications
import labs.dotanuki.tite.checks.ErrorVerifications
import labs.dotanuki.tite.checks.SequenceVerifications

/*
*
*   Tite, because :
*
*   Reatibilidade &&
*   Testabilidade &&
*   Confiabilidade &&
*   Verificabilidade &&
*   Competitividade &&
*   Opensourceabilidade == Titebilidade
*
*/

internal typealias RxTester<T> = BaseTestConsumer<T, *>

fun <T> given(target: Flowable<T>, block: EvaluationDSL<T>.() -> Unit) {
    EvaluationDSL(target.test()).block()
}

fun <T> given(target: Observable<T>, block: EvaluationDSL<T>.() -> Unit) {
    EvaluationDSL(target.test()).block()
}

fun <T> given(target: Single<T>, block: EvaluationDSL<T>.() -> Unit) {
    EvaluationDSL(target.test()).block()
}

fun <T> given(target: Maybe<T>, block: EvaluationDSL<T>.() -> Unit) {
    EvaluationDSL(target.test()).block()
}

fun given(target: Completable, block: EvaluationDSL<Void>.() -> Unit) {
    EvaluationDSL(target.test()).block()
}

class EvaluationDSL<T>(private val tester: RxTester<T>) {

    fun beforeChecksAwait(block: AwaitCriteria<*>.() -> Unit) {
        AwaitCriteria(tester).block()
    }

    fun verifyForEmissions(block: EmissionsVerifications<T>.() -> Unit) {
        EmissionsVerifications(tester).block()
    }

    fun verifyWhenError(block: ErrorVerifications<T>.() -> Unit) {
        ErrorVerifications(tester).block()
    }

    fun assertThatSequence(block: SequenceVerifications<T>.() -> Unit) {
        SequenceVerifications(tester).block()
    }

}







