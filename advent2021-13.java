import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Advent13 {

    public static void main(String args[]) throws FileNotFoundException {
       
        File txt = new File("advent2021-13.txt");
        Scanner reader = new Scanner(txt);

        String rawPos[] = reader.nextLine().split(",");
        int pos[] = new int[rawPos.length];
        reader.close();

        int totalDistance = 0;
        int maxDistance = 0;
        int leastDistance = 2147483647;

        for (int i = 0; i < pos.length; i++) {
            pos[i] = Integer.parseInt(rawPos[i]);
            if (pos[i] > maxDistance) {
                maxDistance = pos[i];
            }
        }

        for (int i = 0; i < maxDistance + 1; i++) {
            totalDistance = 0;
            for (int j = 0; j < pos.length; j++) {
                totalDistance += moveCrab(pos[j], i);
            }
            if (totalDistance < leastDistance) {
                leastDistance = totalDistance;
            }
        }

        System.out.println(leastDistance);
    }

    public static int moveCrab(int pos, int dest) {

        return Math.abs(pos - dest);
    }
}