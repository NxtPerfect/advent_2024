package lib;

/**
 * Tests
 * Class to give simple functions to help test code
 */
public class Tests {

  public static final <T extends Comparable<T>> void assertTrue(final T actualResult, final T expectedResult) {
    try {
      if (actualResult != expectedResult) {
        throw new AssertionFailedException(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (final AssertionFailedException e) {
      System.err.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  public static final void assertTrue(final String actualResult, final String expectedResult) {
    try {
      if (actualResult.equals(expectedResult)) {
        throw new AssertionFailedException(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (final AssertionFailedException e) {
      System.err.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  public static final <T extends Comparable<T>> void assertNotTrue(final T actualResult, final T expectedResult) {
    try {
      if (actualResult != expectedResult) {
        throw new AssertionFailedException(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (final AssertionFailedException e) {
      System.err.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  public static final void assertNotTrue(final String actualResult, final String expectedResult) {
    try {
      if (!actualResult.equals(expectedResult)) {
        throw new AssertionFailedException(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (final AssertionFailedException e) {
      System.err.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  public void runTestSuit() {
    // TODO: Run all tests and print pass/fail
  }
}
