package be.pieterprovoost.equator;

import java.util.List;

/**
 * Enum OperatorType.
 */
public enum OperatorType {

    ADD(2, "+", 0, true),
    SUBTRACT(2, "-", 0, true),
    MINUS(1, "-", 3, false),
    DIVIDE(2, "/", 1, true),
    MOD(2, "%", 1, true),
    MULTIPLY(2, "*", 1, true),
    POWER(2, "^", 2, false);

    private Integer operands;
    private String symbol;
    private int precedence;
    private boolean left;

    OperatorType(Integer operands, String symbol, int precedence, boolean left) {
        this.symbol = symbol;
        this.precedence = precedence;
        this.left = left;
        this.operands = operands;
    }

    public Token calculate(List<Token> operands) {
        Token result = new Token();
        switch (this) {
            case ADD:
                if (operands.get(0).isSingle() && operands.get(1).isSingle()) {
                    result.add(operands.get(0).getValues().get(0) + operands.get(1).getValues().get(0));
                } else if (operands.get(0).isSingle()) {
                    for (Double value : operands.get(1).getValues()) {
                        result.add(operands.get(0).getValues().get(0) + value);
                    }
                } else if (operands.get(1).isSingle()) {
                    for (Double value : operands.get(0).getValues()) {
                        result.add(value + operands.get(1).getValues().get(0));
                    }
                } else {
                    if (operands.get(0).getValues().size() != operands.get(1).getValues().size()) {
                        throw new RuntimeException("Operands must have the same size");
                    }
                    for (int i = 0; i < operands.get(0).getValues().size(); i++) {
                        result.add(operands.get(0).getValues().get(i) + operands.get(1).getValues().get(i));
                    }
                }
                break;
            case SUBTRACT:
                if (operands.get(0).isSingle() && operands.get(1).isSingle()) {
                    result.add(operands.get(0).getValues().get(0) - operands.get(1).getValues().get(0));
                } else if (operands.get(0).isSingle()) {
                    for (Double value : operands.get(1).getValues()) {
                        result.add(operands.get(0).getValues().get(0) - value);
                    }
                } else if (operands.get(1).isSingle()) {
                    for (Double value : operands.get(0).getValues()) {
                        result.add(value - operands.get(1).getValues().get(0));
                    }
                } else {
                    if (operands.get(0).getValues().size() != operands.get(1).getValues().size()) {
                        throw new RuntimeException("Operands must have the same size");
                    }
                    for (int i = 0; i < operands.get(0).getValues().size(); i++) {
                        result.add(operands.get(0).getValues().get(i) - operands.get(1).getValues().get(i));
                    }
                }
                break;
            case DIVIDE:
                if (operands.get(0).isSingle() && operands.get(1).isSingle()) {
                    result.add(operands.get(0).getValues().get(0) / operands.get(1).getValues().get(0));
                } else if (operands.get(0).isSingle()) {
                    for (Double value : operands.get(1).getValues()) {
                        result.add(operands.get(0).getValues().get(0) / value);
                    }
                } else if (operands.get(1).isSingle()) {
                    for (Double value : operands.get(0).getValues()) {
                        result.add(value / operands.get(1).getValues().get(0));
                    }
                } else {
                    if (operands.get(0).getValues().size() != operands.get(1).getValues().size()) {
                        throw new RuntimeException("Operands must have the same size");
                    }
                    for (int i = 0; i < operands.get(0).getValues().size(); i++) {
                        result.add(operands.get(0).getValues().get(i) / operands.get(1).getValues().get(i));
                    }
                }
                break;
            case MOD:
                if (operands.get(0).isSingle() && operands.get(1).isSingle()) {
                    result.add(operands.get(0).getValues().get(0) % operands.get(1).getValues().get(0));
                } else if (operands.get(0).isSingle()) {
                    for (Double value : operands.get(1).getValues()) {
                        result.add(operands.get(0).getValues().get(0) % value);
                    }
                } else if (operands.get(1).isSingle()) {
                    for (Double value : operands.get(0).getValues()) {
                        result.add(value % operands.get(1).getValues().get(0));
                    }
                } else {
                    if (operands.get(0).getValues().size() != operands.get(1).getValues().size()) {
                        throw new RuntimeException("Operands must have the same size");
                    }
                    for (int i = 0; i < operands.get(0).getValues().size(); i++) {
                        result.add(operands.get(0).getValues().get(i) % operands.get(1).getValues().get(i));
                    }
                }
                break;
            case MULTIPLY:
                if (operands.get(0).isSingle() && operands.get(1).isSingle()) {
                    result.add(operands.get(0).getValues().get(0) * operands.get(1).getValues().get(0));
                } else if (operands.get(0).isSingle()) {
                    for (Double value : operands.get(1).getValues()) {
                        result.add(operands.get(0).getValues().get(0) * value);
                    }
                } else if (operands.get(1).isSingle()) {
                    for (Double value : operands.get(0).getValues()) {
                        result.add(value * operands.get(1).getValues().get(0));
                    }
                } else {
                    if (operands.get(0).getValues().size() != operands.get(1).getValues().size()) {
                        throw new RuntimeException("Operands must have the same size");
                    }
                    for (int i = 0; i < operands.get(0).getValues().size(); i++) {
                        result.add(operands.get(0).getValues().get(i) * operands.get(1).getValues().get(i));
                    }
                }
                break;
            case POWER:
                if (operands.get(0).isSingle() && operands.get(1).isSingle()) {
                    result.add(Math.pow(operands.get(0).getValues().get(0), operands.get(1).getValues().get(0)));
                } else if (operands.get(0).isSingle()) {
                    for (Double value : operands.get(1).getValues()) {
                        result.add(Math.pow(operands.get(0).getValues().get(0), value));
                    }
                } else if (operands.get(1).isSingle()) {
                    for (Double value : operands.get(0).getValues()) {
                        result.add(Math.pow(value, operands.get(1).getValues().get(0)));
                    }
                } else {
                    if (operands.get(0).getValues().size() != operands.get(1).getValues().size()) {
                        throw new RuntimeException("Operands must have the same size");
                    }
                    for (int i = 0; i < operands.get(0).getValues().size(); i++) {
                        result.add(Math.pow(operands.get(0).getValues().get(i), operands.get(1).getValues().get(i)));
                    }
                }
                break;
            case MINUS:
                for (int i = 0; i < operands.get(0).getValues().size(); i++) {
                    result.add(-operands.get(0).getValues().get(i));
                }
                break;
        }
        return result;
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

    public Integer getOperands() {
        return operands;
    }
}
