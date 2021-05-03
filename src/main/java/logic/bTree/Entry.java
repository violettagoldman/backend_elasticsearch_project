package logic.bTree;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Entry {
    private int key;
    private String data;
    private ArrayList occurrences;

    public Entry(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        byte byteData[] = md.digest();
        key = ByteBuffer.wrap(byteData).getInt();
        this.data = data;
        occurrences = new ArrayList();
    }

    public int getKey() { return key; }

    public String getData() {
        return data;
    }

    public ArrayList getOccurrences(){ return occurrences; }

    public void setKey(int newKey) {
        key = newKey;
    }

    public String toString(){
        return "key : "+key+" | data : "+data+" | Occurrence "+occurrences;
    }
}
