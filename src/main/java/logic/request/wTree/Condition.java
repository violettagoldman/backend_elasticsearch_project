package logic.request.wTree;

import logic.Column;
import logic.bTree.BTree;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Condition extends WNode {
    private List<Integer> result;

    public Condition(String value, BTree index) throws NoSuchAlgorithmException {
        super(true);
        result = index.occurences(value);
    }

    public Condition(String value, Column column) throws NoSuchAlgorithmException {
        super(true);
        result = column.getOccurences(value);
    }

}
