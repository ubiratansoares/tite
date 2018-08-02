package labs.dotanuki.tite.tests

import io.reactivex.Observable
import labs.dotanuki.tite.checks.broken
import labs.dotanuki.tite.checks.completed
import labs.dotanuki.tite.checks.subscribed
import labs.dotanuki.tite.checks.terminated
import labs.dotanuki.tite.given
import labs.dotanuki.tite.util.seconds
import org.junit.Test

class TiteTests {

    @Test fun `checks for a soccer team warchief`() {

        val tite = Observable.just("Adenor", "Leonardo", "Bacchi")

        given(tite) {

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
                items are listOf("Adenor", "Leonardo", "Bacchi")
                firstItem shouldBe "Adenor"
                never emmits "Parreira"
                never emmits "Felipão"
            }
        }
    }

}