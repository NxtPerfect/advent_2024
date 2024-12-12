package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import lib.Tests;
import lib.AoC;

class Solution {
  void main() {
    int properResultPart1 = 0; // TODO: change to part 1 sample data result
    int validationResultPart1 = part1("test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    int resultPart1 = part1("input.txt");
    System.out.println(resultPart1);



    int properResultPart2 = 0; // TODO: change to part 2 sample data result
    int validationResultPart2 = part2("test_input.txt");
    System.out.println(validationResultPart2);
    System.out.println(validationResultPart2 == properResultPart2);

    int resultPart2 = part2("input.txt");
    System.out.println(resultPart2);
  }

  static int part1(String inputFilePath) {
    int result = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {
        // TODO: do something with file reading
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return result;
  }

  static int part2(String inputFilePath) {
    int result = 0;

    return result;
  }

  private static int[] readFileLineToArray(String line) {
    return Arrays.stream(line.split("\\s+"))
        .mapToInt(Integer::parseInt) // TODO: Change if needing string
        .toArray();
  }
}
