package be.pieterprovoost.equator;

/**
 * Class Main.
 */
public class Main {

    public static void main(String[] args) {

        EquationEngine engine = new EquationEngine();
        engine.evaluate("a = pi");
        engine.evaluate("x = cos(a)");
        engine.evaluate("x");

    }

}
