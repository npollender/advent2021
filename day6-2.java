import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

class Advent12 {

    public static void main(String args[]) throws FileNotFoundException {
       
        File txt = new File("advent2021-12.txt");
        Scanner reader = new Scanner(txt);

        String rawFish[] = reader.nextLine().split(",");
        double[] ageCounts = new double[9];
        double numFish = 0;
        reader.close();
        
        for (int i = 0; i < rawFish.length; i++) {
            int age = Integer.parseInt(rawFish[i]);
            switch (age) {
                case 0:
                    ageCounts[0]++;
                    break;
                case 1:
                    ageCounts[1]++;
                    break;
                case 2:
                    ageCounts[2]++;
                    break;
                case 3:
                    ageCounts[3]++;
                    break;
                case 4:
                    ageCounts[4]++;
                    break;
                case 5:
                    ageCounts[5]++;
                    break;
                case 6:
                    ageCounts[6]++;
                    break;
                case 7:
                    ageCounts[7]++;
                    break;
                case 8:
                    ageCounts[8]++;
                    break;
                default:
                    break;
            }
        }

        numFish += rawFish.length;

        for (int i = 0; i < 256; i ++) {
            double pop = ageCounts[0];
            numFish += pop;
            ageCounts[0] = ageCounts[1];
            ageCounts[1] = ageCounts[2];
            ageCounts[2] = ageCounts[3];
            ageCounts[3] = ageCounts[4];
            ageCounts[4] = ageCounts[5];
            ageCounts[5] = ageCounts[6];
            ageCounts[6] = ageCounts[7];
            ageCounts[7] = ageCounts[8];
            ageCounts[6] += pop;
            ageCounts[8] = pop;
        }

        String str = new DecimalFormat("#").format(numFish);
        System.out.println(str);
    }
}
