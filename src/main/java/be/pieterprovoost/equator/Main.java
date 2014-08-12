package be.pieterprovoost.equator;

/**
 * Class Main.
 */
public class Main {

    public static void main(String[] args) {

        EquationEngine engine = new EquationEngine();

        engine.evaluate("x = seq(-pi, pi, 0.5)");
        engine.evaluate("y = sin(x)");
        engine.print("x");
        engine.print("y");

    }

}
