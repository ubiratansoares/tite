package labs.dotanuki.tite.checks

import labs.dotanuki.tite.RxTester
import java.util.concurrent.TimeUnit

class AwaitCriteria<T>(private val tester: RxTester<T>) {

    val untilTerminalEvent: Unit
        get() {
            tester.awaitTerminalEvent()
        }

    val untilEmissions = AwaitByCountingEmissions(tester)

    val countingTime = AwaitByCountingTime(tester)

}

class AwaitByCountingEmissions<T>(private val tester: RxTester<T>) {

    infix fun reach(count: Int) {
        tester.awaitCount(count)
    }
}

class AwaitByCountingTime<T>(private val tester: RxTester<T>) {

    infix fun until(timeAndUnit: Pair<Long, TimeUnit>) {
        tester.await(timeAndUnit.first, timeAndUnit.second)
    }
}
