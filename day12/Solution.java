package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lib.Tests;
import lib.AoC;
import lib.Day;
import lib.Pair;

class Solution extends Day {
  void main() {
    // final int properResultPart1 = 1930;
    final List<String[]> testInput = readInputToStringList("day12/test_input.txt");
    // final int validationResultPart1 = part1(testInput);
    // System.out.println(validationResultPart1 == properResultPart1);
    //
    // final List<String[]> input = readInputToStringList("day12/input.txt");
    // final int resultPart1 = part1(input);
    // System.out.println(resultPart1);
    //
    int properResultPart2 = 1206;
    final int validationResultPart2 = part2(testInput);
    System.out.println(validationResultPart2 == properResultPart2);

    // int resultPart2 = part2("day12/input.txt");
    // System.out.println(resultPart2);
  }

  protected static List<String[]> readInputToStringList(final String path) {
    try {
      final BufferedReader reader = new BufferedReader(new FileReader(path));
      final List<String[]> input = reader.lines().map(s -> s.split("")).collect(Collectors.toList());
      return input;
    } catch (final IOException e) {
      System.err.println(e);
      return null;
    }
  }

  // Use BFS with queue to go through elements
  // in good order
  int part1(final List<String[]> input) {
    int result = 0;
    final List<List<Pair<Integer, Integer>>> regions = new ArrayList<List<Pair<Integer, Integer>>>();
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length; j++) {
        List<Pair<Integer, Integer>> newRegion = new ArrayList<Pair<Integer, Integer>>();
        newRegion = bfs(new LinkedList<Pair<Integer, Integer>>(List.of(new Pair<>(i, j))), input,
            new ArrayList<Pair<Integer, Integer>>());

        Collections.sort(newRegion, new Comparator<Pair<Integer, Integer>>() {
          @Override
          public int compare(Pair<Integer, Integer> arg0, Pair<Integer, Integer> arg1) {
            if (arg0.getLeft().equals(arg1.getLeft()))
              return arg0.getRight().compareTo(arg1.getRight());
            return arg0.getLeft().compareTo(arg1.getLeft());
          };
        });

        if (regions.contains(newRegion)) {
          continue;
        }
        regions.add(newRegion);
      }
    }

    // System.out.println(regions.toString());

    System.out.println("Costs...");
    for (final List<Pair<Integer, Integer>> region : regions) {
      result += calculateCostOfRegion(region);
    }

    System.out.println("Part 1: " + result);
    return result;
  }

  List<Pair<Integer, Integer>> bfs(final Deque<Pair<Integer, Integer>> deque, final List<String[]> input,
      final List<Pair<Integer, Integer>> visited) {
    Pair<Integer, Integer> coords = new Pair<>(0, 0);
    while (!deque.isEmpty()) {
      coords = deque.removeLast();
      if (visited.contains(coords))
        continue;

      visited.add(coords);

      final String letter = input.get(coords.getLeft())[coords.getRight()];
      // System.out.println("Letter " + letter);
      // Left is y axis, right is x axis
      for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
          if (Math.abs(i) == Math.abs(j))
            continue;
          final int newY = coords.getLeft() + i;
          final int newX = coords.getRight() + j;

          if (newX < 0 || newX >= input.get(0).length || newY < 0 || newY >= input.size()
              || visited.contains(new Pair<>(newY, newX)) || deque.contains(new Pair<>(newY, newX)))
            continue;
          if (input.get(newY)[newX].equals(letter))
            deque.push(new Pair<>(newY, newX));
        }
      }
    }
    return visited;
  }

  int calculateCostOfRegion(final List<Pair<Integer, Integer>> region) {
    final int area = region.size();
    int perimeter = 0;
    for (final Pair<Integer, Integer> pair : region) {
      for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
          if (Math.abs(i) == Math.abs(j))
            continue;
          final Pair<Integer, Integer> neighbourCell = new Pair<>(pair.getLeft() + i, pair.getRight() + j);
          final boolean hasNeighbour = region.contains(neighbourCell);
          perimeter += hasNeighbour ? 0 : 1;
        }

      }
    }
    return area * perimeter;
  }

  // Should be mostly what part 1 is
  // but this time calculate how many sides each region has
  // which is equal to amount of corners
  // and use that instead of perimeter
  int part2(final List<String[]> input) {
    int result = 0;
    final List<List<Pair<Integer, Integer>>> regions = new ArrayList<List<Pair<Integer, Integer>>>();
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length; j++) {
        List<Pair<Integer, Integer>> newRegion = new ArrayList<Pair<Integer, Integer>>();
        newRegion = bfs(new LinkedList<Pair<Integer, Integer>>(List.of(new Pair<>(i, j))), input,
            new ArrayList<Pair<Integer, Integer>>());

        Collections.sort(newRegion, new Comparator<Pair<Integer, Integer>>() {
          @Override
          public int compare(Pair<Integer, Integer> arg0, Pair<Integer, Integer> arg1) {
            if (arg0.getLeft().equals(arg1.getLeft()))
              return arg0.getRight().compareTo(arg1.getRight());
            return arg0.getLeft().compareTo(arg1.getLeft());
          };
        });

        if (regions.contains(newRegion)) {
          continue;
        }
        regions.add(newRegion);
      }
    }

    // System.out.println(regions.toString());

    System.out.println("Costs...");
    for (final List<Pair<Integer, Integer>> region : regions) {
      result += calculateCostOfRegionPart2(region);
    }

    System.out.println("Part 2: " + result);
    return result;
  }

  int calculateCostOfRegionPart2(final List<Pair<Integer, Integer>> region) {
    final int area = region.size();
    int sides = 0;
    for (final Pair<Integer, Integer> pair : region) {
      int cellSides = 0;
      for (int i = -1; i <= 1; i++) {
        cellSides = 0;
        boolean nextSide = false;
        for (int j = -1; j <= 1; j++) {
          if (Math.abs(i) == Math.abs(j))
            continue;
          final Pair<Integer, Integer> neighbourCell = new Pair<>(pair.getLeft() + i, pair.getRight() + j);
          final boolean hasNeighbour = region.contains(neighbourCell);
          if (nextSide && hasNeighbour) {
            cellSides = 3;
            break;
          }
          cellSides += hasNeighbour ? 0 : 1;
          nextSide = hasNeighbour;
        }
        if (nextSide && cellSides == 3)
          break;
      }
      if (cellSides == 0) {
        for (int i = -1; i <= 1; i++) {
          for (int j = -1; j <= 1; j++) {
            // corners
            if (Math.abs(i) != Math.abs(j))
              continue;
            final Pair<Integer, Integer> neighbourCell = new Pair<>(pair.getLeft() + i, pair.getRight() + j);
            final boolean hasNeighbour = region.contains(neighbourCell);
            sides++;
          }
        }
      }
      sides += cellSides == 3 ? 1 : 0;
    }
    return area * sides;
  }
}
