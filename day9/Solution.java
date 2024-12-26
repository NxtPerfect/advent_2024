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
    // int properResultPart1 = 1928;
    // Long validationResultPart1 = part1("day9/test_input.txt");
    // System.out.println(validationResultPart1);
    // System.out.println(validationResultPart1 == properResultPart1);
    //
    // Long resultPart1 = part1("day9/input.txt");
    // System.out.println(resultPart1);
    //
    int properResultPart2 = 2858;
    Long validationResultPart2 = part2("day9/test_input.txt");
    System.out.println(validationResultPart2);
    System.out.println(validationResultPart2 == properResultPart2);

    // Long resultPart2 = part2("day9/input.txt");
    // System.out.println(resultPart2);
  }

  Long part1(String inputFilePath) {
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

  // Move the values from right to left
  // to the first slot they fit as a whole
  // only move them to the left
  Long part2(String inputFilePath) {
    Integer howManyTimes = 0;
    List<String> input = new ArrayList<>();
    List<List<Integer>> chunks = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine().trim();
      Integer id = 0;

      while (line != null) {
        for (int i = 0; i < line.length(); i++) {
          if (i % 2 == 0) {
            howManyTimes = Integer.parseInt(Character.toString(line.charAt(i)));

            // adds chunk id, index, length
            chunks.add(new ArrayList<>(List.of(new Integer[] { id, input.size(), howManyTimes })));

            for (int j = 0; j < howManyTimes; j++) {
              input.add(id.toString());
            }
            id++;
            continue;
          }
          howManyTimes = Integer.parseInt(Character.toString(line.charAt(i)));
          for (int j = 0; j < howManyTimes; j++) {
            input.add(".");
          }
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println(chunks.toString());

    // TODO: Move values to the left where they fit
    // as a whole
    List<List<Integer>> finalChunks = new ArrayList<>();

    for (List<Integer> rightChunkInfo : chunks.reversed()) {
      Integer rightChunkLength = rightChunkInfo.get(2);
      // TODO: Check where fits, move there
      for (int i = 0; i < rightChunkInfo.get(1) && i < chunks.size() - 1; i++) {
        List<Integer> firstLeftChunkInfo = chunks.get(i);
        Integer firstIndexPlusLength = firstLeftChunkInfo.get(1) + firstLeftChunkInfo.get(2);

        List<Integer> secondLeftChunkInfo = chunks.get(i + 1);
        Integer secondIndex = secondLeftChunkInfo.get(1);

        if (Math.abs(secondIndex - firstIndexPlusLength) < rightChunkLength)
          continue;

        // TODO: change index of right chunk
        firstLeftChunkInfo.set(2, firstIndexPlusLength + rightChunkLength);
        rightChunkInfo.set(1, firstIndexPlusLength + 1);
        // TODO: Sort list by chunk's index
        // chunks.sort((a, b) -> a.get(1).compareTo(b.get(1)));
        // actually, add to final chunks
        finalChunks.add(firstLeftChunkInfo);
        finalChunks.add(rightChunkInfo);
        finalChunks.add(secondLeftChunkInfo);
      }
    }

    System.out.println(finalChunks.toString());

    // System.out.println(input.toString());
    return checksum(input);
  }
}
