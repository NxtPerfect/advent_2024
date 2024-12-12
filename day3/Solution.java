package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
  void main() {
    int properResultPart1 = 161;
    int validationResultPart1 = part1("test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    int resultPart1 = part1("input.txt");
    System.out.println("Part1 result\n" + resultPart1);

    int properResultPart2 = 48;
    int validationResultPart2 = part2("test_input2.txt");
    System.out.println(validationResultPart2);
    System.out.println(validationResultPart2 == properResultPart2);

    int resultPart2 = part2("input.txt");
    System.out.println("Part2 result\n" + resultPart2);
  }

  static Integer part1(String inputFilePath) {
    int result = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
          int num1 = Integer.parseInt(matcher.group(1));
          int num2 = Integer.parseInt(matcher.group(2));
          result += (num1 * num2);
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return result;
  }

  static Integer part2(String inputFilePath) {
    int result = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      boolean isIgnored = false;
      while (line != null) {
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don\'t\\(\\)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          if (matcher.toString().contains("don't()")) {
            isIgnored = true;
            continue;
          }
          if (matcher.toString().contains("do()")) {
            isIgnored = false;
            continue;
          }
          if (isIgnored)
            continue;
          int num1 = Integer.parseInt(matcher.group(1));
          int num2 = Integer.parseInt(matcher.group(2));
          result += (num1 * num2);
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return result;
  }
}
