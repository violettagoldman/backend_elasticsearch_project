package logic.request.wTree;

import logic.IndexBTree.BTree;

import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * implements a condition (column = value)
 */
public class Condition {
    public final String value;
    public final BTree index;
    private ArrayList result;

    /**
     * constructor
     * @param value the data
     * @param index the column
     */
    public Condition(String value, BTree index){
        this.index = index;
        this.value = value;
        result = index.occurrences(value);
    }

    /**
     * calculates the result condition or condition
     * @param c the condition to add (OR)
     */
    public void or(Condition c){
        if(c == null)return;
        result.addAll(c.result);
        Set set = new HashSet(result);
        result = new ArrayList(set);
        Collections.sort(result);
    }

    /**
     * calculates the result condition and condition
     * @param c the condition to add (AND)
     */
    public void and(Condition c){
        result.retainAll(c.result);
        Collections.sort(result);
    }

    /**
     * return the result
     * @return the result (rows)
     */
    public ArrayList getResult() {
        return result;
    }

    /**
     * return the column
     * @return return the column
     */
    public String getColumn() {
        return index.name;
    }

}
