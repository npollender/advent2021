import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Advent18 {

    public static void main(String args[]) throws FileNotFoundException {
       
        File txt = new File("advent2021-17.txt");
        Scanner reader = new Scanner(txt);

        int map[][];
        int numCol = 0;
        int numRow;
        String rawLines[];
        int ctr = 0;

        ArrayList<int[]> basins = new ArrayList<>();
        ArrayList<int[]> toCheck = new ArrayList<>();
        ArrayList<int[]> checked = new ArrayList<>();
        int[] sizes;

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

        for (int i = 0; i < numCol; i++) {
            for (int j = 0; j < numRow; j++) {
                int coord[] = new int[2];
                coord = checkCoord(i, j, numCol, numRow, map);
                if (coord[0] != -1) {
                    basins.add(coord);
                }
            }
        }
        sizes = new int[basins.size()];

        //ok this has got to be one of the jankiest pieces of code ive ever written, even worse than question 17.
        int coord[];
        for (int i = 0; i < basins.size(); i++) {
            toCheck.clear();
            checked.clear();
            coord = new int[2];
            coord[0] = basins.get(i)[0];
            coord[1] = basins.get(i)[1];
            checked.add(coord);
            sizes[i]++;
            try {
                if (map[basins.get(i)[0] - 1][basins.get(i)[1]] != 9) {
                    coord = new int[2];
                    coord[0] = basins.get(i)[0] - 1;
                    coord[1] = basins.get(i)[1];
                    toCheck.add(coord);
                    sizes[i]++;
                }
            } catch (Exception e) {}
            try {
                if (map[basins.get(i)[0] + 1][basins.get(i)[1]] != 9) {
                    coord = new int[2];
                    coord[0] = basins.get(i)[0] + 1;
                    coord[1] = basins.get(i)[1];
                    toCheck.add(coord);
                    sizes[i]++;
                }
            } catch (Exception e) {}
            try {
                if (map[basins.get(i)[0]][basins.get(i)[1] - 1] != 9) {
                    coord = new int[2];
                    coord[0] = basins.get(i)[0];
                    coord[1] = basins.get(i)[1] - 1;
                    toCheck.add(coord);
                    sizes[i]++;
                }
            } catch (Exception e) {}
            try {
                if (map[basins.get(i)[0]][basins.get(i)[1] + 1] != 9) {
                    coord = new int[2];
                    coord[0] = basins.get(i)[0];
                    coord[1] = basins.get(i)[1] + 1;
                    toCheck.add(coord);
                    sizes[i]++;
                }
            } catch (Exception e) {}
            while (!toCheck.isEmpty()) {
                try {
                    coord = new int[2];
                    coord[0] = toCheck.get(0)[0] - 1;
                    coord[1] = toCheck.get(0)[1];
                    if (!containsCoord(checked, coord) && !containsCoord(toCheck, coord) && map[coord[0]][coord[1]] != 9) {
                        toCheck.add(coord);
                        sizes[i]++;
                    }
                } catch (Exception e) {}
                try {
                    coord = new int[2];
                    coord[0] = toCheck.get(0)[0] + 1;
                    coord[1] = toCheck.get(0)[1];
                    if (!containsCoord(checked, coord) && !containsCoord(toCheck, coord) && map[coord[0]][coord[1]] != 9) {
                        toCheck.add(coord);
                        sizes[i]++;
                    }
                } catch (Exception e) {}
                try {
                    coord = new int[2];
                    coord[0] = toCheck.get(0)[0];
                    coord[1] = toCheck.get(0)[1] - 1;
                    if (!containsCoord(checked, coord) && !containsCoord(toCheck, coord) && map[coord[0]][coord[1]] != 9) {
                        toCheck.add(coord);
                        sizes[i]++;
                    }
                } catch (Exception e) {}
                try {
                    coord = new int[2];
                    coord[0] = toCheck.get(0)[0];
                    coord[1] = toCheck.get(0)[1] + 1;
                    if (!containsCoord(checked, coord) && !containsCoord(toCheck, coord) && map[coord[0]][coord[1]] != 9) {
                        toCheck.add(coord);
                        sizes[i]++;
                    }
                } catch (Exception e) {}
                checked.add(toCheck.get(0));
                toCheck.remove(0);
            }
        }

        int large1 = 0;
        int large2 = 0;
        int large3 = 0;
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i] > large1) {
                large3 = large2;
                large2 = large1;
                large1 = sizes[i];
            } else if (sizes[i] > large2) {
                large3 = large2;
                large2 = sizes[i];
            } else if (sizes[i] > large3) {
                large3 = sizes[i];
            }
        }

        System.out.println(large1 * large2 * large3);
    }

    //this is crap, could have just used 4 try/catch blocks (like above) but i cba to change it.
    public static int[] checkCoord(int i, int j, int numCol, int numRow, int map[][]) {
        int coord[] = new int[2];
        coord[0] = -1;
        coord[1] = -1;
        //NOT along the edges (4 adjacent)
        if (i > 0 && i < numCol - 1 && j > 0 && j < numRow - 1) {
            if (map[i][j] < map[i-1][j] && map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1]) {
                coord[0] = i;
                coord[1] = j;
            }
        //top row (omit corners)
        } else if (j == 0 && (i != 0 && i != numCol - 1)) {
             if (map[i][j] < map[i-1][j] && map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1]) {
                coord[0] = i;
                coord[1] = j;
            }
         //bottom row (omit corners)
        } else if (j == numRow - 1 && (i != 0 && i != numCol - 1)) {
            if (map[i][j] < map[i-1][j] && map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1]) {
                coord[0] = i;
                coord[1] = j;
            }
        //left col (omit corners)
        } else if (i == 0 && (j != 0 && j != numRow - 1)) {
            if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1]) {
                coord[0] = i;
                coord[1] = j;
            }
        //right col (omit corners)    
        } else if (i == numCol - 1 && (j != 0 && j != numRow - 1)) {
            if (map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1] && map[i][j] < map[i][j+1]) {
                coord[0] = i;
                coord[1] = j;
            }
        //top left corner    
        } else if (i == 0 && j == 0) {
            if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1]) {
                coord[0] = i;
                coord[1] = j;
            }
        //top right corner
        } else if (i == numCol - 1 && j == 0) {
            if (map[i][j] < map[i-1][j] && map[i][j] < map[i][j+1]) {
                coord[0] = i;
                coord[1] = j;
            }
        //bottom left corner
        } else if (i == 0 && j == numRow - 1) {
            if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1]) {
                coord[0] = i;
                coord[1] = j;
            }
        //bottom right corner
        } else if (i == numCol - 1 && j == numRow - 1) {
            if (map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                coord[0] = i;
                coord[1] = j;
            }
        }
        return coord;
    }

    public static boolean containsCoord(ArrayList<int[]> checked, int coord[]) {

        for (int i = 0; i < checked.size(); i++) {
            if (coord[0] == checked.get(i)[0] && coord[1] == checked.get(i)[1]) {
                return true;
            }
        }
        return false;
    }
}