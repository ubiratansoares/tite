package labs.dotanuki.tite.checks

import labs.dotanuki.tite.RxTester
import kotlin.reflect.KClass

class ErrorVerifications<T>(private val tester: RxTester<T>) {

    val fails by lazy { CheckForUniqueError(tester) }

}

class CheckForUniqueError<T>(private val tester: RxTester<T>) {

    infix fun byError(error: Throwable) {
        tester.assertError(error)
    }

    infix fun byType(kClass: KClass<out Throwable>) {
        tester.assertError(kClass.java)
    }

    infix fun withErrorMessage(errorMessage: String) {
        tester.assertErrorMessage(errorMessage)
    }

    infix fun byMatching(predicate: (Throwable) -> Boolean) {
        tester.assertError(predicate)
    }

}