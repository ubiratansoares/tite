package labs.dotanuki.tite.tests

import labs.dotanuki.tite.given
import labs.dotanuki.tite.tests.Fixtures.thatFailsWith
import org.junit.Test
import java.lang.IllegalArgumentException

class TestsForErrorChecks {

    private val reporting = "Not accepted"
    private val failureCause = IllegalArgumentException(reporting)

    @Test fun `dsl for fails by error`() {
        given(thatFailsWith(failureCause)) {
            verifyWhenError {
                fails byError failureCause
            }
        }
    }

    @Test fun `dsl for fails by type`() {
        given(thatFailsWith(failureCause)) {
            verifyWhenError {
                fails byType IllegalArgumentException::class
            }
        }
    }

    @Test fun `dsl for fails with error message`() {
        given(thatFailsWith(failureCause)) {
            verifyWhenError {
                fails withErrorMessage reporting
            }
        }
    }

    @Test fun `dsl for fails by matching`() {
        given(thatFailsWith(failureCause)) {
            verifyWhenError {
                fails byMatching { it == failureCause }
            }
        }
    }
}