package labs.dotanuki.tite.checks

import labs.dotanuki.tite.RxTester

class EmissionsVerifications<T>(private val tester: RxTester<T>) {

    val items by lazy { VerifyItemsEmmited(tester) }
    val never by lazy { NeverEmmits(tester) }
    val count by lazy { VerifyItemsCount(tester) }
    val firstItem by lazy { FirstItemMatcher(tester) }
    val matches by lazy { MatchItemByPosition(tester) }

}

class VerifyItemsEmmited<T>(private val tester: RxTester<T>) {

    infix fun are(items: List<T>) {
        tester.assertValueSequence(items)
    }

    infix fun match(items: Sequence<T>) {
        tester.assertValueSequence(items.asIterable())
    }
}

class NeverEmmits<T>(private val tester: RxTester<T>) {

    infix fun emmits(value: T) {
        tester.assertNever(value)
    }

}

class VerifyItemsCount<T>(private val tester: RxTester<T>) {

    infix fun shouldBe(count: Int) {
        tester.assertValueCount(count)
    }

}

class FirstItemMatcher<T>(private val tester: RxTester<T>) {

    infix fun shouldBe(value: T) {
        tester.assertValueAt(0, value)
    }

}

class MatchItemByPosition<T>(private val tester: RxTester<T>) {

    infix fun criteria(block: EmissionAtPositionMatchingCriteria<T>.() -> Unit) {
        EmissionAtPositionMatchingCriteria<T>().run {
            block()
            tester.assertValueAt(position, value)
        }
    }

}

class EmissionAtPositionMatchingCriteria<T> {
    var position: Int = 0
    var value: T? = null
}

