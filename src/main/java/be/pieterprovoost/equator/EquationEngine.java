package be.pieterprovoost.equator;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Class EquationEngine.
 */
public class EquationEngine {

    private Map<String, Token> map = new HashMap<String, Token>();
    private Stack<Token> stack = new Stack<Token>();

    /**
     * Evaluates an equation. If the input is a single variable name, its value is printed.
     *
     * @param input equation or variable name
     */
    public void evaluate(String input) {
        String[] parts = input.split("=");
        if (parts.length == 2) {
            String variable = parts[0].trim();
            String expression = parts[1].trim();
            if (!StringUtils.isAlphanumeric(variable)) {
                throw new IllegalArgumentException("Equation must be of the form variable = expression");
            }
            List<Token> queue = EquationParser.parse(expression);
            Token result = process(queue);
            map.put(variable, result);
        } else {
            throw new IllegalArgumentException("Equation must be of the form variable = expression");
        }
    }

    /**
     * Returns the value of a variable or expression.
     *
     * @param input variable name or expression
     * @return value
     */
    public Double getValue(String input) {
        if (StringUtils.isAlphanumeric(input.trim())) {
            if (!map.containsKey(input.trim())) {
                throw new IllegalArgumentException("Variable " + input.trim() + " does not exist");
            }
            return map.get(input.trim()).getValue();
        } else {
            List<Token> queue = EquationParser.parse(input);
            return(process(queue).getValue());
        }
    }

    /**
     * Prints the value of a variable or expression.
     *
     * @param input variable name or expression
     */
    public void printValue(String input) {
        System.out.println(getValue(input));
    }

    /**
     * Processes a Reverse Polish Notation queue.
     *
     * @param queue token queue
     * @return result
     */
    private Token process(List<Token> queue) {
        stack.clear();
        for (int i = 0; i < queue.size(); i++) {
            Token token = queue.get(i);
            if (token.getType() == TokenType.VALUE) {
                stack.push(token);
            } else if (token.getType() == TokenType.VARIABLE) {
                if (token.getName().toLowerCase().equals("pi")) {
                    token.setValue(Math.PI);
                } else {
                    token = map.get(token.getName());
                }
                stack.push(token);
            } else if (token.getType() == TokenType.OPERATOR) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Missing operand");
                }
                Token second = stack.pop();
                Token first = stack.pop();
                Token result = new Token("");

                if (first.isSingle() && second.isSingle()) {
                    result.setValue(token.getOperatorType().calculate(first.getValue(), second.getValue()));
                } else if (first.isSingle()) {
                    for (Double value : second.getValues()) {
                        result.getValues().add(token.getOperatorType().calculate(first.getValue(), value));
                    }
                } else if (second.isSingle()) {
                    for (Double value : first.getValues()) {
                        result.getValues().add(token.getOperatorType().calculate(value, second.getValue()));
                    }
                } else {
                    for (int v = 0; v < first.getValues().size(); v++) {
                        result.getValues().add(token.getOperatorType().calculate(first.getValues().get(v), second.getValues().get(v)));
                    }
                }

                stack.push(result);
            } else if (token.getType() == TokenType.FUNCTION) {
                List<Token> arguments = new ArrayList<Token>();
                for (int a = 0; a < token.getFunctionType().getArguments(); a++) {
                    arguments.add(stack.pop());
                }
                stack.push(token.getFunctionType().calculate(arguments));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Missing operator");
        }
        return stack.get(0);
    }

    /**
     * Clears the value map.
     */
    public void clear() {
        map.clear();
    }

}
