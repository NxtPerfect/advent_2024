package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lib.Tests;
import lib.AoC;
import lib.Day;
import lib.Pair;

class Solution extends Day {
  void main() {
    final int properResultPart1 = 12;
    final int validationResultPart1 = part1("day14/test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);
    //
    // int resultPart1 = part1("day14/input.txt");
    // System.out.println(resultPart1);
    //
    // int properResultPart2 = 0; // TODO: change to part 2 sample data result
    // int validationResultPart2 = part2("day14/test_input.txt");
    // System.out.println(validationResultPart2);
    // System.out.println(validationResultPart2 == properResultPart2);
    //
    // int resultPart2 = part2("day14/input.txt");
    // System.out.println(resultPart2);
  }

  @Override
  protected int part1(final String inputFilePath) {
    int result = 0;
    final int width = 11; // 101
    final int height = 7; // 103
    final int steps = 5;

    final int[] quadrantCount = new int[4];

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();
      final Pattern pattern = Pattern.compile("^(p=)(\\d),(\\d)\\s(v=)(-?\\d),(-?\\d)$");

      while (line != null) {
        final String[] lineSplit = line.split("\\s");
        final Matcher matcher = pattern.matcher(line);
        Pair<Integer, Integer> pos = new Pair<Integer, Integer>(0, 0);
        Pair<Integer, Integer> vec = new Pair<Integer, Integer>(0, 0);
        while (matcher.find()) {
          if (line.strip().length() == 0 || matcher.group(2) == null || matcher.group(3) == null
              || matcher.group(5) == null || matcher.group(6) == null)
            break;
          pos = new Pair<Integer, Integer>(Integer.parseInt(matcher.group(2).strip()),
              Integer.parseInt(matcher.group(3).strip()));
          vec = new Pair<Integer, Integer>(Integer.parseInt(matcher.group(5).strip()),
              Integer.parseInt(matcher.group(6).strip()));
        }

        int newX = (pos.getLeft() + vec.getLeft() * steps) % width;
        int newY = (pos.getRight() + vec.getRight() * steps) % height;
        if (newX < 0)
          newX += width;
        if (newY < 0)
          newY += height;
        // check in which quarter it is

        System.out.println(newX + " " + newY);
        if (newX == width / 2 || newY == height / 2) {
          line = br.readLine();
          System.out.println("Skip");
          continue;
        }
        final int index = (newX < width / 2 ? 0 : 1) + 2 * (newY < height / 2 ? 0 : 1);
        System.out.println(index);
        quadrantCount[index]++;

        line = br.readLine();
      }
    } catch (final Exception e) {
      System.out.println(e);
    }

    for (int i = 0; i < quadrantCount.length; i++) {
      System.out.print(quadrantCount[i]);
    }
    System.out.println();
    result = Arrays.stream(quadrantCount).reduce(1, (prev, next) -> prev * next);
    System.out.println("Part 1: " + result);
    return result;
  }

  @Override
  protected int part2(final String inputFilePath) {
    final int result = 0;

    System.out.println("Part 2: " + result);
    return result;
  }
}
