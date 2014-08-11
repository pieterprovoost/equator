package be.pieterprovoost.equator;

/**
 * Class Main.
 */
public class Main {

    public static void main(String[] args) {

        EquationEngine engine = new EquationEngine();

        engine.evaluate("x = 3 * (0-2)");
        engine.evaluate("x = 3 * -2");

    }

}
