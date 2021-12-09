import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

class Day1 {
  public static int calc1(ArrayList<Integer> inputs) {
    int increases = 0;
    int prev = inputs.get(0);
    for (int i = 1; i < inputs.size(); i++) {
      if (inputs.get(i) > prev) {
        increases++;
      }
      prev = inputs.get(i);
    }

    return increases;
  }

  public static int calc2(ArrayList<Integer> inputs) {
    int increases = 0;
    int prev = inputs.get(0) + inputs.get(1) + inputs.get(2);

    for (int i = 3; i < inputs.size(); i++) {
      int currentWindow = prev - inputs.get(i - 3) + inputs.get(i);
      if (currentWindow > prev) {
        increases++;
      }
    }

    return increases;
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new File("input1.txt"));
    ArrayList<Integer> inputs = new ArrayList<>();

    while (sc.hasNextInt()) {
      inputs.add(sc.nextInt());
    }

    int answer1 = calc1(inputs);
    int answer2 = calc2(inputs);

    System.out.println("Part 1: " + answer1);
    System.out.println("Part 2: " + answer2);
  }
}
