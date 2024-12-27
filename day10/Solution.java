package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lib.Tests;
import lib.AoC;
import lib.Day;

class Solution extends Day {
  void main() {
    // int properResultPart1 = 36;
    // int validationResultPart1 = part1("day10/test_input.txt");
    // System.out.println(validationResultPart1);
    // System.out.println(validationResultPart1 == properResultPart1);

    int testResultPart1 = part1("day10/test_input2.txt");
    System.out.println(testResultPart1);
    System.out.println(testResultPart1 == 3);

    // int resultPart1 = part1("day10/input.txt");
    // System.out.println(resultPart1);
    //
    //
    //
    // int properResultPart2 = 0; // TODO: change to part 2 sample data result
    // int validationResultPart2 = part2("day10/test_input.txt");
    // System.out.println(validationResultPart2);
    // System.out.println(validationResultPart2 == properResultPart2);
    //
    // int resultPart2 = part2("day10/input.txt");
    // System.out.println(resultPart2);
  }

  // BFS for every 0 found
  // must increase by 1 in height
  // score is the number of 9-height positions
  // reachable from that trailhead via a hiking trail
  int part1(String inputFilePath) {
    int result = 0;
    List<List<Integer>> input = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine().strip();
      int row = 0;

      while (line != null) {
        input.add(new ArrayList<>());
        for (int i = 0; i < line.length(); i++) {
          input.get(row).add(Character.getNumericValue(line.charAt(i)));
        }
        row++;
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // find all zeroes
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).size(); j++) {
        if (input.get(i).get(j) == 0) {
          result += dfs(input, new HashSet<>(), i, j);
        }
      }
    }

    return result;
  }

  int dfs(List<List<Integer>> input, Set<String> visited, int row, int col) {
    int cur = input.get(row).get(col);
    if (cur == 9)
      return 1;
    int res = 0;
    String pos = String.format("%d,%d", row, col);
    visited.add(pos);

    for (int[] direction : new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }) {
      int newRow = row + direction[0];
      int newCol = col + direction[1];
      String newPos = String.format("%d,%d", newRow, newCol);
      if (newRow < 0 || newRow >= input.size()
          || newCol < 0 || newCol >= input.get(row).size()
          || visited.contains(newPos) || input.get(newRow).get(newCol) != cur + 1)
        continue;
      // visited.add(newPos);
      // visited.add(rowCol);
      System.out.println(visited.toString());
      res += dfs(input, visited, newRow, newCol);
    }
    visited.remove(pos);

    return res;
  }

  int part2(String inputFilePath) {
    int result = 0;

    return result;
  }
}
