package be.pieterprovoost.equator;

import com.sun.deploy.util.StringUtils;

import java.util.*;

/**
 * Class Util.
 */
public class Util {

    /**
     * Generates a string representation of a token queue or stack.
     *
     * @param collection token queue or stack
     * @return string representation
     */
    public static String print(Collection<Token> collection) {
        List<String> parts = new ArrayList<String>();
        Iterator<Token> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Token token = iterator.next();
            if (token.getValue() != null) {
                parts.add(token.getName() + " (" + token.getValue() + ")");
            } else {
                parts.add(token.getName());
            }
        }
        return(StringUtils.join(parts, " "));
    }

}
