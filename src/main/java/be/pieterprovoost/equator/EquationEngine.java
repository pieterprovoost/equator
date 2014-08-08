package be.pieterprovoost.equator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Class EquationEngine.
 */
public class EquationEngine {

    private Map<String, Double> map = new HashMap<String, Double>();
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
            Double result = process(queue);
            map.put(variable, result);
        } else {
            throw new IllegalArgumentException("Equation must be of the form variable = expression");
        }
    }

    /**
     * Returns the value of a variable.
     *
     * @param input variable name
     * @return variable value
     */
    public Double getValue(String input) {
        return map.get(input.trim());
    }

    /**
     * Prints the value of a variable.
     *
     * @param input variable name
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
    private Double process(List<Token> queue) {
        stack.clear();
        for (int i = 0; i < queue.size(); i++) {
            Token token = queue.get(i);
            if (token.getType() == TokenType.VALUE) {
                stack.push(token);
            } else if (token.getType() == TokenType.VARIABLE) {
                if (token.getName().toLowerCase().equals("pi")) {
                    token.setValue(Math.PI);
                } else {
                    token.setValue(map.get(token.getName()));
                }
                stack.push(token);
            } else if (token.getType() == TokenType.OPERATOR) {
                Token second = stack.pop();
                Token first = stack.pop();
                Token result = new Token("");

                if (token.getOperatorType() == OperatorType.ADD) {
                    result.setValue(first.getValue() + second.getValue());
                } else if (token.getOperatorType() == OperatorType.SUBTRACT) {
                    result.setValue(first.getValue() - second.getValue());
                } else if (token.getOperatorType() == OperatorType.DIVIDE) {
                    result.setValue(first.getValue() / second.getValue());
                } else if (token.getOperatorType() == OperatorType.MULTIPLY) {
                    result.setValue(first.getValue() * second.getValue());
                } else if (token.getOperatorType() == OperatorType.MOD) {
                    result.setValue(first.getValue() % second.getValue());
                } else if (token.getOperatorType() == OperatorType.POWER) {
                    result.setValue(Math.pow(first.getValue(), second.getValue()));
                }

                stack.push(result);
            } else if (token.getType() == TokenType.FUNCTION) {
                Token argument = stack.pop();
                Token result = new Token("");

                if (token.getFunctionType() == FunctionType.COS) {
                    result.setValue(Math.cos(argument.getValue()));
                } else if (token.getFunctionType() == FunctionType.COS) {
                    result.setValue(Math.cos(argument.getValue()));
                } else if (token.getFunctionType() == FunctionType.SIN) {
                    result.setValue(Math.sin(argument.getValue()));
                } else if (token.getFunctionType() == FunctionType.EXP) {
                    result.setValue(Math.exp(argument.getValue()));
                } else if (token.getFunctionType() == FunctionType.ASIN) {
                    result.setValue(Math.asin(argument.getValue()));
                } else if (token.getFunctionType() == FunctionType.ACOS) {
                    result.setValue(Math.acos(argument.getValue()));
                } else if (token.getFunctionType() == FunctionType.ATAN) {
                    result.setValue(Math.atan(argument.getValue()));
                } else if (token.getFunctionType() == FunctionType.CEIL) {
                    result.setValue(Math.ceil(argument.getValue()));
                } else if (token.getFunctionType() == FunctionType.FLOOR) {
                    result.setValue(Math.floor(argument.getValue()));
                } else if (token.getFunctionType() == FunctionType.ABS) {
                    result.setValue(Math.abs(argument.getValue()));
                }

                stack.push(result);
            }
        }
        return stack.get(0).getValue();
    }

    /**
     * Clears the value map.
     */
    public void clear() {
        map.clear();
    }

}
