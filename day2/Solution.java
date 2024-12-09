package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {

  int part1(String inputFilePath) {
    int safeReports = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {
        boolean isAscending = false;
        boolean isSafe = true;

        int[] levels = readLineToIntArray(line);
        int num1 = levels[0], num2 = levels[1];

        if (num1 < num2) {
          isAscending = true;
        }

        for (int i = 0; i < levels.length - 1; i++) {
          num1 = levels[i];
          num2 = levels[i + 1];
          int diff = num1 - num2;
          if (isAscending) {
            diff = num2 - num1;
          }
          if (diff < 1 || diff > 3) {
            isSafe = false;
            break;
          }
        }
        if (isSafe)
          safeReports++;

        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return safeReports;
  }

  int part2(String inputFilePath) { // too high
    int safeReports = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {
        boolean isValidLine = false;

        int[] levels = readLineToIntArray(line);
        for (int i = 0; i < levels.length; i++) {
          if (checkIsValidArrayWithoutIndex(levels, i)) {
            isValidLine = true;
            break;
          }
        }

        if (isValidLine)
          safeReports++;

        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return safeReports;
  }

  private static int[] readLineToIntArray(String line) {
    return Arrays.stream(line.split("\\s+"))
        .mapToInt(Integer::parseInt)
        .toArray();
  }

  private static boolean checkIsValidArrayWithoutIndex(int[] levels, int index) {
    int[] newLevels = IntStream.range(0, levels.length)
        .filter(i -> i != index)
        .map(i -> levels[i])
        .toArray();

    boolean isAscending = newLevels[0] < newLevels[1];
    for (int j = 0; j < newLevels.length - 1; j++) {
      int num1 = newLevels[j];
      int num2 = newLevels[j + 1];
      int diff = num1 - num2;
      if (isAscending) {
        diff = num2 - num1;
      }

      if (diff < 1 || diff > 3)
        return false;
    }
    return true;
  }

  void main(String[] args) {
    int validationResultPart1 = part1("test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == 2);

    int resultPart1 = part1("input.txt");
    System.out.println(resultPart1);

    int validationResultPart2 = part2("test_input.txt");
    System.out.println(validationResultPart2);
    System.out.println(validationResultPart2 == 4);

    int resultPart2 = part2("input.txt");
    System.out.println("Not equal to 580, because that's too high " +
        (resultPart2 < 580));
    System.out.println(resultPart2);
  }
}
