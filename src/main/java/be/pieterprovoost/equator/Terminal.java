package be.pieterprovoost.equator;

import java.util.Scanner;

/**
 * Class Terminal.
 */
public class Terminal {

    public static void main(String[] args) {
        EquationEngine engine = new EquationEngine();
        Scanner in = new Scanner(System.in);

        String input = "";
        while (!"quit".equals(input.trim())) {
            System.out.print("> ");
            input = in.nextLine();
            if (input.contains("=")) {
                try {
                    engine.evaluate(input);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    engine.print(input);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

}
