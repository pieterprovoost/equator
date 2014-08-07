package be.pieterprovoost.equator;

/**
 * Class Token.
 */
public class Token {

    private Double value;
    private String name;
    private TokenType type;
    private OperatorType operatorType;
    private FunctionType functionType;

    /**
     * Constructor, sets token type, operator type and function type.
     *
     * @param name token name
     */
    public Token(String name) {
        this.name = name;
        if (TokenType.functions.contains(name)) {
            this.type = TokenType.FUNCTION;
            for (FunctionType type : FunctionType.values()) {
                if (type.getSymbol().equals(this.getName())) {
                    this.functionType = type;
                    break;
                }
            }
        } else if (TokenType.operators.contains(name)) {
            this.type = TokenType.OPERATOR;
            for (OperatorType type : OperatorType.values()) {
                if (type.getSymbol().equals(this.getName())) {
                    this.operatorType = type;
                    break;
                }
            }
        } else if (TokenType.isNumeric(name)) {
            this.type = TokenType.VALUE;
            this.value = Double.parseDouble(name);
        } else if (TokenType.separators.contains(name)) {
            this.type = TokenType.SEPARATOR;
        } else if (TokenType.closings.contains(name)) {
            this.type = TokenType.CLOSING;
        } else if (TokenType.openings.contains(name)) {
            this.type = TokenType.OPENING;
        } else {
            this.type = TokenType.VARIABLE;
        }
    }

    /**
     * Determines operator precedence.
     *
     * @param other other token
     * @return true if this operator precedes the other operator
     */
    public boolean precedes(Token other) {
        return OperatorComparator.compare(this.getOperatorType(), other.getOperatorType()) > 0;
    }

    public String toString() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public FunctionType getFunctionType() {
        return functionType;
    }
}
