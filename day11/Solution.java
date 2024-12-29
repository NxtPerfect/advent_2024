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
    // int properResultPart1 = 55312; // TODO: change to part 1 sample data result
    // int validationResultPart1 = part1("day11/test_input2.txt");
    // System.out.println(validationResultPart1);
    // System.out.println(validationResultPart1 == properResultPart1);
    //
    // int resultPart1 = part1("day11/input.txt");
    // System.out.println(resultPart1);
    //
    Long resultPart2 = part2("day11/input.txt");
    System.out.println(resultPart2);
  }

  // If stone == 0 then stone = 1
  // if stone.length() % 2 == 0 then stone.split(middle)
  // else stone *= 2024
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

    // System.out.println(stones.toString());

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

  Long part2(String inputFilePath) {
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

    // System.out.println(stones.toString());

    // Iterate each stone 75 times
    // then add to result
    // do same for temp stones
    // so probably anytime i add to it
    // i should just let it wait
    // and focus on first stone
    // in temp stones
    Map<Long, Long[]> cache = new HashMap<>();

    for (int i = 0; i < stones.size(); i++) {
      Long stone = stones.get(i);
      System.out.println("Stone " + i + " out of " + stones.size());
      // List<Long> tempStones = new ArrayList<>(List.of(stone));

      for (int k = 0; k < 75; k++) {
        // for (int j = 0; j < tempStones.size(); j++)
        // stone = tempStones.get(j);
        if (cache.get(stone) != null) {
          // tempStones.set(j, cache.get(stone)[0]);
          if (cache.get(stone).length == 2) {
            // tempStones.add(cache.get(stone)[1]);
            stones.add(cache.get(stone)[1]);
          }
          stone = cache.get(stone)[0];
          continue;
        }
        if (stone.equals(0l)) {
          // tempStones.set(j, 1l);
          cache.put(stone, new Long[] { 1l });
          stone = 1l;
          continue;
        }
        int length = (int) (Math.log10(stone) + 1);
        if (length % 2 == 0) {
          Long newLeftStone = Long.parseLong(stone.toString().substring(0, length / 2));
          Long newRightStone = Long.parseLong(stone.toString().substring(length / 2));
          // tempStones.set(j, newLeftStone);
          // tempStones.add(newRightStone);
          cache.put(stone, new Long[] { newLeftStone, newRightStone });
          stone = newLeftStone;
          continue;
        }
        // tempStones.set(j, stone * 2024);
        cache.put(stone, new Long[] { stone * 2024 });
        stone *= 2024l;
      }
      result++;
      stones.removeFirst();
      i--;
      // System.out.println(cache.size());
      // System.out.println(result);
    }
    // result += tempStones.size();

    return result;
  }
}
