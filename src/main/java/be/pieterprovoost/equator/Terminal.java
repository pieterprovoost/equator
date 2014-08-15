package be.pieterprovoost.equator;

import java.util.Scanner;

/**
 * Class Terminal.
 */
public class Terminal {

    public static void main(String[] args) {
        EquationEngine engine = new EquationEngine();
        //engine.setVerbose(true);
        Scanner in = new Scanner(System.in);

        String input;
        while (true) {
            System.out.print("> ");
            input = in.nextLine();
            try {
                if (input.equals("quit")) {
                    break;
                } else if (input.contains("=")) {
                    engine.evaluate(input);
                } else {
                    engine.print(input);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}
