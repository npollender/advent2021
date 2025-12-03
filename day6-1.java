import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Advent11 {

    public static void main(String args[]) throws FileNotFoundException {
       
        File txt = new File("advent2021-11.txt");
        Scanner reader = new Scanner(txt);

        ArrayList<Integer> fish = new ArrayList<>();
        String rawFish[] = reader.nextLine().split(",");
        reader.close();
        
        for (int i = 0; i < rawFish.length; i++) {
            fish.add(Integer.parseInt(rawFish[i]));
        }

        for (int i = 0; i < 80; i ++) {
            fish = breedFish(fish);
        }

        System.out.println(fish.size());
    }

    public static ArrayList<Integer> breedFish(ArrayList<Integer> fish) {

        ArrayList<Integer> newFish = new ArrayList<>();
        int age = 0;

        for (int i = 0; i < fish.size(); i++) {
            if (fish.get(i) == 0) {
                fish.set(i, 6);
                newFish.add(fish.get(i));
                newFish.add(8);
            } else {
                age = fish.get(i);
                fish.set(i, --age);
                newFish.add(fish.get(i));
            }
        }
        return newFish;
    }
}
