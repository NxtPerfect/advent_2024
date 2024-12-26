package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lib.Tests;
import lib.AoC;
import lib.Day;

class Solution extends Day {
  void main() {
    int properResultPart1 = 1928;
    Long validationResultPart1 = part1("day9/test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

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

  long part1(String inputFilePath) {
    Long howManyTimes = 0l;
    List<String> input = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine().trim();
      Long id = 0l;

      while (line != null) {
        for (int i = 0; i < line.length(); i++) {
          if (i % 2 == 0) {
            howManyTimes = Long.parseLong(Character.toString(line.charAt(i)));
            for (int j = 0; j < howManyTimes; j++) {
              input.add(id.toString());
            }
            id++;
            continue;
          }
          howManyTimes = Long.parseLong(Character.toString(line.charAt(i)));
          for (int j = 0; j < howManyTimes; j++) {
            input.add(".");
          }
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // System.out.println(input.toString());

    // Find "." and replace with end
    int start = 0;
    int end = input.size() - 1;
    while (start < end) {
      if (!input.get(start).equals(".")) {
        start++;
        continue;
      }
      if (input.get(end).equals(".")) {
        end--;
        continue;
      }
      swap(input, start, end);
      start++;
      end--;
    }

    // System.out.println(input.toString());

    // remove elements from start -> input.size()
    // because they're all '.'
    start++;
    try {
      while (input.get(start) != null) {
        input.remove(start);
      }
    } catch (IndexOutOfBoundsException e) {
    }

    // System.out.println(input.toString());
    return checksum(input);
  }

  <T> void swap(List<T> list, Integer indexFirst, Integer indexSecond) {
    T temp = list.get(indexFirst);
    list.set(indexFirst, list.get(indexSecond));
    list.set(indexSecond, temp);
  }

  Long checksum(List<String> list) {
    Long result = 0l;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).equals("."))
        continue;
      result += i * Long.parseLong(list.get(i));
    }
    return result;
  }

  int part2(String inputFilePath) {
    int result = 0;

    return result;
  }
}
