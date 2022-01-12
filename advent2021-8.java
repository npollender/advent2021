import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Advent8 {

    public static void main(String args[]) throws FileNotFoundException {

        File txt = new File("advent2021-8.txt");
        Scanner reader = new Scanner(txt);

        String callOutsRaw = reader.nextLine();
        String callOuts[] = callOutsRaw.split(",");
        int lastCall = 0;

        String bingoLineRaw;
        String bingoLine[];
        String bingo[][];
        String lastWinner[][] = new String[5][5];
        ArrayList<String[][]> boards = new ArrayList<>();

        while (reader.hasNextLine()) {
            bingo = new String[5][5];
            reader.nextLine();
            for (int i = 0; i < 5; i++) {
                bingoLineRaw = reader.nextLine();
                if (bingoLineRaw.charAt(0) == ' ') {
                    StringBuilder sb = new StringBuilder(bingoLineRaw);
                    sb.deleteCharAt(0);
                    bingoLineRaw = sb.toString();
                }
                bingoLine = bingoLineRaw.split("\\s+");
                for (int j = 0; j < 5; j++) {
                    bingo[i][j] = bingoLine[j];
                }
            }
            boards.add(bingo);
        }
        Collections.reverse(boards);
        reader.close(); 

        for (int i = 0; i < callOuts.length; i++) {
            for (int j = 0; j < boards.size(); j++) {
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (boards.get(j)[k][l].equals(callOuts[i])) {
                            boards.get(j)[k][l] = " X";
                            if (isWinner(boards.get(j))) {
                                for (int x = 0; x < 5; x++) {
                                    for (int y = 0; y < 5; y++) {
                                        lastWinner[x][y] = boards.get(j)[x][y];
                                    }
                                }
                                lastCall = Integer.parseInt(callOuts[i]);
                                boards.set(j, wipeBoard(boards.get(j)));
                                //analyzeBoards(boards);
                            }
                        } 
                    }
                }
            }
        }

        System.out.println(score(lastCall, lastWinner));
    }

    public static boolean isWinner(String bingo[][]) {
        //Horizontal & Vertical Lines
        for (int i = 0; i < 5; i++) {
            if ((bingo[i][0].equals(" X") &&
                 bingo[i][1].equals(" X") &&
                 bingo[i][2].equals(" X") &&
                 bingo[i][3].equals(" X") &&
                 bingo[i][4].equals(" X")) ||
                (bingo[0][i].equals(" X") &&
                 bingo[1][i].equals(" X") &&
                 bingo[2][i].equals(" X") &&
                 bingo[3][i].equals(" X") &&
                 bingo[4][i].equals(" X"))) {
                return true;
            }
        }
        /*Diagonals
        if (bingo[0][0].equals("X") &&
            bingo[1][1].equals("X") &&
            bingo[2][2].equals("X") &&
            bingo[3][3].equals("X") &&
            bingo[4][4].equals("X")) {
            return true;
        } else if (bingo[0][4].equals("X") &&
                   bingo[1][3].equals("X") &&
                   bingo[2][2].equals("X") &&
                   bingo[3][1].equals("X") &&
                   bingo[4][0].equals("X")) {
            return true;
        }*/
        return false;
    }

    public static int score(int call, String bingo[][]) {

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!bingo[i][j].equals(" X") && !bingo[i][j].equals("-")) {
                    sum += Integer.parseInt(bingo[i][j]);
                }
            }
        }
        return call * sum;
    }

    public static void analyzeBoards(ArrayList<String[][]> boards) {

        for (int i = 0; i < boards.size(); i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.print(boards.get(i)[j][k] + " ");
                }
                System.out.println();
            }
            System.out.print("\n--------------------");
        }
    }

    public static String[][] wipeBoard(String[][] bingo) {
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                bingo[i][j] = "-";
            }
        }
        return bingo;
    }
}