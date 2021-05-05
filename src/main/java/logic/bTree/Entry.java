package logic.bTree;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Entry {
    private int key;
    private String data;
    private Map occurrences;

    public Entry(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        byte byteData[] = md.digest();
        key = ByteBuffer.wrap(byteData).getInt();
        this.data = data;
        occurrences = new HashMap<Integer, Occurence>();

    }

    public int getKey() { return key; }

    public String getData() {
        return data;
    }

    public Map getOccurrences(){ return occurrences; }

    public void setKey(int newKey) {
        key = newKey;
    }

    public String toString(){
        String str = "key : "+key+" | data : "+data+" | Occurrence [";
//        for (Object occurence:
//             occurrences) {
//            str = str + ((Occurence)occurence).id + " ; ";
//        }
        str = str + "]";
        return str;
    }
}
