package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import lib.Tests;
import lib.AoC;
import lib.Day;

class Solution extends Day {
  void main() {
    int properResultPart1 = 14;
    int validationResultPart1 = part1("day8/test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    // int resultPart1 = part1("day8/input.txt");
    // System.out.println(resultPart1);
    //
    //
    //
    // int properResultPart2 = 0; // TODO: change to part 2 sample data result
    // int validationResultPart2 = part2("day8/test_input.txt");
    // System.out.println(validationResultPart2);
    // System.out.println(validationResultPart2 == properResultPart2);
    //
    // int resultPart2 = part2("day8/input.txt");
    // System.out.println(resultPart2);
  }

  // The antinodes are created by going
  // distance between antena from the left
  // and from the right antena to the right
  //
  // actually get the y distance between antenas
  // then x distance
  // and go from there, if it's out of bounds, continue
  int part1(String inputFilePath) {
    int result = 0;
    int mapWidth = 0;
    int mapHeight = 0;
    int j = 0;
    HashMap<String, ArrayList<Integer[]>> antenas = new HashMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();
      mapWidth = line.length();

      while (line != null) {
        for (int i = 0; i < line.length(); i++) {
          String node = String.valueOf(line.charAt(i));
          if (node.equals("."))
            continue;
          if (antenas.keySet().contains(node)) {
            ArrayList<Integer[]> values = antenas.get(node);
            values.add(new Integer[] { i, j });
            antenas.put(node, values);
            continue;
          }
          ArrayList<Integer[]> newValues = new ArrayList<>();
          newValues.add(new Integer[] { i, j });
          antenas.put(node, newValues);
        }
        line = br.readLine();
        j++;
        mapHeight++;
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // TODO: for each element in antenas
    // compare with other elements in antenas

    // TODO: store already added new antenas
    // hash map of int[]?
    HashSet<List<Integer>> createdAntenas = new HashSet<>();
    for (String antenaKey : antenas.keySet()) {
      for (Integer[] coordinates : antenas.get(antenaKey)) {
        System.out.println(coordinates[0] + " " + coordinates[1]);
        for (Integer[] otherCoordinates : antenas.get(antenaKey)) {
          if (otherCoordinates[0].equals(coordinates[0]) && otherCoordinates[1].equals(coordinates[1]))
            continue;
          boolean isFirstAntenaAbove = otherCoordinates[1] > coordinates[1];
          int x_diff = otherCoordinates[0] - coordinates[0];
          int y_diff = isFirstAntenaAbove ? otherCoordinates[1] - coordinates[1] : coordinates[1] - otherCoordinates[1];

          int newCoordinateX = coordinates[0] - x_diff;
          int newCoordinateY = coordinates[1] - y_diff;
          int newOtherCoordinateX = otherCoordinates[0] + x_diff;
          int newOtherCoordinateY = otherCoordinates[1] + y_diff;
          if (!isFirstAntenaAbove) {
            int temp = newCoordinateX;
            newCoordinateX = newOtherCoordinateX;
            newOtherCoordinateX = temp;

            temp = newCoordinateY;
            newCoordinateY = newOtherCoordinateY;
            newOtherCoordinateY = temp;
          }

          List<Integer> coords = List.of((Integer) newCoordinateX, (Integer) newCoordinateY);
          List<Integer> otherCoords = List.of((Integer) newOtherCoordinateX, (Integer) newOtherCoordinateY);

          if (newCoordinateX < 0
              || newCoordinateY < 0
                  && (newOtherCoordinateX < mapWidth
                      && newOtherCoordinateY < mapHeight)) {
            duplicateSafeAddNewAntenas(createdAntenas, otherCoords);
            continue;
          }

          if (newCoordinateX >= 0
              || newCoordinateY >= 0
                  && (newOtherCoordinateX >= mapWidth
                      || newOtherCoordinateY >= mapHeight)) {
            duplicateSafeAddNewAntenas(createdAntenas, coords);
            continue;
          }

          if (newCoordinateX >= 0
              || newCoordinateY >= 0
              || newOtherCoordinateX >= mapWidth
              || newOtherCoordinateY >= mapHeight) {
            continue;
          }

          duplicateSafeAddNewAntenas(createdAntenas, coords);
          duplicateSafeAddNewAntenas(createdAntenas, otherCoords);
        }
      }
    }

    for (List<Integer> cAntenas : createdAntenas) {
      System.out.println(cAntenas.get(0).toString() + ", " + cAntenas.get(1).toString());
    }
    return createdAntenas.size();

  }

  private void duplicateSafeAddNewAntenas(HashSet<List<Integer>> createdAntenas, List<Integer> newCords) {
    if (createdAntenas.contains(newCords))
      return;
    createdAntenas.add(newCords);
  }

  int part2(String inputFilePath) {
    int result = 0;

    return result;
  }
}
