package org.fest.assertions.api.datetime;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;
import static org.fest.assertions.api.JODA_TIME.assertThat;
import static org.fest.util.FailureMessages.actualIsNull;

import static org.joda.time.DateTimeZone.UTC;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author Paweł Stawicki
 * @author Joel Costigliola
 */
@RunWith(Theories.class)
public class DateTimeAssert_isAfterOrEqualTo_Test extends DateTimeAssertBaseTest {

  @Theory
  public void test_isAfterOrEqual_assertion(DateTime referenceDate, DateTime dateBefore, DateTime dateAfter) {
    // GIVEN
    testAssumptions(referenceDate, dateBefore, dateAfter);
    // WHEN
    assertThat(dateAfter).isAfterOrEqualTo(referenceDate);
    assertThat(dateAfter).isAfterOrEqualTo(referenceDate.toString());
    assertThat(referenceDate).isAfterOrEqualTo(referenceDate);
    assertThat(referenceDate).isAfterOrEqualTo(referenceDate.toString());
    // THEN
    verify_that_isAfterOrEqual_assertion_fails_and_throws_AssertionError(dateBefore, referenceDate);
  }

  @Test
  public void test_isAfterOrEqual_assertion_error_message() {
    try {
      assertThat(new DateTime(2000, 1, 5, 3, 0, 5, UTC)).isAfterOrEqualTo(new DateTime(2012, 1, 1, 3, 3, 3, UTC));
    } catch (AssertionError e) {
      assertThat(e).hasMessage(
          "expected:<2000-01-05T03:00:05.000Z> to be after or equals to:<2012-01-01T03:03:03.000Z>");
      return;
    }
    fail("Should have thrown AssertionError");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectException(AssertionError.class, actualIsNull());
    DateTime actual = null;
    assertThat(actual).isAfterOrEqualTo(new DateTime());
  }

  @Test
  public void should_fail_if_dateTime_parameter_is_null() {
    expectException(IllegalArgumentException.class, "The DateTime to compare actual with should not be null");
    assertThat(new DateTime()).isAfterOrEqualTo((DateTime) null);
  }

  @Test
  public void should_fail_if_dateTime_as_string_parameter_is_null() {
    expectException(IllegalArgumentException.class,
        "The String representing the DateTime to compare actual with should not be null");
    assertThat(new DateTime()).isAfterOrEqualTo((String) null);
  }

  private static void verify_that_isAfterOrEqual_assertion_fails_and_throws_AssertionError(DateTime dateToCheck,
      DateTime reference) {
    try {
      assertThat(dateToCheck).isAfterOrEqualTo(reference);
    } catch (AssertionError e) {
      // AssertionError was expected, test same assertion with String based parameter
      try {
        assertThat(dateToCheck).isAfterOrEqualTo(reference.toString());
      } catch (AssertionError e2) {
        // AssertionError was expected (again)
        return;
      }
    }
    fail("Should have thrown AssertionError");
  }

}
