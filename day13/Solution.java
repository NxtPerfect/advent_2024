package day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.Day;

class Solution extends Day {
  void main() {
    int properResultPart1 = 480;
    List<long[]> testInput = readInputToStringList("day13/test_input.txt");
    long validationResultPart1 = part1(testInput);
    System.out.println(validationResultPart1 == properResultPart1);

    final List<long[]> inputPart1 = readInputToStringList("day13/input.txt");
    long resultPart1 = part1(inputPart1);
    // System.out.println(resultPart1 > 30899);

    final List<long[]> inputPart2 = readInputToStringList("day13/input.txt");
    long resultPart2 = part2(inputPart1);
    // System.out.println(resultPart2 > 30899);
  }

  protected static List<long[]> readInputToStringList(final String path) {
    long[] claw = new long[6];
    try {
      final List<String> fileInput = Files.readAllLines(Path.of(path));
      final Pattern pattern = Pattern.compile("(?:X[+=])(\\d+)(,\\s)(?:Y[+=])(\\d+)");
      final List<long[]> input = new ArrayList<long[]>();

      int i = 0;
      for (String line : fileInput) {
        final Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          if (line.strip().length() == 0 || matcher.group(1) == null || matcher.group(3) == null)
            break;
          claw[i++] = (Long.parseLong(matcher.group(1).strip()));
          claw[i++] = (Long.parseLong(matcher.group(3).strip()));
        }
        if (line.strip().length() == 0 || line == "\n") {
          input.add(claw);
          i = 0;
          claw = new long[6];
        }
      }
      input.add(claw);
      return input;
    } catch (final IOException e) {
      System.err.println(e);
      return null;
    }
  }

  // This is kind of like finding n*x+m*y=z
  // where x, y, and z are known
  long part1(List<long[]> input) {
    long result = 0;

    for (long[] claws : input) {
      final long aX = claws[0];
      final long aY = claws[1];
      final long bX = claws[2];
      final long bY = claws[3];
      final long prizeX = claws[4];
      final long prizeY = claws[5];

      final long numerator = prizeX * aY - prizeY * aX;
      final long b = numerator / (bX * aY - bY * aX);
      final long remX = prizeX - b * bX;
      final long l = aX == 0 ? prizeY : remX;
      final long r = aX == 0 ? aY : aX;
      final long a = l / r;
      result += (a * aY + b * bY == prizeY && l % r == 0) ? 3 * a + b : 0;
    }
    System.out.println("Part 1: " + result);
    return result;
  }

  // 10000000000000
  long part2(List<long[]> input) {
    long result = 0;

    for (long[] claws : input) {
      final long aX = claws[0];
      final long aY = claws[1];
      final long bX = claws[2];
      final long bY = claws[3];
      final long prizeX = claws[4] + 10000000000000l;
      final long prizeY = claws[5] + 10000000000000l;

      final long numerator = prizeX * aY - prizeY * aX;
      final long b = numerator / (bX * aY - bY * aX);
      final long remX = prizeX - b * bX;
      final long l = aX == 0 ? prizeY : remX;
      final long r = aX == 0 ? aY : aX;
      final long a = l / r;
      result += (a * aY + b * bY == prizeY && l % r == 0) ? 3 * a + b : 0;
    }
    System.out.println("Part 2: " + result);
    return result;
  }
}
