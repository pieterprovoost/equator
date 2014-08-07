package be.pieterprovoost.equator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class EquationEngineClass.
 */
public class EquationEngineTest {

    @Test
    public void evaluateTest() {

        EquationEngine engine = new EquationEngine();

        engine.evaluate("a = pi");
        engine.evaluate("x = cos(a)");
        assertEquals(engine.evaluateVariable("x"), -1, 0.01);

        engine.evaluate("y = abs(x)");
        assertEquals(engine.evaluateVariable("y"), 1, 0.01);

        engine.evaluate("z = 2 + 3 * 5");
        assertEquals(engine.evaluateVariable("z"), 17, 1);

    }

}
