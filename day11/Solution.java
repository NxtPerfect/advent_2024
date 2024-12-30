package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.Tests;
import lib.AoC;
import lib.Day;

class Solution extends Day {
  void main() {
    int properResultPart1 = 55312; // TODO: change to part 1 sample data result
    int validationResultPart1 = part1("day11/test_input2.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    int resultPart1 = part1("day11/input.txt");
    System.out.println(resultPart1);

    long resultPart2 = part2("day11/input.txt");
    System.out.println(resultPart2);
  }

  int part1(String inputFilePath) {
    List<Long> stones = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {
        for (String stone : line.split("\s")) {
          stones.add(Long.parseLong(stone));
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    for (int i = 0; i < 25; i++) {
      List<Long> newStones = new ArrayList<>();
      for (int j = 0; j < stones.size(); j++) {
        Long stone = stones.get(j);
        if (stone.equals(0l)) {
          stones.set(j, 1l);
          continue;
        }
        int length = (int) (Math.log10(stone) + 1);
        if (length % 2 == 0) {
          Long newLeftStone = Long.parseLong(stone.toString().substring(0, length / 2));
          Long newRightStone = Long.parseLong(stone.toString().substring(length / 2));
          stones.set(j, newLeftStone);
          newStones.add(newRightStone);
          continue;
        }
        stones.set(j, stone * 2024);
      }
      for (Long newStone : newStones) {
        stones.add(newStone);
      }
    }

    return stones.size();
  }

  /*
   * Solution inspired by
   * https://youtu.be/WJ48BFHAtPY
   */
  long part2(String inputFilePath) {
    Long result = 0l;
    List<Long> stones = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {
        for (String stone : line.split("\s")) {
          stones.add(Long.parseLong(stone));
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // key = number
    // val = map -> key = blink amount, val = result
    Map<Long, Map<Integer, Long>> cache = new HashMap<>();

    for (Long stone : stones) {
      result += blink2(cache, 75, stone);
    }

    return result;
  }

  private long blink2(Map<Long, Map<Integer, Long>> cache, int iterations, long stone) {
    if (iterations == 0) {
      return 1;
    }

    Map<Integer, Long> stoneCache = cache.get(stone);
    if (stoneCache != null) {
      Long res = stoneCache.get(iterations);
      if (res != null) {
        return res;
      }
    }

    String stoneString = "" + stone;
    long result = 0;
    if (stone == 0l) {
      result = blink2(cache, iterations - 1, 1l);
    } else if (stoneString.length() % 2 == 0) {
      int middlePoint = stoneString.length() / 2;
      Long newLeftStone = Long.parseLong(stoneString.substring(0, middlePoint));
      Long newRightStone = Long.parseLong(stoneString.substring(middlePoint));
      result = blink2(cache, iterations - 1, newLeftStone) + blink2(cache, iterations - 1, newRightStone);
    } else {
      result = blink2(cache, iterations - 1, stone * 2024);
    }

    if (stoneCache == null) {
      stoneCache = new HashMap<>();
      cache.put(stone, stoneCache);
    }
    stoneCache.put(iterations, result);
    return result;
  }
}
