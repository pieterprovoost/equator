package be.pieterprovoost.equator;

/**
 * Class OperatorComparator.
 */
public class OperatorComparator {

    public static int compare(OperatorType o1, OperatorType o2) {
        return o1.getPrecedence() - o2.getPrecedence();
    }

}
