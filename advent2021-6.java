import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

class Advent6 {
    
    static int zeros[];
    static int ones[];
    static int zerosL[];
    static int onesL[];
    public static void main(String args[]) throws FileNotFoundException {

        char toKeep = 'X';
        char toKeepL = 'X';
        String oxygen = "";
        String carbon = "";
        ArrayList<String> binContainerMore = new ArrayList<String>();
        ArrayList<String> binContainerLess = new ArrayList<String>();

        File txt = new File("advent2021-6.txt");
        Scanner reader = new Scanner(txt);
        Scanner rereader = new Scanner(txt);
        Scanner lineSize = new Scanner(txt);

        String line = lineSize.nextLine();
        zeros = new int[line.length()];
        ones = new int[line.length()];
        zerosL = new int[line.length()];
        onesL = new int[line.length()];
        lineSize.close();

        while (reader.hasNextLine()) {
            
            line = reader.nextLine();
            if (line.charAt(0) == '0') {
                zeros[0]++;
            } else if (line.charAt(0) == '1') {
                ones[0]++;
            }
        }
        reader.close();

        if (zeros[0] > ones[0]) {
            toKeep = '0';
        } else if (zeros[0] < ones[0]) {
            toKeep = '1';
        }
        
        while (rereader.hasNextLine()) {

            line = rereader.nextLine();
            if (line.charAt(0) == toKeep) {
                binContainerMore.add(line);
                if (line.charAt(1) == '0') {
                    zeros[1]++;
                } else if (line.charAt(1) == '1') {
                    ones[1]++;
                }
            } else {
                binContainerLess.add(line);
                if (line.charAt(1) == '0') {
                    zerosL[1]++;
                } else if (line.charAt(1) == '1') {
                    onesL[1]++;
                }
            }
        }
        rereader.close();

        for (int i = 1; i < line.length(); i++) {
            if (oxygen.equals("") && zeros[i] == ones[i]) {
                if (binContainerMore.size() > 2) {
                    toKeep = '1';
                    binContainerMore = consolidateBins(i, toKeep, binContainerMore, true);
                } else {
                    for (int j = 0; j < binContainerMore.size(); j++) {
                        if (binContainerMore.get(j).charAt(i) == '1') {
                            oxygen = binContainerMore.get(j);
                        }
                    }
                }
            } else if (zeros[i] > ones[i]) {
                toKeep = '0';
                binContainerMore = consolidateBins(i, toKeep, binContainerMore, true);
            } else if (zeros[i] < ones[i]) {
                toKeep = '1';
                binContainerMore = consolidateBins(i, toKeep, binContainerMore, true);
            } 
            if (carbon.equals("") && zerosL[i] == onesL[i]) {
                if (binContainerLess.size() > 2) {
                    toKeepL = '0';
                    binContainerLess = consolidateBins(i, toKeepL, binContainerLess, false);
                } else {
                    for (int j = 0; j < binContainerLess.size(); j++) {
                        if (binContainerLess.get(j).charAt(i) == '0') {
                            carbon = binContainerLess.get(j);
                       }
                   }
                }
            } else if (zerosL[i] > onesL[i]) {
                toKeepL = '1';
                binContainerLess = consolidateBins(i, toKeepL, binContainerLess, false);
            } else if (zerosL[i] < onesL[i]) {
                toKeepL = '0';
                binContainerLess = consolidateBins(i, toKeepL, binContainerLess, false);
            } 
        }

        System.out.println(binaryToInt(oxygen) * binaryToInt(carbon));
    }

    public static int binaryToInt(String bin) {

        int j = 0;
        int result = 0;
        for (int i = bin.length() - 1; i >= 0; i--) {
            if (bin.charAt(i) == '0') {
                j++;
                continue;
            }
            result += Math.pow(2, j);
            j++;
        }
        return result;
    }

    public static ArrayList<String> consolidateBins(int index, char toKeep, ArrayList<String> list, boolean flag) {

        ArrayList<String> output = new ArrayList<String>();
        if (flag) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).charAt(index) == toKeep) {
                    output.add(list.get(i));
                    if (list.get(i).charAt(index + 1) == '0') {
                        zeros[index + 1]++;
                    } else if (list.get(i).charAt(index + 1) == '1') {
                        ones[index + 1]++;
                    }
                }        
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).charAt(index) == toKeep) {
                    output.add(list.get(i));
                    if (list.get(i).charAt(index + 1) == '0') {
                        zerosL[index + 1]++;
                    } else if (list.get(i).charAt(index + 1) == '1') {
                        onesL[index + 1]++;
                    }
                }        
            } 
        } 
        return output;
    }
}
