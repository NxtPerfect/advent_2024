package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lib.Day;

class Solution extends Day {

  // Starting from top, going counterclockwise
  int[][] directions = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
  String[] XMASletters = { "M", "A", "S" };
  String[] X_MASletters = { "M", "S" };

  int part1(String inputFilePath) {
    int result = 0;
    List<List<String>> input2D = new ArrayList<List<String>>();
    int inputLength = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();
      inputLength = line.length();
      int i = 0;

      while (line != null) {
        input2D.add(new ArrayList<String>());
        String[] splitLineByChar = line.strip().split("(?<=.)");
        for (String letter : splitLineByChar) {
          input2D.get(i).add(letter);
        }
        i++;
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    for (int i = 0; i < input2D.size(); i++) {
      List<String> line = input2D.get(i);
      int firstXIndex = line.indexOf("X");
      if (firstXIndex == -1) {
        continue;
      }
      List<Integer> xIndexes = new ArrayList<Integer>();
      for (int j = firstXIndex; j < input2D.get(i).size(); j++) {
        if (line.get(j).equals("X"))
          xIndexes.add(j);
      }

      for (Integer index : xIndexes) {
        boolean isValid = true;
        for (int j = 0; j < directions.length; j++) {
          int row = directions[j][0];
          int col = directions[j][1];

          for (int k = 0; k < XMASletters.length; k++) {
            int dirRow = row * (k + 1);
            int dirCol = col * (k + 1);
            if ((i + dirRow) < 0 || (i + dirRow) >= input2D.size() || (index + dirCol) < 0
                || (index + dirCol) >= input2D.get(i + dirRow).size()) {
              isValid = false;
              break;
            }

            if (input2D.get(i + dirRow).get(index + dirCol).equals(XMASletters[k])) {
              isValid = true;
              continue;
            }
            isValid = false;
            break;
          }
          result += isValid ? 1 : 0;
        }
      }
    }
    return result;
  }

  int part2(String inputFilePath) {
    int result = 0;
    List<List<String>> input2D = new ArrayList<List<String>>();
    int inputLength = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
      String line = br.readLine();
      inputLength = line.length();
      int i = 0;

      while (line != null) {
        input2D.add(new ArrayList<String>());
        String[] splitLineByChar = line.strip().split("(?<=.)");
        for (String letter : splitLineByChar) {
          input2D.get(i).add(letter);
        }
        i++;
        line = br.readLine();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    for (int i = 0; i < input2D.size(); i++) {
      List<String> line = input2D.get(i);
      int firstAIndex = line.indexOf("A");
      if (firstAIndex == -1) {
        continue;
      }
      List<Integer> aIndexes = new ArrayList<Integer>();
      for (int j = firstAIndex; j < input2D.get(i).size(); j++) {
        if (line.get(j).equals("A"))
          aIndexes.add(j);
      }

      for (Integer index : aIndexes) {
        boolean isValid = true;
        for (int j = 0; j < directions.length; j++) {
          int row = directions[j][0];
          int col = directions[j][1];

          for (int k = 0; k < X_MASletters.length; k++) {
            int dirRow = row * (k + 1);
            int dirCol = col * (k + 1);
            if ((i + dirRow) < 0 || (i + dirRow) >= input2D.size() || (index + dirCol) < 0
                || (index + dirCol) >= input2D.get(i + dirRow).size()) {
              isValid = false;
              break;
            }

            if (input2D.get(i + dirRow).get(index + dirCol).equals(X_MASletters[k])) {
              isValid = true;
              continue;
            }
            isValid = false;
            break;
          }
          result += isValid ? 1 : 0;
        }
      }
    }
    return result;
  }

  void main() {
    int properResultPart1 = 18;
    int validationResultPart1 = part1("day4/test_input.txt");
    System.out.println(validationResultPart1);
    System.out.println(validationResultPart1 == properResultPart1);

    int resultPart1 = part1("day4/input.txt");
    System.out.println(resultPart1);

    int properResultPart2 = 9;
    int validationResultPart2 = part2("day4/test_input.txt");
    System.out.println(validationResultPart2);
    System.out.println(validationResultPart2 == properResultPart2);

    // int resultPart2 = part2("day4/input.txt");
    // System.out.println(resultPart2);
  }

}
