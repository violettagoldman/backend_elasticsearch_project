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
     * @param value
     * @param index
     * @throws NoSuchAlgorithmException
     */
    public Condition(String value, BTree index) throws NoSuchAlgorithmException {
        this.index = index;
        this.value = value;
        result = index.occurrences(value);
    }

    /**
     * calculates the result condition or condition
     * @param c
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
     * @param c
     */
    public void and(Condition c){
        result.retainAll(c.result);
        Collections.sort(result);
    }

    /**
     * return the result
     * @return
     */
    public ArrayList getResult() {
        return result;
    }

    /**
     * return the column
     * @return
     */
    public String getColumn() {
        return index.name;
    }

}
