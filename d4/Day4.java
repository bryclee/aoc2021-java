import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day4 {
  static int calc1(int[] numbers, List<BingoCard> cards) {
    for (int num : numbers) {
      for (BingoCard card : cards) {
        if (!card.hasNumber(num)) {
          continue;
        }

        boolean winning = card.checkNumber(num);

        if (winning) {
          return card.scoreCard(num);
        }
      }
    }

    return 0;
  }

  static int calc2(int[] numbers, List<BingoCard> cards) {
    for (int num : numbers) {
      Iterator<BingoCard> iter = cards.iterator();

      while (iter.hasNext()) {
        BingoCard lastCard = iter.next();

        if (!lastCard.hasNumber(num)) {
          continue;
        }

        boolean winning = lastCard.checkNumber(num);

        // System.out.printf("Check num: %s%n", num);

        if (winning) {
          iter.remove();

          if (cards.size() == 0) {
            // System.out.printf("Last num: %s%n", num);
            return lastCard.scoreCard(num);
          }
        }
      }
    }

    return 0;
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new File("input.txt"));
    String[] numbers = sc.nextLine().split(",");
    LinkedList<BingoCard> cards1 = new LinkedList<>();
    LinkedList<BingoCard> cards2 = new LinkedList<>();

    while (sc.hasNextInt()) {
      int[] nums = new int[25];
      for (int i = 0; i < 25; i++) {
        nums[i] = sc.nextInt();
      }

      cards1.add(new BingoCard(nums));
      cards2.add(new BingoCard(nums));
    }

    int[] nums = new int[numbers.length];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = Integer.parseInt(numbers[i]);
    }

    int answer1 = calc1(nums, cards1);
    int answer2 = calc2(nums, cards2);

    System.out.printf("Answer 1: %s%n", answer1);
    System.out.printf("Answer 2: %s%n", answer2);
  }
}
