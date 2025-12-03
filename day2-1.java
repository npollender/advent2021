import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Advent3 {
    
    public static void main(String args[]) throws FileNotFoundException {

        String cmd = "";
        int xpos = 0;
        int ypos = 0;
        File txt = new File("advent2021-3.txt");
        Scanner reader = new Scanner(txt);
        while (reader.hasNextLine()) {
            cmd = reader.nextLine();
            if (cmd.charAt(0) == 'f') {
                xpos += (cmd.charAt(cmd.length() - 1) - '0');
            } else if (cmd.charAt(0) == 'd') {
                ypos += (cmd.charAt(cmd.length() - 1) - '0');
            } else if (cmd.charAt(0) == 'u') {
                ypos -= (cmd.charAt(cmd.length() - 1) - '0');
            }
        }

        reader.close();
        System.out.println(xpos * ypos);
    }
}
