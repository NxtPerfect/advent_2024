package day6;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

import lib.Tests;
import lib.AoC;

class Solution {
  void main() {
    int properResultPart1 = 41;
    int validationResultPart1 = part1("day6/test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    // int resultPart1 = part1("day6/input.txt");
    // System.out.println(resultPart1);
    //
    //
    //
    // int properResultPart2 = 0; // TODO: change to part 2 sample data result
    // int validationResultPart2 = part2("day6/test_input.txt");
    // System.out.println(validationResultPart2);
    // System.out.println(validationResultPart2 == properResultPart2);
    //
    // int resultPart2 = part2("day6/input.txt");
    // System.out.println(resultPart2);
  }

  static final HashMap<Integer, ArrayList<Integer>> insertIntoHashMapOrAppendIfExists(
      HashMap<Integer, ArrayList<Integer>> map,
      Integer key,
      Integer val) {
    if (map.get(key) != null) {
      ArrayList<Integer> existingValues = map.get(key);
      existingValues.add(val);
      map.put(key, existingValues);
      // append
      return map;
    }
    map.put(key, new ArrayList<Integer>(Arrays.asList(val)));
    return map;
  }

  // static final HashMap<Integer, ArrayList<Integer>>
  // insertIntoHashMapOrAppendIfExists(
  // HashMap<Integer, ArrayList<Integer>> map,
  // int key,
  // int[] val) {
  // if (map.get(key) != null) {
  // ArrayList<Integer> existingValues = map.get(key);
  // for (int v : val) {
  // existingValues.add(v);
  // }
  // map.put(key, existingValues);
  // // append
  // return map;
  // }
  // ArrayList<Integer> newValues = new ArrayList<Integer>(Arrays.asList(val));
  // map.put(key, new ArrayList<Integer>(Arrays.asList(val)));
  // return map;
  // }
  //
  // when we go up, then we add to result our y pos
  // and substitute by the position of next obstacle in y
  // if we go right, then (current x pos - x pos of next element - 1)
  // - 1 because we don't want to count current box
  static int part1(String inputFilePath) {
    int result = 0;
    ArrayList<String> map = new ArrayList<String>(); // one element is one entire line
    HashMap<Integer, ArrayList<Integer>> obstaclesYIndexesForX = new HashMap<Integer, ArrayList<Integer>>();
    HashMap<Integer, ArrayList<Integer>> obstaclesXIndexesForY = new HashMap<Integer, ArrayList<Integer>>();
    Integer[] startingPlayerPos = null;

    // starting from going to the top, then clockwise
    Integer[][] moveDirections = new Integer[][] { { 0, -1 }, { 1, 0 }, { 0, 1 }, { 1, 0 } };

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine().strip();
      int j = 0;

      while (line != null) {
        map.add(line.strip());

        if (line.contains("#")) {
          for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '#') {
              obstaclesYIndexesForX = insertIntoHashMapOrAppendIfExists(obstaclesYIndexesForX, j, i);
              obstaclesXIndexesForY = insertIntoHashMapOrAppendIfExists(obstaclesXIndexesForY, i, j);
            }
            if (line.charAt(i) == '^') {
              startingPlayerPos = new Integer[] { i, j };
            }
          }
        }
        j++;
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    int playerX = startingPlayerPos[0];
    int playerY = startingPlayerPos[1];
    int directionIndex = 0;
    // X as key, Y as value
    HashMap<Integer, ArrayList<Integer>> visited = new HashMap<Integer, ArrayList<Integer>>();

    // from hashmap get indexes of obstacles in path
    // then check it's position - cur position on axis
    // if value is < 0, then continue
    // if > 0, get the min of all results
    // and pick that one as the obstacle to go to
    //
    // We also need to check if the square was already visited
    //
    // In visited hashmap, i already store all the tiles visited
    // so just return length of that hashmap,
    // or rather how many elements it has
    while (playerX > 0 && playerX < map.get(0).length() && playerY > 0 && playerY < map.size()) {
      int realDirectionIndex = directionIndex % 4;
      boolean isLeftGoing = realDirectionIndex % 4 == 3;
      boolean isRightGoing = realDirectionIndex % 4 == 1;
      boolean isTopGoing = realDirectionIndex % 4 == 0;
      boolean isBottomGoing = realDirectionIndex % 4 == 2;

      // If no obstacle found on current path
      if (obstaclesYIndexesForX.get(playerX) == null &&
          (isRightGoing || isLeftGoing)) {
        for (int k = playerX; k < map.get(0).length(); k += moveDirections[realDirectionIndex][0]) {
          visited = insertIntoHashMapOrAppendIfExists(visited, k, playerY);
        }
        return visited.size();
      }
      // Going out of tiles on Y
      if (obstaclesXIndexesForY.get(playerY) == null &&
          (isTopGoing || isBottomGoing)) {
        for (int k = playerY; k < map.get(0).length(); k += moveDirections[realDirectionIndex][1]) {
          visited = insertIntoHashMapOrAppendIfExists(visited, playerX, k);
        }
        return visited.size();
      }

      // If went left or right and got obstacles
      ArrayList<Integer> obstaclesInXPath = isRightGoing || isLeftGoing
          ? obstaclesXIndexesForY.get(playerY)
          : new ArrayList<Integer>();
      int smallestDistance = Integer.MAX_VALUE;
      int indexOfClosestObstacle = Integer.MAX_VALUE;
      for (Integer xIndex : obstaclesInXPath) {
        if ((xIndex > playerX && isLeftGoing) ||
            xIndex < playerX && isRightGoing) {
          continue;
        }
        int temp = (xIndex - playerX) / moveDirections[realDirectionIndex][0];
        if (temp < smallestDistance && temp > 0) {
          smallestDistance = temp;
          indexOfClosestObstacle = xIndex;
        }
      }

      ArrayList<Integer> obstaclesInYPath = isTopGoing || isBottomGoing
          ? obstaclesYIndexesForX.get(playerX)
          : new ArrayList<Integer>();
      for (Integer yIndex : obstaclesInYPath) {
        if ((yIndex > playerY && isTopGoing) ||
            yIndex < playerY && isBottomGoing) {
          continue;
        }
        int temp = (yIndex - playerY) / moveDirections[realDirectionIndex][1];
        if (temp < smallestDistance && temp > 0) {
          smallestDistance = temp;
          indexOfClosestObstacle = yIndex;
        }
      }

      // add all the indexes to visited hashmap
      // between player and obstacle
      //
      // What if indexOfClosestObstacle is MAX_VALUE, as we found none?
      if (isTopGoing) {
        System.out.println(playerY + " " + indexOfClosestObstacle);
        int[] closedRange = new int[] {};
        if (indexOfClosestObstacle != Integer.MAX_VALUE)
          closedRange = IntStream.rangeClosed(indexOfClosestObstacle, playerY).toArray();
        else
          closedRange = IntStream.rangeClosed(0, playerY).toArray();
        for (Integer y : closedRange) {
          visited = insertIntoHashMapOrAppendIfExists(visited, playerX, y);
        }
        directionIndex++;
        playerY = indexOfClosestObstacle - 1;
        continue;
      }
      if (isBottomGoing) {
        int[] closedRange = new int[] {};
        if (indexOfClosestObstacle != Integer.MAX_VALUE)
          closedRange = IntStream.rangeClosed(playerY, indexOfClosestObstacle).toArray();
        else
          closedRange = IntStream.rangeClosed(playerY, map.size() - 1).toArray();
        for (Integer x : closedRange) {
          visited = insertIntoHashMapOrAppendIfExists(visited, playerX, x);
        }
        directionIndex++;
        playerY = indexOfClosestObstacle + 1;
        continue;
      }
      if (isLeftGoing) {
        for (int i = playerX; i > indexOfClosestObstacle; i += moveDirections[realDirectionIndex][0]) {
          visited = insertIntoHashMapOrAppendIfExists(visited, i, playerY);
        }
        directionIndex++;
        playerX = indexOfClosestObstacle - 1;
        continue;
      }
      if (isRightGoing) {
        for (int i = playerX; i < indexOfClosestObstacle; i += moveDirections[realDirectionIndex][0]) {
          visited = insertIntoHashMapOrAppendIfExists(visited, i, playerY);
        }
        directionIndex++;
        playerX = indexOfClosestObstacle + 1;
        continue;
      }
      // go in direction until we find "#"
      // then increment direction
      // add to result the path we took
      // continue
      //
      // Or maybe optimized solution
      // go up, and if there's an obstacle at our y
      // then get that obstacle's y, and add to result
      // current y pos - obstacle's y - 1
      // if (obsctaleIndexes.contains(new Integer[] { playerX, playerY })) {
      // directionIndex++;
      // }
    }
    // if we go out of bounds
    // add last path to result
    // return

    return visited.size();
  }

  static int part2(String inputFilePath) {
    int result = 0;

    return result;
  }
}
