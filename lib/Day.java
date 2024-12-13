package lib;

import java.util.Arrays;

public class Day {
  void main() {
    System.out.println("Day");
  }

  int part1(String inputFilePath) {
    return 0;
  }

  int part2(String inputFilePath) {
    return 0;
  }

  private static int[] readFileLineToIntArray(String line) {
    return Arrays.stream(line.split("\\s+"))
        .mapToInt(Integer::parseInt)
        .toArray();
  }
}
