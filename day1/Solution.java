package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
  void main(String[] args) {
    try {
      int part1CorrectResult = 11;
      int part1Validation = part1("test_input.txt");
      if (part1Validation != part1CorrectResult)
        throw new Exception("Part 1 isn't equal to " + part1CorrectResult + ", instead equal to " + part1Validation);
      System.out.println("Part 1 solution: " + part1("input.txt"));
    } catch (Exception e) {
      System.out.println(e);
      return;
    }

    try {
      int part2CorrectResult = 31;
      int part2Validation = part2("test_input.txt");
      if (part2Validation != part2CorrectResult)
        throw new Exception("Part 2 isn't equal to " + part2CorrectResult + ", instead equal to " + part2Validation);
      System.out.println("Part 2 solution: " + part2("input.txt"));
    } catch (Exception e) {
      System.out.println(e);
      return;
    }
  }

  void readFileToTwoArrayLists(String inputFilePath, ArrayList<Integer> left, ArrayList<Integer> right) {
    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {
        String[] splitString = line.split("\\s+");
        left.add(Integer.parseInt(splitString[0].strip()));
        right.add(Integer.parseInt(splitString[1].strip()));

        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  int part1(String inputFilePath) {
    // Read file
    ArrayList<Integer> left = new ArrayList<Integer>();
    ArrayList<Integer> right = new ArrayList<Integer>();

    readFileToTwoArrayLists(inputFilePath, left, right);

    // Sort
    left.sort(null);
    right.sort(null);

    // Calculate distance between each item in line
    int totalDistance = 0;
    for (int i = 0; i < left.size(); i++) {
      totalDistance += Math.abs(left.get(i) - right.get(i));
    }

    return totalDistance;
  }

  int part2(String inputFilePath) {
    ArrayList<Integer> left = new ArrayList<Integer>();
    ArrayList<Integer> right = new ArrayList<Integer>();

    readFileToTwoArrayLists(inputFilePath, left, right);

    int similiarityScore = 0;

    for (int i = 0; i < left.size(); i++) {
      int number = left.get(i);
      int occurances = Collections.frequency(right, number);
      similiarityScore += (occurances * number);
    }

    return similiarityScore;
  }
}
