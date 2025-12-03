import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Advent17 {

    public static void main(String args[]) throws FileNotFoundException {
       
        File txt = new File("advent2021-17.txt");
        Scanner reader = new Scanner(txt);

        int map[][];
        int numCol = 0;
        int numRow;
        String rawLines[];
        int risk = 0;
        int ctr = 0;

        while(reader.hasNextLine()) {
            reader.nextLine();
            numCol++;
        }
        reader.close();
        reader = new Scanner(txt);

        rawLines = new String[numCol];
        while (reader.hasNextLine()) {
            rawLines[ctr++] = reader.nextLine();
        }
        reader.close();

        numRow = rawLines[0].length();

        map = new int[numCol][numRow];

        for (int i = 0; i < numCol; i++) {
            for (int j = 0; j < numRow; j++) {
                map[i][j] = Character.getNumericValue(rawLines[i].charAt(j));
            }
        }

        //this is pretty fuckin jank
        for (int i = 0; i < numCol; i++) {
            for (int j = 0; j < numRow; j++) {
                //NOT along the edges (4 adjacent)
                if (i > 0 && i < numCol - 1 && j > 0 && j < numRow - 1) {
                    if (map[i][j] < map[i-1][j] && map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1]) {
                        risk += map[i][j] + 1;
                    }
                //top row (omit corners)
                } else if (j == 0 && (i != 0 && i != numCol - 1)) {
                    if (map[i][j] < map[i-1][j] && map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1]) {
                        risk += map[i][j] + 1;
                    }
                //bottom row (omit corners)
                } else if (j == numRow - 1 && (i != 0 && i != numCol - 1)) {
                    if (map[i][j] < map[i-1][j] && map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1]) {
                        risk += map[i][j] + 1;
                    }
                //left col (omit corners)
                } else if (i == 0 && (j != 0 && j != numRow - 1)) {
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1]) {
                        risk += map[i][j] + 1;
                    }
                //right col (omit corners)    
                } else if (i == numCol - 1 && (j != 0 && j != numRow - 1)) {
                    if (map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1]) {
                        risk += map[i][j] + 1;
                    }
                //top left corner    
                } else if (i == 0 && j == 0) {
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1]) {
                        risk += map[i][j] + 1;
                    }
                //top right corner
                } else if (i == numCol - 1 && j == 0) {
                    if (map[i][j] < map[i-1][j] && map[i][j] < map[i][j+1]) {
                        risk += map[i][j] + 1;
                    }
                //bottom left corner
                } else if (i == 0 && j == numRow - 1) {
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1]) {
                        risk += map[i][j] + 1;
                    }
                //bottom right corner
                } else if (i == numCol - 1 && j == numRow - 1) {
                    if (map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                        risk += map[i][j] + 1;
                    }
                }
            }
        }

        System.out.println(risk);
    }
}
