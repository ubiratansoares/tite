package labs.dotanuki.tite.tests

import io.reactivex.Observable
import labs.dotanuki.tite.given
import org.junit.Test


class TestsForEmissionChecks {

    private val letters = listOf("A", "B", "C")
    private fun target() = Observable.fromIterable(letters)

    @Test fun `dsl for items are`() {
        given(target()) {
            verifyForEmissions {
                items are letters
            }
        }
    }

    @Test fun `dsl for items match`() {

        val numbers = sequenceOf(1, 2, 3)
        val fromSequence = Observable.fromIterable(numbers.asIterable())

        given(fromSequence) {
            verifyForEmissions {
                items match numbers
            }
        }
    }

    @Test fun `dsl for never emmits`() {
        given(target()) {
            verifyForEmissions {
                never emmits "D"
            }
        }
    }

    @Test fun `dsl for count should be`() {
        given(target()) {
            verifyForEmissions {
                count shouldBe letters.size
            }
        }
    }

    @Test fun `dsl for first item should be`() {
        given(target()) {
            verifyForEmissions {
                firstItem shouldBe "A"
            }
        }
    }

    @Test fun `dsl for matching criteria`() {
        given(target()) {
            verifyForEmissions {
                matches criteria {
                    position = 1
                    value = "B"
                }
            }
        }
    }
}