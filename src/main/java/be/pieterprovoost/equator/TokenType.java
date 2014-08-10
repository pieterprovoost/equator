package be.pieterprovoost.equator;

import java.util.Arrays;
import java.util.List;

/**
 * Enum TokenType.
 */
public enum TokenType {

    FUNCTION, OPERATOR, VARIABLE, VALUE, SEPARATOR, OPENING, CLOSING;

    public static List<String> functions = Arrays.asList("sum", "seq", "log", "exp", "sin", "cos", "sqrt", "abs", "acos", "asin", "atan", "ceil", "floor");
    public static List<String> operators = Arrays.asList("+", "-", "*", "/", "^", "%");
    public static List<String> separators = Arrays.asList(",");
    public static List<String> openings = Arrays.asList("(");
    public static List<String> closings = Arrays.asList(")");

    /**
     * Determines if a string is numeric.
     *
     * @param s string
     * @return true if the string is numeric
     */
    public static boolean isNumeric(String s) {
        try {
            double d = Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

}
