package be.pieterprovoost.equator;

/**
 * Class Main.
 */
public class Main {

    public static void main(String[] args) {

        EquationEngine engine = new EquationEngine();

        engine.evaluate("x = seq(1, 2, 0.5)");
        engine.evaluate("y = exp(x)");
        engine.printValue("sum(y)");

    }

}
