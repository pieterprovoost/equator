package be.pieterprovoost.equator;

/**
 * Class Main.
 */
public class Main {

    public static void main(String[] args) {

        EquationEngine engine = new EquationEngine();

        //engine.evaluate("x = -2");
        engine.evaluate("x = 3 * -2");
        //engine.evaluate("x = sin(-2)");

        engine.print("x");

    }

}
