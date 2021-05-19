package logic.request.wTree;

import logic.Column;
import logic.bTree.BTree;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        result.remove(c.result);
        result.addAll(c.result);
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
