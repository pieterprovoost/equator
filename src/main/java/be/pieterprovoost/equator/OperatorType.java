package be.pieterprovoost.equator;

/**
 * Enum OperatorType.
 */
public enum OperatorType {

    ADD("+", 0, true),
    SUBTRACT("-", 0, true),
    DIVIDE("/", 1, true),
    MOD("%", 1, true),
    MULTIPLY("*", 1, true),
    POWER("^", 2, false);

    private int precedence;
    private boolean left;
    private String symbol;

    OperatorType(String symbol, int precedence, boolean left) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.left = left;
    }

    public Double calculate(Double first, Double second) {
        switch (this) {
            case ADD:
                return first + second;
            case SUBTRACT:
                return first - second;
            case DIVIDE:
                return first / second;
            case MOD:
                return first % second;
            case MULTIPLY:
                return first * second;
            case POWER:
                return Math.pow(first, second);
            default:
                return null;
        }
    }

    public int getPrecedence() {
        return precedence;
    }

    public boolean isLeft() {
        return left;
    }

    public String getSymbol() {
        return symbol;
    }
}
