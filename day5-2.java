import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Advent10 {
    
    public static void main(String args[]) throws FileNotFoundException {

        File txt = new File("advent2021-10.txt");
        Scanner reader = new Scanner(txt);

        int xcoords[] = new int[2];
        int ycoords[] = new int[2];
        int grid[][] = new int[1000][1000];

        int overlaps = 0;

        while (reader.hasNextLine()) {

            String coords[] = new String[2];
            String coords1[] = new String[2];
            String coords2[] = new String[2];
            coords = reader.nextLine().split(" -> ");
            coords1 = coords[0].split(",");
            coords2 = coords[1].split(",");

            ycoords[0] = Integer.parseInt(coords1[0]);
            ycoords[1] = Integer.parseInt(coords2[0]);
            xcoords[0] = Integer.parseInt(coords1[1]);
            xcoords[1] = Integer.parseInt(coords2[1]);

            if (xcoords[0] == xcoords[1]) {
                if (ycoords[1] > ycoords[0]) {
                    for (int i = ycoords[0]; i <= ycoords[1]; i++) {
                        grid[xcoords[0]][i]++;
                        if (grid[xcoords[0]][i] == 2) {
                            overlaps++;
                        }
                    }
                } else if (ycoords[0] > ycoords[1]) {
                    for (int i = ycoords[1]; i <= ycoords[0]; i++) {
                        grid[xcoords[0]][i]++;
                        if (grid[xcoords[0]][i] == 2) {
                            overlaps++;
                        }
                    }
                }
            } else if (ycoords[0] == ycoords[1]) {
                if (xcoords[1] > xcoords[0]) {
                    for (int i = xcoords[0]; i <= xcoords[1]; i++) {
                        grid[i][ycoords[0]]++;
                        if (grid[i][ycoords[0]] == 2) {
                            overlaps++;
                        }
                    }
                } else if (xcoords[0] > xcoords[1]) {
                    for (int i = xcoords[1]; i <= xcoords[0]; i++) {
                        grid[i][ycoords[0]]++;
                        if (grid[i][ycoords[0]] == 2) {
                            overlaps++;
                        }
                    }
                }
            } else {
                int xpos = xcoords[0];
                int xgoal = xcoords[1];
                int ypos = ycoords[0];
                int ygoal = ycoords[1];
                int intervals = Math.abs(xpos - xgoal) + 1;
                for (int i = 0; i < intervals; i++) {
                    grid[xpos][ypos]++;
                    if (grid[xpos][ypos] == 2) {
                        overlaps++;
                    }
                    if (xpos > xgoal) {
                        xpos--;
                    } else if (xpos < xgoal) {
                        xpos++;
                    }
                    if (ypos > ygoal) {
                        ypos--;
                    } else if (ypos < ygoal) {
                        ypos++;
                    }
                }
            }
        }

        /*for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] < 1) {
                    System.out.print(".");
                } else {
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }*/
        reader.close();
        System.out.println(overlaps);
    }
}
