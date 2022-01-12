import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Advent16 {

    public static void main(String args[]) throws FileNotFoundException {
       
        File txt = new File("advent2021-16.txt");
        Scanner reader = new Scanner(txt);

        ArrayList<String[]> inputs = new ArrayList<>();
        ArrayList<String[]> outputs = new ArrayList<>();
        String splits[] = new String[2];
        String nums[] = new String[10];
        String unknown[] = new String[6];

        String rawNum = "";
        int sum = 0;

        while (reader.hasNextLine()) {
            splits = reader.nextLine().split(" \\| ");
            inputs.add(splits[0].split(" "));
            outputs.add(splits[1].split(" "));
        }
        reader.close();

        //look for strings with length 2, 3, 4, 7
        for (int i = 0; i < inputs.size(); i++) {
            //getting null ptr ex for some reason so just init each index
            for (int z = 0; z < nums.length; z++) {
                nums[z] = "empty";
            }
            int index = 0;
            for (int j = 0; j < inputs.get(i).length; j++) {
                if (inputs.get(i)[j].length() == 2) {
                    nums[1] = inputs.get(i)[j];
                } else if (inputs.get(i)[j].length() == 3) {
                    nums[7] = inputs.get(i)[j];
                } else if (inputs.get(i)[j].length() == 4) {
                    nums[4] = inputs.get(i)[j];
                } else if (inputs.get(i)[j].length() == 7) {
                    nums[8] = inputs.get(i)[j];
                } else {
                    unknown[index++] = inputs.get(i)[j];
                }
            }
            while (true) {
                //6 length that matches number 4 will be number 9
                if (nums[9].equals("empty")) {
                    for (int x = 0; x < unknown.length; x++) {
                        if (unknown[x].length() == 6) {
                            if (isMatch(unknown[x], nums[4])) {
                                nums[9] = unknown[x];
                                unknown[x] = "x";
                                break;
                            }
                        }
                    }
                //5 length that matches number 7 will be number 3
                } else if (nums[3].equals("empty")) {
                    for (int x = 0; x < unknown.length; x++) {
                        if (unknown[x].length() == 5) {
                            if (isMatch(unknown[x], nums[7])) {
                                nums[3] = unknown[x];
                                unknown[x] = "x";
                                break;
                            }
                        }
                    }
                //6 length that matches number 7 will be number 0    
                } else if (nums[0].equals("empty")) {
                    for (int x = 0; x < unknown.length; x++) {
                        if (unknown[x].length() == 6) {
                            if (isMatch(unknown[x], nums[7])) {
                                nums[0] = unknown[x];
                                unknown[x] = "x";
                                break;
                            }
                        }
                    }
                //last remaining 6 length is number 6
                } else if (nums[6].equals("empty")) {
                    for (int x = 0; x < unknown.length; x++) {
                        if (unknown[x].length() == 6) {
                            nums[6] = unknown[x];
                            unknown[x] = "x";
                            break;
                            
                        }
                    }
                //if we know number 6 we know number 5
                } else if (nums[5].equals("empty")) {
                    for (int x = 0; x < unknown.length; x++) {
                        if (unknown[x].length() == 5) {
                            if (isMatch(unknown[x], nums[6])) {
                                nums[5] = unknown[x];
                                unknown[x] = "x";
                                break;
                            }
                        }
                    }
                //by process of elimination, last index is number 2
                } else if (nums[2].equals("empty")) {
                    for (int x = 0; x < unknown.length; x++) {
                        if (!unknown[x].equals("x")) {
                            nums[2] = unknown[x];
                            unknown[x] = "x";
                            break;
                        }
                    }
                } else {
                    break;
                }
            }

            for (int j = 0; j < outputs.get(i).length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (isPerfMatch(outputs.get(i)[j], nums[k])) {
                        rawNum += k;
                        break;
                    }
                }
            }

            sum += Integer.parseInt(rawNum);
            System.out.println(rawNum);
            rawNum = "";
        }

        System.out.println("-------------------\n" + sum);
    }

    public static boolean isMatch(String unknown, String known) {
        int count = 0;
        int goal = 0;
        if (unknown.length() < known.length()) {
            goal = unknown.length();
        } else {
            goal = known.length();
        }
        for (int i = 0; i < unknown.length(); i++) {
            for (int j = 0; j < known.length(); j++) {
                if (unknown.charAt(i) == known.charAt(j)) {
                    count++;
                    break;
                }
            }
        }
        return count == goal;
    }

    public static boolean isPerfMatch(String num1, String num2) {
        int count = 0;
        int goal = 0;
        if (num1.length() == num2.length()) {
            goal = num1.length();
        } else {
            return false;
        }
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                if (num1.charAt(i) == num2.charAt(j)) {
                    count++;
                    break;
                }
            }
        }
        return count == goal;
    }
}