import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Main {

    /*      [G]         [D]     [Q]
    [P]     [T]         [L] [M] [Z]
    [Z] [Z] [C]         [Z] [G] [W]
    [M] [B] [F]         [P] [C] [H] [N]
    [T] [S] [R]     [H] [W] [R] [L] [W]
    [R] [T] [Q] [Z] [R] [S] [Z] [F] [P]
    [C] [N] [H] [R] [N] [H] [D] [J] [Q]
    [N] [D] [M] [G] [Z] [F] [W] [S] [S]
     1   2   3   4   5   6   7   8   9 */

    public static void main(String[] args) throws IOException {
        String cadena;
        FileReader f = new FileReader("src\\puzzle5.txt");
        BufferedReader b = new BufferedReader(f);

        ArrayList<Character>[] stack = new ArrayList[10];

        stack[1] = new ArrayList<>(Arrays.asList('N','C','R','T','M','Z','P'));
        stack[2] = new ArrayList<>(Arrays.asList('D','N','T','S','B','Z'));
        stack[3] = new ArrayList<>(Arrays.asList('M','H','Q','R','F','C','T','G'));
        stack[4] = new ArrayList<>(Arrays.asList('G','R','Z'));
        stack[5] = new ArrayList<>(Arrays.asList('Z','N','R','H'));
        stack[6] = new ArrayList<>(Arrays.asList('F','H','S','W','P','Z','L','D'));
        stack[7] = new ArrayList<>(Arrays.asList('W','D','Z','R','C','G','M'));
        stack[8] = new ArrayList<>(Arrays.asList('S','J','F','L','H','W','Z','Q'));
        stack[9] = new ArrayList<>(Arrays.asList('S','Q','P','W','N'));

        //Reverse Lists
        for (int i = 1; i < 10; i++) {
            Collections.reverse(stack[i]);
        }

        while((cadena = b.readLine())!=null) {
            //Parse string
            String[] tokens = cadena.split(" ");

            //Get numbers
            int quantity = Integer.parseInt(tokens[1]);
            int origin = Integer.parseInt(tokens[3]);
            int dest = Integer.parseInt(tokens[5]);

            //Introduce the characters in a temp stack and remove characters from origin stack
            ArrayList<Character> temp_stack = new ArrayList<>();
            for (int i = 0; i < quantity; i++) {
                temp_stack.add(0, stack[origin].get(0));
                stack[origin].remove(0);
            }

            //Add the temp stack to the dest stack
            for (int i = 0; i < quantity; i++) {
                stack[dest].add(0, temp_stack.get(0));
                temp_stack.remove(0);
            }

        }
        System.out.println("-------------");
        //Show characters
        for (int i = 1; i < 10; i++) {
            System.out.println(stack[i].get(0));
        }
    }
}