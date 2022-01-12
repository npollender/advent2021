import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Advent2 {

    public static void main(String args[]) throws FileNotFoundException {

        int increases = 0;
        int previous = -1, current = -1;
        File txt = new File("advent2021-2.txt");
        Scanner reader = new Scanner(txt);
        Scanner reader2 = new Scanner(txt);
        Scanner reader3 = new Scanner(txt);
        reader2.nextLine();
        reader3.nextLine();
        reader3.nextLine();
        while (reader3.hasNextLine()) {
            previous = current;
            current = Integer.parseInt(reader.nextLine()) +
                      Integer.parseInt(reader2.nextLine()) +
                      Integer.parseInt(reader3.nextLine());
            if (previous == -1) {
                continue;
            } else {
                try {
                    if (current > previous) {
                        increases++;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        reader.close();
        reader2.close();
        reader3.close();
        System.out.println(increases);
    }
}