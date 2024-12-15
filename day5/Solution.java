package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import lib.Tests;
import lib.AoC;

public class Solution {
  void main() {
    int properResultPart1 = 143;
    int validationResultPart1 = part1("day5/test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    int resultPart1 = part1("day5/input.txt");
    System.out.println(resultPart1);

    int properResultPart2 = 123;
    int validationResultPart2 = part2("day5/test_input.txt");
    System.out.println(validationResultPart2);
    System.out.println(validationResultPart2 == properResultPart2);
    //
    // int resultPart2 = part2("day5/input.txt");
    // System.out.println(resultPart2);
  }

  /*
   * My idea is that i can create a hash map for each rule
   * where first number is key, and any value that needs
   * to be further down the line is a value
   * then i go over the update and check if the two values
   * are in ruleset, if they're not, continue
   * if they are, and the positions match. continue
   * if positons don't match. break and move to next update
   *
   * If update is valid, add up <b>middle value</b> to result
   */
  int part1(String inputFilePath) {
    int result = 0;

    HashMap<Integer, ArrayList<Integer>> rulesNumbers = new HashMap<Integer, ArrayList<Integer>>();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {

        if (line.contains("|")) {
          int[] numbersInLine = splitRuleLineToIntArray(line);

          if (rulesNumbers.get(numbersInLine[0]) != null) {
            ArrayList<Integer> newValueForKey = rulesNumbers.get(numbersInLine[0]);
            newValueForKey.add(numbersInLine[1]);
            rulesNumbers.put(numbersInLine[0], newValueForKey);
            line = br.readLine();
            continue;
          }

          rulesNumbers.put(numbersInLine[0], new ArrayList<Integer>(Arrays.asList(numbersInLine[1])));
          line = br.readLine();
          continue;
        }

        if (line.length() == 0) {
          line = br.readLine();
          continue;
        }

        int[] updateNumbersInLine = splitUpdateLineToIntArray(line);
        boolean isValidLine = true;
        for (int i = (updateNumbersInLine.length - 1); i > 0; i--) {
          for (int j = (i - 1); j >= 0; j--) {
            if (rulesNumbers.get(updateNumbersInLine[i]) == null)
              continue;
            if (rulesNumbers.get(updateNumbersInLine[i]).contains(updateNumbersInLine[j]))
              isValidLine = false;
          }
        }
        if (isValidLine) {
          int middleIndex = ((updateNumbersInLine.length + 2 - 1) / 2) - 1;
          result += updateNumbersInLine[middleIndex];
        }

        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return result;
  }

  int part2(String inputFilePath) {
    int result = 0;

    HashMap<Integer, ArrayList<Integer>> rulesNumbers = new HashMap<Integer, ArrayList<Integer>>();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {

        if (line.contains("|")) {
          int[] numbersInLine = splitRuleLineToIntArray(line);

          if (rulesNumbers.get(numbersInLine[0]) != null) {
            ArrayList<Integer> newValueForKey = rulesNumbers.get(numbersInLine[0]);
            newValueForKey.add(numbersInLine[1]);
            rulesNumbers.put(numbersInLine[0], newValueForKey);
            line = br.readLine();
            continue;
          }

          rulesNumbers.put(numbersInLine[0], new ArrayList<Integer>(Arrays.asList(numbersInLine[1])));
          line = br.readLine();
          continue;
        }

        if (line.length() == 0) {
          line = br.readLine();
          continue;
        }

        int[] updateNumbersInLine = splitUpdateLineToIntArray(line);
        ArrayList<Integer> swappedValues = new ArrayList<Integer>();
        boolean isValidLine = true;
        for (int i = (updateNumbersInLine.length - 1); i > 0; i--) {
          for (int j = 0; j < i; j++) {
            if (rulesNumbers.get(updateNumbersInLine[i]) == null)
              continue;
            // Now if we get into this situation, maybe we could swap the values
            // and check again if they're valid starting from the back?
            if (rulesNumbers.get(updateNumbersInLine[i]).contains(updateNumbersInLine[j])
                && !swappedValues.contains(updateNumbersInLine[j])) {
              int temp = updateNumbersInLine[i];
              updateNumbersInLine[i] = updateNumbersInLine[j];
              updateNumbersInLine[j] = temp;
              swappedValues.add(updateNumbersInLine[j]);
              i = (updateNumbersInLine.length);
              isValidLine = true;
              break;
            }
            if (rulesNumbers.get(updateNumbersInLine[i]).contains(updateNumbersInLine[j]))
              isValidLine = false;
          }
        }
        if (isValidLine) {
          int middleIndex = ((updateNumbersInLine.length + 2 - 1) / 2) - 1;
          result += updateNumbersInLine[middleIndex];
        }

        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return result;
  }

  private static int[] splitRuleLineToIntArray(String line) throws NumberFormatException {
    return Arrays.stream(line.split("\\|"))
        .mapToInt(Integer::parseInt)
        .toArray();
  }

  private static int[] splitUpdateLineToIntArray(String line) throws NumberFormatException {
    return Arrays.stream(line.split(","))
        .mapToInt(Integer::parseInt)
        .toArray();
  }
}
