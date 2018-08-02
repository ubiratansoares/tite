package labs.dotanuki.tite.tests

import io.reactivex.Observable
import labs.dotanuki.tite.checks.broken
import labs.dotanuki.tite.checks.completed
import labs.dotanuki.tite.given
import labs.dotanuki.tite.tests.Fixtures.thatEmmits
import labs.dotanuki.tite.tests.Fixtures.thatHasNoValues
import labs.dotanuki.tite.util.seconds
import org.junit.Test


class TestsForAwaitingChecks {

    @Test fun `dsl for until terminal event`() {

        given(thatHasNoValues()) {
            beforeChecksAwait {
                untilTerminalEvent
            }

            assertThatSequence {
                should be completed
            }
        }
    }

    @Test fun `dsl for until emissions`() {

        val target = Observable.range(1, 5)

        given(target) {
            beforeChecksAwait {
                untilEmissions reach 2
            }

            assertThatSequence {
                should be completed
            }
        }
    }

    @Test fun `dsl for counting time`() {

        given(thatEmmits()) {
            beforeChecksAwait {
                countingTime until 2.seconds
            }

            assertThatSequence {
                should notBe broken
            }
        }
    }
}