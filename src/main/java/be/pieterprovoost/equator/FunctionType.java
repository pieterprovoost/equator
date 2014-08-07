package be.pieterprovoost.equator;

/**
 * Enum FunctionType.
 */
public enum FunctionType {

    SIN("sin"),
    COS("cos"),
    EXP("exp"),
    ABS("abs"),
    ACOS("acos"),
    ASIN("asin"),
    ATAN("atan"),
    CEIL("ceil"),
    FLOOR("floor");

    private String symbol;

    FunctionType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
