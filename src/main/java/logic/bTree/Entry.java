package logic.bTree;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Entry {
    private int key;
    private String data;
    private Map<Integer, Occurence> occurrences;

    public Entry(String data, int id) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        byte byteData[] = md.digest();
        key = ByteBuffer.wrap(byteData).getInt();
        this.data = data;
        occurrences = new HashMap<Integer, Occurence>();
        occurrences.put(id, new Occurence());
    }

    public int getKey() { return key; }

    public String getData() {
        return data;
    }

    public Map getOccurrences(){ return occurrences; }

    public void setKey(int newKey) {
        key = newKey;
    }

    public List<Integer> getOccurrencesList(){
        List<Integer> result = new ArrayList<>();
        for (Map.Entry entry: occurrences.entrySet()) {
            result.add((int)entry.getKey());
        }
        return result;
    }

    public String toString(){
        String str = "key : "+key+" | data : "+data+" | Occurrence [ ";
        for (Map.Entry occurence: occurrences.entrySet())
            str = str + occurence.getKey() + " ; ";
        str = str.substring(0, str.length()-2);
        str = str + "]\n";
        return str;
    }
}
