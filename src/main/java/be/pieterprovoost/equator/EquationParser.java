package be.pieterprovoost.equator;

import java.util.*;

/**
 * Class EquationParser.
 */
public class EquationParser {

    /**
     * Parses an equation into a Reverse Polish Notation queue.
     *
     * @param equation
     * @return token queue
     */
    public static List<Token> parse(String equation) {

        Stack<Token> stack = new Stack<Token>();
        List<Token> queue = new ArrayList<Token>();

        StringTokenizer st = new StringTokenizer(equation, "+-*/()^, ", true);
        while (st.hasMoreTokens()) {
            Token token = new Token(st.nextToken().trim());
            if (!token.getName().isEmpty()) {
                if (token.getType() == TokenType.VALUE || token.getType() == TokenType.VARIABLE) {
                    queue.add(token);
                } else if (token.getType() == TokenType.FUNCTION) {
                    stack.push(token);
                } else if (token.getType() == TokenType.SEPARATOR) {
                    while (!stack.empty() && stack.peek().getType() != TokenType.OPENING) {
                        queue.add(stack.pop());
                    }
                } else if (token.getType() == TokenType.OPERATOR) {
                    while (!stack.empty() && stack.peek().getType() == TokenType.OPERATOR
                            && ((token.getOperatorType().isLeft() && !token.precedes(stack.peek()))
                            || (stack.peek().precedes(token)))) {
                        queue.add(stack.pop());
                    }
                    stack.push(token);
                } else if (token.getType() == TokenType.OPENING) {
                    stack.push(token);
                } else if (token.getType() == TokenType.CLOSING) {
                    while (!stack.empty() && stack.peek().getType() != TokenType.OPENING) {
                        queue.add(stack.pop());
                    }
                    if (stack.empty()) {
                        throw new IllegalArgumentException("Parenthesis mismatch");
                    }
                    stack.pop();
                    if (!stack.empty() && stack.peek().getType() == TokenType.FUNCTION) {
                        queue.add(stack.pop());
                    }
                }
            }
        }

        while (!stack.empty()) {
            if (stack.peek().getType() == TokenType.OPENING) {
                throw new IllegalArgumentException("Parenthesis mismatch");
            }
            queue.add(stack.pop());
        }

        return queue;
    }
}
