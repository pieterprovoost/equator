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
        assertEquals(engine.getValue("x").get(0), -1, 0.01);

        engine.evaluate("y = abs(x)");
        assertEquals(engine.getValue("y").get(0), 1, 0.01);

        engine.evaluate("z = 2 + 3 * 5");
        assertEquals(engine.getValue("z").get(0), 17, 1);

        try {
            engine.evaluate("k = 1 2");
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Missing operator"));
        }

        try {
            engine.evaluate("k = 2 *");
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Missing operand"));
        }

        try {
            engine.evaluate("k = (2 * 3)) + 4");
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Parenthesis mismatch"));
        }

    }

}
