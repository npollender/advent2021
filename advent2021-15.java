import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Advent15 {

    public static void main(String args[]) throws FileNotFoundException {
       
        File txt = new File("advent2021-15.txt");
        Scanner reader = new Scanner(txt);

        ArrayList<String> outputs = new ArrayList<>();
        String splits[] = new String[2];
        String rawOut[] = new String[4];
        int uniques = 0;

        while (reader.hasNextLine()) {
            splits = reader.nextLine().split(" \\| ");
            rawOut = splits[1].split(" ");
            for (int i = 0; i < rawOut.length; i++) {
                outputs.add(rawOut[i]);
            }
        }
        reader.close();

        //look for strings with length 2, 3, 4, 7
        for (int i = 0; i < outputs.size(); i++) {
            if (outputs.get(i).length() == 2 ||
                outputs.get(i).length() == 3 ||
                outputs.get(i).length() == 4 ||
                outputs.get(i).length() == 7) {
                uniques++;
            }
        }

        System.out.println(uniques);
    }
}