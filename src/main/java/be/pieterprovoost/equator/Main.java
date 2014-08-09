package be.pieterprovoost.equator;

/**
 * Class Main.
 */
public class Main {

    public static void main(String[] args) {
        EquationEngine engine = new EquationEngine();
        engine.evaluate("x = 3");
        engine.evaluate("y = x^2");
        engine.printValue("y - 1");
    }

}
