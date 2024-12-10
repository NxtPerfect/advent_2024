package lib;

/**
 * Tests
 * Class to give simple functions to help test code
 */
public class Tests {

  public static <T extends Comparable<T>> void assertTrue (T actualResult, T expectedResult) throws AssertionFailedException {
    try {
      if (actualResult != expectedResult) {
        throw new AssertionFailedException(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (AssertionFailedException e) {
      System.out.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  public static void assertTrue (String actualResult, String expectedResult) throws AssertionFailedException {
    try {
      if (actualResult.equals(expectedResult)) {
        throw new AssertionFailedException(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (AssertionFailedException e) {
      System.out.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  public static <T extends Comparable<T>> void assertNotTrue (T actualResult, T expectedResult)  throws AssertionFailedException {
    try {
      if (actualResult != expectedResult) {
        throw new AssertionFailedException(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (AssertionFailedException e) {
      System.out.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  public static void assertNotTrue (String actualResult, String expectedResult) throws AssertionFailedException {
    try {
      if (!actualResult.equals(expectedResult)) {
        throw new AssertionFailedException(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (AssertionFailedException e) {
      System.out.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  public void runTestSuit() {
    // TODO: Run all tests and print pass/fail
  }
}
