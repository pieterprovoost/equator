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
                for (Double x : arguments.get(0).getValues()) {
                    result.add(Math.sin(x));
                }
                break;
            case COS:
                for (Double x : arguments.get(0).getValues()) {
                    result.add(Math.cos(x));
                }
                break;
            case EXP:
                for (Double x : arguments.get(0).getValues()) {
                    result.add(Math.exp(x));
                }
                break;
            case ABS:
                for (Double x : arguments.get(0).getValues()) {
                    result.add(Math.abs(x));
                }
                break;
            case ACOS:
                for (Double x : arguments.get(0).getValues()) {
                    result.add(Math.acos(x));
                }
                break;
            case ASIN:
                for (Double x : arguments.get(0).getValues()) {
                    result.add(Math.asin(x));
                }
                break;
            case ATAN:
                for (Double x : arguments.get(0).getValues()) {
                    result.add(Math.atan(x));
                }
                break;
            case CEIL:
                for (Double x : arguments.get(0).getValues()) {
                    result.add(Math.ceil(x));
                }
                break;
            case FLOOR:
                for (Double x : arguments.get(0).getValues()) {
                    result.add(Math.floor(x));
                }
                break;
            case SEQ:
                Double start = arguments.get(2).getValues().get(0);
                Double end = arguments.get(1).getValues().get(0);
                Double step = arguments.get(0).getValues().get(0);
                for (Double x = start; x <= end; x = x + step) {
                    result.getValues().add(x);
                }
                break;
            case SUM:
                Double sum = 0.0;
                for (Double value : arguments.get(0).getValues()) {
                    sum = sum + value;
                }
                result.add(sum);
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
