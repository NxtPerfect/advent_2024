package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;

import lib.Tests;
import lib.AoC;
import lib.Day;

class Solution extends Day {
  void main() {
    int properResultPart1 = 1928;
    Long validationResultPart1 = part1("day9/test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);
    //
    // Long validationResultTest2 = part1("day9/test_input2.txt");
    // System.out.println(validationResultTest2);
    // System.out.println(validationResultTest2 == 134);

    Long resultPart1 = part1("day9/input.txt");
    System.out.println(resultPart1);
    System.out.println(resultPart1 > 92697854996l);

    // int properResultPart2 = 0; // TODO: change to part 2 sample data result
    // int validationResultPart2 = part2("day9/test_input.txt");
    // System.out.println(validationResultPart2);
    // System.out.println(validationResultPart2 == properResultPart2);
    //
    // int resultPart2 = part2("day9/input.txt");
    // System.out.println(resultPart2);
  }

  /*
   * Read char in line
   * if i % 2 == 1:
   * insert $id * line.charAt(i) -> if it's 2, then inserts
   * 00 if id is 0
   * if i % 2 == 0:
   * insert '.' * line.charAt(i)
   *
   * After we finish reading input
   * get two pointers, one in the start, one in the end of string
   * then if input.charAt(start) != '.' start++; continue;
   * else input.charAt(start) = input.charAt(end) start++; end++;
   *
   * Finally to get the result
   * multiply current input by the index in string
   * "00998" -> 0 * 0 + 1 * 0 + 2 * 9 + 3 * 9 + 4 * 8...
   */
  long part1(String inputFilePath) {
    Long result = 0l;
    StringBuilder input = new StringBuilder();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine().trim();
      Long id = 0l;

      while (line != null) {
        for (int i = 0; i < line.length(); i++) {
          if (i % 2 == 0) {
            input.append(String.join("",
                Collections.nCopies(Integer.parseInt(Character.toString(line.charAt(i))), id.toString())));
            id++;
            continue;
          }
          input.append(String.join("", Collections.nCopies(Integer.parseInt(Character.toString(line.charAt(i))), ".")));
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // System.out.println(input.toString());

    Integer start = 0;
    Integer end = input.length() - 1;
    while (start < end) {
      if (input.charAt(start) != '.') {
        start++;
        continue;
      }
      if (input.charAt(end) == '.') {
        end--;
        continue;
      }

      input.replace(start, ++start, Character.toString(input.charAt(end--)));
    }
    input.replace(++end, input.length(), String.join("", Collections.nCopies(input.length() - end, ".")));

    // System.out.println(input.toString());

    // Save what was the last value
    // if it was 9, then digits = 2
    // if 99 digits = 3
    // etc.
    Integer lastVal = 0;
    Integer digits = 1;
    for (int i = 0; i < end; i++) {
      switch (lastVal) {
        case 9:
          digits = input.substring(i, i + 2).equals("10") ? 2 : 1;
          break;
        case 99:
          digits = input.substring(i, i + 3).equals("100") ? 3 : 2;
          break;
        case 999:
          digits = input.substring(i, i + 4).equals("1000") ? 4 : 3;
          break;
        case 9999:
          digits = input.substring(i, i + 5).equals("10000") ? 5 : 4;
          break;
        case 99999:
          digits = input.substring(i, i + 6).equals("100000") ? 6 : 5;
          break;
        case 999999:
          digits = input.substring(i, i + 7).equals("1000000") ? 7 : 6;
          break;
        default:
          digits = 1;
          break;
      }

      StringBuilder value = new StringBuilder();
      for (int j = 0; j < digits; j++) {
        value.append(String.valueOf(input.charAt(i + j)));
      }
      if (input.charAt(i) == '.')
        break;
      // System.out.println(value);
      Integer intValue = Integer.parseInt(value.toString());
      result += i * intValue;
      lastVal = intValue;
    }

    return result;
  }

  int part2(String inputFilePath) {
    int result = 0;

    return result;
  }
}
