import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day2 {
  static int calc1(ArrayList<String> commands) {
    int horizontal = 0;
    int vertical = 0;

    for (int i = 0; i < commands.size(); i++) {
      String[] command = commands.get(i).split(" ");
      int change = Integer.parseInt(command[1]);
      String direction = command[0];

      switch (direction) {
        case "forward":
          horizontal += change;
          break;
        case "down":
          vertical += change;
          break;
        case "up":
          vertical -= change;
          break;
        default:
          break;
      }
    }

    return horizontal * vertical;
  }

  static int calc2(ArrayList<String> commands) {
    int horizontal = 0;
    int vertical = 0;
    int aim = 0;

    for (int i = 0; i < commands.size(); i++) {
      String[] command = commands.get(i).split(" ");
      int change = Integer.parseInt(command[1]);
      String direction = command[0];

      switch (direction) {
        case "forward":
          horizontal += change;
          vertical += aim * change;
          break;
        case "down":
          aim += change;
          break;
        case "up":
          aim -= change;
          break;
        default:
          break;
      }
    }

    return horizontal * vertical;
  }

  public static void main(String[] args) throws FileNotFoundException {
    ArrayList<String> commands = new ArrayList<>();
    Scanner sc = new Scanner(new File("input.txt"));

    while (sc.hasNextLine()) {
      commands.add(sc.nextLine());
    }

    int answer1 = calc1(commands);
    int answer2 = calc2(commands);

    System.out.println("Answer 1: " + answer1);
    System.out.println("Answer 2: " + answer2);
  }
}
