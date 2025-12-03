import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

class Advent5 {
    
    public static void main(String args[]) throws FileNotFoundException {

        String binGamma = "";
        String binEpsilon = "";
        int decGamma, decEpsilon;

        File txt = new File("advent2021-5.txt");
        Scanner reader = new Scanner(txt);
        Scanner lineSize = new Scanner(txt);

        String line = lineSize.nextLine();
        int zeros[] = new int[line.length()];
        int ones[] = new int[line.length()];
        lineSize.close();

        while (reader.hasNextLine()) {
            
            line = reader.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '0') {
                    zeros[i]++;
                } else if (line.charAt(i) == '1') {
                    ones[i]++;
                }
            }
        }

        for (int i = 0; i < line.length(); i++) {
            if (zeros[i] > ones[i]) {
                binGamma += 0;
                binEpsilon += 1;
            } else if (zeros[i] < ones[i]) {
                binGamma += 1;
                binEpsilon += 0;
            }
        }
        System.out.println("Gamma = " + binGamma +
                           "\nEpsilon = " + binEpsilon);
        decGamma = binaryToInt(binGamma);
        decEpsilon = binaryToInt(binEpsilon);

        reader.close();
        System.out.println("Gamma = " + decGamma +
                           "\nEpsilon = " + decEpsilon +
                           "\nPower = " + decGamma * decEpsilon);
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
}
