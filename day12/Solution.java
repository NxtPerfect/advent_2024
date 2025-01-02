package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lib.Tests;
import lib.AoC;
import lib.Day;

class Solution extends Day {
  void main() {
    int properResultPart1 = 1930;
    List<String[]> testInput = readInputToStringList("day12/test_input.txt");
    int validationResultPart1 = part1(testInput);
    System.out.println(validationResultPart1 == properResultPart1);
    //
    // int resultPart1 = part1("day12/input.txt");
    // System.out.println(resultPart1);
    //
    // int properResultPart2 = 0; // TODO: change to part 2 sample data result
    // int validationResultPart2 = part2("day12/test_input.txt");
    // System.out.println(validationResultPart2);
    // System.out.println(validationResultPart2 == properResultPart2);
    //
    // int resultPart2 = part2("day12/input.txt");
    // System.out.println(resultPart2);
  }

  protected static List<String[]> readInputToStringList(String path) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(path));
      List<String[]> input = reader.lines().map(s -> s.split("")).collect(Collectors.toList());
      return input;
    } catch (IOException e) {
      System.err.println(e);
      return null;
    }
  }

  /*
   * 1. Get regions of plants
   * - the same type must be touching
   * - try to get the most plants that
   * are horizontally or vertically touching
   * not diagonally
   * - How to find regions
   * dfs/bfs with parameters of last letter
   * current region and input
   * then if it's touching, add to region
   * if not, return region
   * - Could hold region's points in hashset
   * with row + col
   * but then, there's no way to tell
   * what's the perimeter or the area
   * of said region
   * 2. calculate their area and perimeter
   * 3. multiply that to get price
   * 4. add up to result
   */
  int part1(List<String[]> input) {
    int result = 0;
    for (String[] strings : input) {
      for (int i = 0; i < strings.length; i++) {
        System.out.print(strings[i]);
      }
      System.out.println();
    }

    System.out.println("Part 1: " + result);
    return result;
  }

  @Override
  protected int part2(String inputFilePath) {
    int result = 0;

    System.out.println("Part 2: " + result);
    return result;
  }
}
