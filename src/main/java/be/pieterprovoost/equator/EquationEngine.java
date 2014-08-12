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
    public List<Double> getValues(String input) {
        if (StringUtils.isAlphanumeric(input.trim())) {
            if (!map.containsKey(input.trim())) {
                throw new IllegalArgumentException("Variable " + input.trim() + " is not set");
            }
            return map.get(input.trim()).getValues();
        } else {
            List<Token> queue = EquationParser.parse(input);
            return(process(queue).getValues());
        }
    }

    /**
     * Prints the value of a variable or expression.
     *
     * @param input variable name or expression
     */
    public void print(String input) {
        List<Double> values = getValues(input);
        System.out.println(StringUtils.join(values, ", "));
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
                    token.add(Math.PI);
                } else {
                    if (!map.containsKey(token.getName())) {
                        throw new RuntimeException("Variable " + token.getName() + " is not set");
                    }
                    token = map.get(token.getName());
                }
                stack.push(token);
            } else if (token.getType() == TokenType.OPERATOR) {
                List<Token> operands = new ArrayList<Token>();
                for (int o = 0; o < token.getOperatorType().getOperands(); o++) {
                    if (stack.empty()) {
                        throw new RuntimeException("Missing operand");
                    }
                    operands.add(stack.pop());
                }
                stack.push(token.getOperatorType().calculate(operands));
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
