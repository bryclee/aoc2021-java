import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Day3 {
  static int calc1(ArrayList<String> data) {
    int totalNums = data.size();
    int k = data.get(0).length();
    int[] sums = new int[k];

    for (int i = 0; i < totalNums; i++) {
      for (int j = 0; j < k; j++) {
        if (data.get(i).charAt(j) == '1') {
          sums[j]++;
        }
      }
    }

    int limit = totalNums / 2;
    int gamma = 0;
    int epsilon = 0;

    for (int j = 0; j < k; j++) {
      if (sums[j] > limit) {
        gamma += 1 << (k - j - 1);
      } else {
        epsilon += 1 << (k - j - 1);
      }
    }

    return gamma * epsilon;
  }

  static int calc2(ArrayList<String> data) {
    int k = data.get(0).length();

    LinkedList<String> generatorCandidates = new LinkedList<>(data);
    LinkedList<String> scrubberCandidates = new LinkedList<>(data);

    for (int j = 0; j < k; j++) {
      if (generatorCandidates.size() <= 1) {
        break;
      }

      int totalNums = generatorCandidates.size();
      int generatorSum = 0;
      int generatorTarget;
      for (Iterator<String> generatorIter = generatorCandidates.iterator(); generatorIter.hasNext(); ) {
        if (generatorIter.next().charAt(j) == '1') {
          generatorSum++;
        }
      }

      if (generatorSum * 2 >= totalNums) {
        generatorTarget = '1';
      } else {
        generatorTarget = '0';
      }

      // System.out.printf("[gen] j: %s, target: %c, %s / %s%n", j, generatorTarget, generatorSum, generatorCandidates.size());
      // System.out.println(Arrays.toString(generatorCandidates.toArray()));

      for (Iterator<String> generatorIter = generatorCandidates.iterator(); generatorIter.hasNext(); ) {
        if (generatorIter.next().charAt(j) != generatorTarget) {
          generatorIter.remove();
        }
      }

      // System.out.printf("rem: %s%n", generatorCandidates.size());
    }

    for (int j = 0; j < k; j++) {
      if (scrubberCandidates.size() <= 1) {
        break;
      }

      int totalNums = scrubberCandidates.size();
      int scrubberSum = 0;
      int scrubberTarget;
      for (Iterator<String> scrubberIter = scrubberCandidates.iterator(); scrubberIter.hasNext(); ) {
        if (scrubberIter.next().charAt(j) == '1') {
          scrubberSum++;
        }
      }

      if (scrubberSum * 2 >= totalNums) {
        scrubberTarget = '0';
      } else {
        scrubberTarget = '1';
      }

      // System.out.printf("[scrub] j: %s, target: %c, %s / %s%n", j, scrubberTarget, scrubberSum, scrubberCandidates.size());
      // System.out.println(Arrays.toString(scrubberCandidates.toArray()));

      for (Iterator<String> scrubberIter = scrubberCandidates.iterator(); scrubberIter.hasNext(); ) {
        if (scrubberIter.next().charAt(j) != scrubberTarget) {
          scrubberIter.remove();
        }
      }

      // System.out.printf("rem: %s%n", scrubberCandidates.size());
    }

    int generatorRating = Integer.parseInt(generatorCandidates.getFirst(), 2);
    int scrubberRating = Integer.parseInt(scrubberCandidates.getFirst(), 2);

    // System.out.printf("%s x %s%n", generatorCandidates.getFirst(), scrubberCandidates.getFirst());
    // System.out.printf("%s x %s%n", generatorRating, scrubberRating);

    return generatorRating * scrubberRating;
  }

  public static void main(String[] args) throws FileNotFoundException {
    ArrayList<String> data = new ArrayList<>();
    Scanner sc = new Scanner(new File("input.txt"));

    while (sc.hasNextLine()) {
      data.add(sc.nextLine());
    };

    int answer1 = calc1(data);
    int answer2 = calc2(data);

    System.out.println("Answer 1: " + answer1);
    System.out.println("Answer 2: " + answer2);
  }
}
