package lib;

/**
 * Tests
 * Class to give simple functions to help test code
 */
public class Tests {

  // If true returns, else throws an error
  public static void assertTrue (Number actualResult, Number expectedResult) {
    try {
      if (actualResult != expectedResult) {
        throw new Exception(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (Exception e) {
      System.out.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  // If true returns, else throws an error
  public static void assertTrue (String actualResult, String expectedResult) {
    try {
      if (actualResult.equals(expectedResult)) {
        throw new Exception(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (Exception e) {
      System.out.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  // If true returns, else throws an error
  public static void assertNotTrue (Number actualResult, Number expectedResult) {
    try {
      if (actualResult != expectedResult) {
        throw new Exception(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (Exception e) {
      System.out.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  // If true returns, else throws an error
  public static void assertNotTrue (String actualResult, String expectedResult) {
    try {
      if (!actualResult.equals(expectedResult)) {
        throw new Exception(actualResult + " not equal to " + expectedResult);
      }
      return;
    } catch (Exception e) {
      System.out.println("Test failed" + actualResult + " not equal to " + actualResult);
    }
  }

  public void runTestSuit() {
    // TODO: Run all tests and print pass/fail
  }
}
