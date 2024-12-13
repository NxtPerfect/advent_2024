package lib;

/**
 * AoC
 * Fetch today's problem
 * Submit solution
 */
public class AoC {
  private class Cache {
    public String problem;
    public String input;

    public Cache() {
      // TODO: Create cache
    }

    public Cache getCache(int year, int day) {
      // TODO: Get cache
      // Return problem and input.txt from ./cache/{year}/{day}
      // or perhaps it should just copy these files to dir of current problem?
      return new Cache();
    }

    public void setCache() {
      // TODO: Set cache
    }
  }

  String getProblem(int year, int day) {
    // TODO: webscrape/api call to get said problem
    // Cache results if got them once
    // send new web request when part is finished
    // for part 2
    // https://adventofcode.com/2024/day/{day_nr}
    // article class=day-desc
    // h2 - day nr + title
    // p's for each line of description
    // code for sample input
    // ul for explanation of input
    // a href={day_nr}/input
    // form
    // input name=answer - input to put in number
    // input type=submit - button to submit answer
    return "";
  }

  boolean submitSolution(int result) {
    // TODO: submit number, get true/false if passed
    // if false show if too little or too much
    return true;
  }
}
