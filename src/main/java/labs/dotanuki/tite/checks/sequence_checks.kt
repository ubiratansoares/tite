package labs.dotanuki.tite.checks

import labs.dotanuki.tite.RxTester

sealed class LifecycleCondition
object terminated : LifecycleCondition()
object subscribed : LifecycleCondition()
object completed : LifecycleCondition()
object broken : LifecycleCondition()
object timedOut : LifecycleCondition()

sealed class EmissionCondition
object something : EmissionCondition()
object nothing : EmissionCondition()

class SequenceVerifications<T>(private val tester: RxTester<T>) {

    val should by lazy { LifecycleCheck(tester) }

}

class LifecycleCheck<T>(private val tester: RxTester<T>) {

    infix fun be(condition: LifecycleCondition) {
        when (condition) {
            is terminated -> tester.assertTerminated()
            is subscribed -> tester.assertSubscribed()
            is completed -> tester.assertComplete()
            is broken -> tester.errors().isNotEmpty()
            is timedOut -> tester.assertTimeout()
        }
    }

    infix fun notBe(condition: LifecycleCondition) {
        when (condition) {
            is terminated -> tester.assertNotTerminated()
            is subscribed -> tester.assertNotSubscribed()
            is completed -> tester.assertNotComplete()
            is broken -> tester.assertNoErrors()
            is timedOut -> tester.assertNoTimeout()
        }
    }

    infix fun emmit(condition: EmissionCondition) {
        when (condition) {
            is something -> tester.values().isNotEmpty()
            is nothing -> tester.assertNoValues()
        }
    }
}