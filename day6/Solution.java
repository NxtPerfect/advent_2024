package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import lib.Tests;
import lib.AoC;

class Solution {
  void main() {
    int properResultPart1 = 41;
    int validationResultPart1 = part1("test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    // int resultPart1 = part1("input.txt");
    // System.out.println(resultPart1);
    //
    //
    //
    // int properResultPart2 = 0; // TODO: change to part 2 sample data result
    // int validationResultPart2 = part2("test_input.txt");
    // System.out.println(validationResultPart2);
    // System.out.println(validationResultPart2 == properResultPart2);
    //
    // int resultPart2 = part2("input.txt");
    // System.out.println(resultPart2);
  }

  // when we go up, then we add to result our y pos
  // and substitute by the position of next obstacle in y
  // if we go right, then (current x pos - x pos of next element - 1)
  // - 1 because we don't want to count current box
  static int part1(String inputFilePath) {
    int result = 0;
    ArrayList<String> map = new ArrayList<String>(); // one element is one entire line
    ArrayList<Integer[]> obsctaleIndexes = new ArrayList<Integer[]>();
    Integer[] playerIndex = null;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine().strip();
      int j = 0;

      while (line != null) {
        map.add(line.strip());

        if (line.contains("#")) {
          for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '#') {
              obsctaleIndexes.add(new Integer[]{i, j}); // x, y
            }
            if (line.charAt(i) == '^') {
              playerIndex = new Integer[]{i, j};
            }
          }
        }
        j++;
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
