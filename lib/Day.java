package lib;

import java.util.Arrays;

public class Day {
  void main() {
    System.out.println("Day");
  }

  static int part1(final String inputFilePath) {
    return 0;
  }

  static int part2(final String inputFilePath) {
    return 0;
  }

  private static int[] splitLineToIntArray(final String line) {
    return Arrays.stream(line.split("\\s+"))
        .mapToInt(Integer::parseInt)
        .toArray();
  }
}
