import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;

public class Day5 {
  static void printCoords(int[][] coords) {
    for (int[] arr : coords) {
      System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
  }

  static int calc1(List<String> inputs) {
    int[][] coords = new int[1000][1000];
    int overlaps = 0;

    for (String input : inputs) {
      Pattern pat = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");
      Matcher mat = pat.matcher(input);
      if (mat.matches()) {
        int startX = Integer.parseInt(mat.group(1));
        int startY = Integer.parseInt(mat.group(2));
        int endX = Integer.parseInt(mat.group(3));
        int endY = Integer.parseInt(mat.group(4));

        if (startX == endX) {
          int start = Math.min(startY, endY);
          int end = Math.max(startY, endY);
          for (int i = start; i <= end; i++) {
            coords[i][startX]++;
            if (coords[i][startX] == 2) {
              overlaps++;
            }
          }
        } else if (startY == endY) {
          // Assume Y is equal
          int start = Math.min(startX, endX);
          int end = Math.max(startX, endX);
          for (int i = start; i <= end; i++) {
            coords[startY][i]++;
            if (coords[startY][i] == 2) {
              overlaps++;
            }
          }
        }
      }
    }

    return overlaps;
  }

  static int calc2(List<String> inputs) {
    int[][] coords = new int[1000][1000];
    int overlaps = 0;

    for (String input : inputs) {
      Pattern pat = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");
      Matcher mat = pat.matcher(input);
      if (mat.matches()) {
        int startX = Integer.parseInt(mat.group(1));
        int startY = Integer.parseInt(mat.group(2));
        int endX = Integer.parseInt(mat.group(3));
        int endY = Integer.parseInt(mat.group(4));

        if (startX == endX) {
          int start = Math.min(startY, endY);
          int end = Math.max(startY, endY);
          for (int i = start; i <= end; i++) {
            coords[i][startX]++;
            if (coords[i][startX] == 2) {
              overlaps++;
            }
          }
        } else if (startY == endY) {
          // Assume Y is equal
          int start = Math.min(startX, endX);
          int end = Math.max(startX, endX);
          for (int i = start; i <= end; i++) {
            coords[startY][i]++;
            if (coords[startY][i] == 2) {
              overlaps++;
            }
          }
        } else {
          if (startX > endX) {
            int tempX = startX;
            int tempY = startY;

            startX = endX;
            endX = tempX;
            startY = endY;
            endY = tempY;
          }

          int dirY = endY > startY ? 1 : -1;

          for (int i = 0; i <= (endX - startX); i++) {
            coords[startY + i * dirY][startX + i]++;
            if (coords[startY + i * dirY][startX + i] == 2) {
              overlaps++;
            }
          }
        }
      }
    }

    return overlaps;
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new File("input.txt"));
    ArrayList<String> inputs = new ArrayList<>();

    while (sc.hasNextLine()) {
      inputs.add(sc.nextLine());
    }

    int answer1 = calc1(inputs);
    int answer2 = calc2(inputs);

    System.out.printf("Answer 1: %s%n", answer1);
    System.out.printf("Answer 2: %s%n", answer2);
  }
}
