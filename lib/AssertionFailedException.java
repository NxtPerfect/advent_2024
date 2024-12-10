package lib;

public class AssertionFailedException extends Exception {
  public AssertionFailedException() {}
  public AssertionFailedException(String message) {
    super(message);
  }
}

