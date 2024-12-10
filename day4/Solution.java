package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {

  // Try representing the input in 1d array
  // take a note of line length
  int part1(String inputFilePath) {
    int result = 0;
    ArrayList<String> input1D = new ArrayList<String>();
    int inputLength = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();
      inputLength = line.length();

      while (line != null) {
        input1D.add(line.strip());
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // now how to handle the array?
    // for left -- index - 1 - string.contains(XMAS)
    // right -- index + 1 - string.contains(SMAX)
    // top -- index + length - string += string[i+(length*j)]
    // bottom -- index - length - string += string[i-(length*j)]
    // diagonal left top to right -- index - length + j - string +=
    // string[i-length+j]
    // diagonal right top to left -- index - length - j - string +=
    // string[i-length-j]

    String[] input1DArray = new String[input1D.size()];
    input1DArray = input1D.toArray(input1DArray);
    String input = String.join("", input1DArray);
    System.out.println(input);

    // Find in string "X" and look in all 8 directions
    // alternatively, remove strings in directions we don't care about,
    // and check if they end up as XMAS or SMAX
    // but both need "X" to be found

    // for (String line : input1DArray) {
    // result += countSubstringOccurance(input1DArray, "XMAS");
    // result += countSubstringOccurance(input1DArray, "SMAX");
    // }
    return result;
  }

  int countSubstringOccurance(String str, String pattern) {
    int occurances = 0;
    int lastIndex = 0;
    while (lastIndex != -1) {
      lastIndex = str.indexOf(pattern, lastIndex);

      if (lastIndex != -1) {
        occurances++;
        lastIndex += pattern.length();
      }
    }
    return occurances;
  }

  int part2(String inputFilePath) {
    int result = 0;

    return result;
  }

  private static int[] readFileLineToArray(String line) {
    return Arrays.stream(line.split("\\s+"))
        .mapToInt(Integer::parseInt) // TODO: Change if needing string
        .toArray();
  }

  void main(String[] args) {
    int properResultPart1 = 18; // TODO: change to part 1 sample data result
    int validationResultPart1 = part1("test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    int resultPart1 = part1("input.txt");
    System.out.println(resultPart1);
    //
    //
    //
    // int properResultPart2 = 0; // TODO: change to part 2 sample data result
    // int validationResultPart2 = part2("test_input.txt");
    // System.out.println(validationResultPart2);
    // System.out.println(validationResultPart2 == properResultPart2);
    //
    // int resultPart2 = part2("input.txt");
    // System.out.println(resultPart2);
  }

}
