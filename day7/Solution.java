package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

// import lib.Tests;
// import lib.AoC;

class Solution {
  void main() {
    int properResultPart1 = 3749;
    Long validationResultPart1 = part1("day7/test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    Long resultPart1 = part1("day7/input.txt");
    System.out.println(resultPart1);

    int properResultPart2 = 11387;
    Long validationResultPart2 = part2("day7/test_input.txt");
    System.out.println(validationResultPart2);
    System.out.println(validationResultPart2 == properResultPart2);

    Long resultPart2 = part2("day7/input.txt");
    System.out.println(resultPart2);
  }

  /*
   * My idea for this <i> problem </i> is to
   * make a dfs function, that gets a result
   * and then calls with either multiply or add
   * and if any of these return true
   * then we add test value to total
   */
  static Long part1(String inputFilePath) {
    Long result = 0L;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {
        Long target = Long.parseLong(line.split(":")[0]);

        long[] notPreparedLineValues = Arrays.stream(line.split("\\s"))
            .skip(1)
            .mapToLong(Long::parseLong)
            .toArray();
        Long[] lineValues = Arrays.stream(notPreparedLineValues)
            .boxed()
            .toArray(Long[]::new);

        if (dfs(target, lineValues, false)) {
          result += target;
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return result;
  }

  static boolean dfs(Long target, Long[] values, boolean isPart2) {
    if (values.length == 1 && values[0].equals(target)) {
      return true;
    }
    if (values.length == 1 || values[0] > target) {
      return false;
    }
    Long multipliedValue = values[0] * values[1];
    Long[] computedMultipliedValues = addToBeginningOfArray(Arrays.copyOfRange(values, 2, values.length),
        multipliedValue);

    Long addedValue = values[0] + values[1];
    Long[] computedAddedValues = addToBeginningOfArray(Arrays.copyOfRange(values, 2, values.length),
        addedValue);
    if (!isPart2)
      return dfs(target, computedMultipliedValues, false) || dfs(target, computedAddedValues, false);
    String stringConcatValue = values[0].toString() + values[1].toString();
    Long concatValue = Long.parseLong(stringConcatValue);
    Long[] computedConcatValues = addToBeginningOfArray(Arrays.copyOfRange(values, 2, values.length),
        concatValue);
    return dfs(target, computedMultipliedValues, true) || dfs(target, computedAddedValues, true)
        || dfs(target, computedConcatValues, true);

  }

  public static <T> T[] addToBeginningOfArray(T[] elements, T element) {
    T[] newArray = Arrays.copyOf(elements, elements.length + 1);
    newArray[0] = element;
    System.arraycopy(elements, 0, newArray, 1, elements.length);

    return newArray;
  }

  static Long part2(String inputFilePath) {
    Long result = 0L;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();

      while (line != null) {
        Long target = Long.parseLong(line.split(":")[0]);

        long[] notPreparedLineValues = Arrays.stream(line.split("\\s"))
            .skip(1)
            .mapToLong(Long::parseLong)
            .toArray();
        Long[] lineValues = Arrays.stream(notPreparedLineValues)
            .boxed()
            .toArray(Long[]::new);

        if (dfs(target, lineValues, true)) {
          result += target;
        }
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return result;
  }
}
