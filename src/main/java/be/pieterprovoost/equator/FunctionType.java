package be.pieterprovoost.equator;

import java.util.List;

/**
 * Enum FunctionType.
 */
public enum FunctionType {

    SIN("sin", 1),
    COS("cos", 1),
    EXP("exp", 1),
    ABS("abs", 1),
    ACOS("acos", 1),
    ASIN("asin", 1),
    ATAN("atan", 1),
    CEIL("ceil", 1),
    FLOOR("floor", 1),
    SEQ("seq", 3),
    SUM("sum", 1);

    private String symbol;
    private Integer arguments;

    public Token calculate(List<Token> arguments) {
        Token result = new Token();
        switch (this) {
            case SIN:
                result = new Token(Math.sin(arguments.get(0).getValue()));
                break;
            case COS:
                result = new Token(Math.cos(arguments.get(0).getValue()));
                break;
            case EXP:
                result = new Token(Math.exp(arguments.get(0).getValue()));
                break;
            case ABS:
                result = new Token(Math.abs(arguments.get(0).getValue()));
                break;
            case ACOS:
                result = new Token(Math.acos(arguments.get(0).getValue()));
                break;
            case ASIN:
                result = new Token(Math.asin(arguments.get(0).getValue()));
                break;
            case ATAN:
                result = new Token(Math.atan(arguments.get(0).getValue()));
                break;
            case CEIL:
                result = new Token(Math.ceil(arguments.get(0).getValue()));
                break;
            case FLOOR:
                result = new Token(Math.floor(arguments.get(0).getValue()));
                break;
            case SEQ:
                Double start = arguments.get(2).getValue();
                Double end = arguments.get(1).getValue();
                Double step = arguments.get(0).getValue();
                for (Double x = start; x <= end; x = x + step) {
                    result.getValues().add(x);
                }
                break;
            case SUM:
                Double sum = 0.0;
                for (Double value : arguments.get(0).getValues()) {
                    sum = sum + value;
                }
                result.setValue(sum);
                break;
        }
        return result;
    }

    FunctionType(String symbol, Integer arguments) {
        this.symbol = symbol;
        this.arguments = arguments;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getArguments() {
        return arguments;
    }
}
