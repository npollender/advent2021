import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Advent4 {
    
    public static void main(String args[]) throws FileNotFoundException {

        String cmd = "";
        int xpos = 0;
        int ypos = 0;
        int aim = 0;
        File txt = new File("advent2021-4.txt");
        Scanner reader = new Scanner(txt);
        while (reader.hasNextLine()) {
            cmd = reader.nextLine();
            if (cmd.charAt(0) == 'f') {
                xpos += (cmd.charAt(cmd.length() - 1) - '0');
                ypos += aim * (cmd.charAt(cmd.length() - 1) - '0');
            } else if (cmd.charAt(0) == 'd') {
                aim += (cmd.charAt(cmd.length() - 1) - '0');
            } else if (cmd.charAt(0) == 'u') {
                aim -= (cmd.charAt(cmd.length() - 1) - '0');
            }
        }

        reader.close();
        System.out.println(xpos * ypos);
    }
}
