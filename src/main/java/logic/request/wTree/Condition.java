package logic.request.wTree;

import logic.Column;
import logic.bTree.BTree;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Condition {
    public final String value;
    public final BTree index;
    private ArrayList result;

    public Condition(String value, BTree index) throws NoSuchAlgorithmException {
        this.index = index;
        this.value = value;
        result = index.occurences(value);
    }

    public void or(Condition c){
        if(c == null)return;
        result.addAll(c.result);
        Set set = new HashSet(result);
        result = new ArrayList(set);
        Collections.sort(result);
    }

    public void and(Condition c){
        result.retainAll(c.result);
        Collections.sort(result);
    }

    public ArrayList getResult() {
        return result;
    }

    public String getColumn() {
        return index.name;
    }


//    public Condition(String value, Column column) throws NoSuchAlgorithmException {
//        this.value = value;
//        result = column.getOccurences(value);
//    }

}
