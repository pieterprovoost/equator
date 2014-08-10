package be.pieterprovoost.equator;

/**
 * Class Main.
 */
public class Main {

    public static void main(String[] args) {

        EquationEngine engine = new EquationEngine();

        engine.evaluate("x = seq(1, 2, 0.5)");
        engine.printValue("x");
        engine.evaluate("y = exp(x)");
        engine.printValue("y");
        engine.printValue("sum(y)");

    }

}
