package labs.dotanuki.tite.tests

import labs.dotanuki.tite.checks.*
import labs.dotanuki.tite.given
import labs.dotanuki.tite.tests.Fixtures.neverCompletes
import labs.dotanuki.tite.tests.Fixtures.neverStarts
import labs.dotanuki.tite.tests.Fixtures.thatEmmits
import labs.dotanuki.tite.tests.Fixtures.thatFail
import labs.dotanuki.tite.tests.Fixtures.thatHasNoValues
import org.junit.Test


class TestsForSequenceChecks {


    @Test fun `dsl for should be terminated`() {

        given(thatEmmits()) {
            assertThatSequence {
                should be terminated
            }
        }
    }

    @Test fun `dsl for should NOT be terminated`() {

        given(neverStarts()) {
            assertThatSequence {
                should notBe terminated
            }
        }

        given(neverCompletes()) {
            assertThatSequence {
                should notBe terminated
            }
        }
    }

    @Test fun `dsl for should be completed`() {

        given(thatEmmits()) {
            assertThatSequence {
                should be completed
            }
        }

        given(thatHasNoValues()) {
            assertThatSequence {
                should be completed
            }
        }
    }

    @Test fun `dsl for should NOT be completed`() {

        given(thatFail()) {
            assertThatSequence {
                should notBe completed
            }
        }

        given(neverCompletes()) {
            assertThatSequence {
                should notBe completed
            }
        }
    }

    @Test fun `dsl for should be broken`() {

        given(thatFail()) {
            assertThatSequence {
                should be broken
            }
        }
    }

    @Test fun `dsl for should NOT be broken`() {

        given(thatEmmits()) {
            assertThatSequence {
                should be broken
            }
        }
    }


    @Test fun `dsls for should emmit`() {

        given(thatEmmits()) {
            assertThatSequence {
                should emmit something
            }
        }

    }

    @Test fun `dsls for should NOT emmit `() {

        given(thatHasNoValues()) {
            assertThatSequence {
                should emmit nothing
            }
        }

        given(thatFail()) {
            assertThatSequence {
                should emmit nothing
            }
        }
    }

}