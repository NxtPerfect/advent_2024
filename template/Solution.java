package dayXX;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import lib.Tests;
import lib.AoC;
import lib.Day;

class Solution extends Day {
  void main() {
    int properResultPart1 = 0; // TODO: change to part 1 sample data result
    int validationResultPart1 = part1("dayXX/test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    int resultPart1 = part1("dayXX/input.txt");
    System.out.println(resultPart1);

    int properResultPart2 = 0; // TODO: change to part 2 sample data result
    int validationResultPart2 = part2("dayXX/test_input.txt");
    System.out.println(validationResultPart2);
    System.out.println(validationResultPart2 == properResultPart2);

    int resultPart2 = part2("dayXX/input.txt");
    System.out.println(resultPart2);
  }

  int part1(String inputFilePath) {
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

    System.out.println("Part 1: " + result);
    return result;
  }

  int part2(String inputFilePath) {
    int result = 0;

    System.out.println("Part 2: " + result);
    return result;
  }
}
