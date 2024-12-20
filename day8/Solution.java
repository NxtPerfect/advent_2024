package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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
        // Find all the antenas
        // add them to "antenas"
        // if key exists, add to list of values
        // else create new entry
        //
        // Antena is any digit/number
        // so ignore "."
        // save everything else
        //
        // TODO: Y difference of antenas
        // should be ABS
        // but x difference doesn't need to be
        // as long as we keep the corners
        //
        // so actually, think about how to
        // keep correct orientation of antenas
        // maybe check if the first one is above
        // then it's legit?
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
    HashSet<Integer[]> createdAntenas = new HashSet<>();
    for (String antenaKey : antenas.keySet()) {
      for (Integer[] coordinates : antenas.get(antenaKey)) {
        System.out.println(coordinates[0] + " " + coordinates[1]);
        for (Integer[] otherCoordinates : antenas.get(antenaKey)) {
          if (otherCoordinates[0].equals(coordinates[0]) && otherCoordinates[1].equals(coordinates[1]))
            continue;
          // boolean isFirstAntenaAbove = otherCoordinates[1] > coordinates[1];
          int x_diff = otherCoordinates[0] - coordinates[0];
          int y_diff = Math.abs(otherCoordinates[1] - coordinates[1]);

          // TODO: we never check if coordinates is above otherCoordinates
          // we possibly create antenas inside them, not outside
          int newCoordinateX = coordinates[0] - x_diff;
          int newCoordinateY = coordinates[1] - y_diff;
          int newOtherCoordinateX = otherCoordinates[0] + x_diff;
          int newOtherCoordinateY = otherCoordinates[1] + y_diff;

          // TODO: if one out of bounds
          // then add other one
          if (newCoordinateX < 0
              || newCoordinateY < 0
              || newOtherCoordinateX > mapWidth
              || newOtherCoordinateY > mapHeight)
            continue;

          if (createdAntenas.contains(new Integer[] { newCoordinateX, newCoordinateY })
              && createdAntenas.contains(new Integer[] { newOtherCoordinateX, newOtherCoordinateY }))
            continue;
          if (createdAntenas.contains(new Integer[] { newCoordinateX, newCoordinateY })) {
            createdAntenas.add(new Integer[] { newOtherCoordinateX, newOtherCoordinateY });
            continue;
          }
          if (createdAntenas.contains(new Integer[] { newOtherCoordinateX, newOtherCoordinateY })) {
            createdAntenas.add(new Integer[] { newCoordinateX, newCoordinateY });
            continue;
          }
          createdAntenas.add(new Integer[] { newCoordinateX, newCoordinateY });
          createdAntenas.add(new Integer[] { newOtherCoordinateX, newOtherCoordinateY });
        }
      }
    }
    return createdAntenas.size();

  }

  int part2(String inputFilePath) {
    int result = 0;

    return result;
  }
}
