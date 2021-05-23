package logic.bTree;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Entry {
    private int key;
    private String data;
    private Map<Integer, Entry> afters;

    public Map<Integer, Entry> getAfters() {
        return afters;
    }

    public Entry(String data, int id) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        byte byteData[] = md.digest();
        key = ByteBuffer.wrap(byteData).getInt();
        key = key < 0 ? key*-1 : key;
        this.data = data;
        afters = new HashMap<Integer , Entry>();
    }

    public int getKey() { return key; }

    public String getData() {
        return data;
    }

    public void setKey(int newKey) {
        key = newKey;
    }

    public ArrayList getOccurrencesList(){
        ArrayList result = new ArrayList<>();
        for (Map.Entry entry: afters.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    public String toString(){
        String str = "key : "+key+" | data : "+data+" | Occurrence [ ";
        for (Map.Entry after: afters.entrySet()){
            str = str + after.getKey() +" : ";
            Entry current = (Entry) after.getValue();
            while(current != null){
                str = str + current.data +" ; ";
                current = current.afters.get(after.getKey());
            }
            str = str.substring(0, str.length()-2) + " | ";
        }
        str = str.substring(0, str.length()-2);
        str = str + "]\n";
        return str;
    }
}
