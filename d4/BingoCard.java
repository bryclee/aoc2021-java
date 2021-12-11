import java.util.HashMap;
import java.util.Map;

public class BingoCard {
  boolean[] card;
  HashMap<Integer, Integer> numbers;

  public BingoCard(int[] nums) {
    card = new boolean[nums.length];
    numbers = new HashMap<Integer, Integer>();

    for (int i = 0; i < nums.length; i++) {
      numbers.put(nums[i], i);
    }
  }

  public boolean hasNumber(int num) {
    return numbers.containsKey(num);
  }

  // Marks number and returns if board is winning or not
  public boolean checkNumber(int num) {
    int position = numbers.get(num);

    card[position] = true;

    int row = position / 5;
    int column = position % 5;

    int rowCount = 0;
    int colCount = 0;

    // System.out.printf("Num: %s, row: %s, col: %s%n", num, row, column);
    for (int i = 0; i < 5; i++) {
      if (card[(row * 5) + i]) {
        rowCount++;
      }
      if (card[column + (i * 5)]) {
        colCount++;
      }
      if (rowCount == 5 || colCount == 5) {
        return true;
      }
    }

    return false;
  }

  public int scoreCard(int finalNum) {
    int score = 0;

    for (Map.Entry<Integer, Integer> entry : numbers.entrySet()) {
      if (!card[entry.getValue()]) {
        score += entry.getKey();
      }
    }

    return score * finalNum;
  }
}
