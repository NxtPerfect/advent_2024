package lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day {
  void main() {
    System.out.println("Day");
  }

  protected int part1(final String inputFilePath) {
    int result = 0;
    System.out.println("Part 1" + result);
    return 0;
  }

  protected int part2(final String inputFilePath) {
    int result = 0;
    System.out.println("Part 2" + result);
    return 0;
  }

  protected static int[] splitLineToIntArray(final String line) {
    return Arrays.stream(line.split("\\s+"))
        .mapToInt(Integer::parseInt)
        .toArray();
  }

  // protected static List<String> readInputToStringList(String path) {
  // try {
  // List<String> input = Files.readAllLines(Paths.get(path),
  // StandardCharsets.UTF_8);
  // return input;
  // } catch (IOException e) {
  // System.err.println(e);
  // return null;
  // }
  // }
}
