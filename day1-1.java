import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Advent {

    public static void main(String args[]) throws FileNotFoundException {

        int increases = 0;
        String previous = "";
        String current = "";
        File txt = new File("advent2021-1.txt");
        Scanner reader = new Scanner(txt);
        while (reader.hasNextLine()) {
            previous = current;
            current = reader.nextLine();
            if (previous.equals("")) {
                continue;
            } else {
                try {
                    if (Integer.parseInt(current) > Integer.parseInt(previous)) {
                        increases++;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        reader.close();
        System.out.println(increases);
    }
}
