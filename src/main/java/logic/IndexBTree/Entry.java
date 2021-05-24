package logic.IndexBTree;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * class that implements the index entries
 */
public class Entry {
    private int key;
    private String data;
    private Map<Integer, Entry> afters;

    /**
     * constructor that transforms the data into int to create the key
     * @param data
     * @param id
     * @throws NoSuchAlgorithmException
     */
    public Entry(String data, int id) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        byte byteData[] = md.digest();
        key = ByteBuffer.wrap(byteData).getInt();
        key = key < 0 ? key*-1 : key;
        this.data = data;
        afters = new HashMap<Integer , Entry>();
    }

    /**
     * return the map of after for concatenated indexes
     * @return
     */
    public Map<Integer, Entry> getAfters() {
        return afters;
    }

    /**
     * return the key
     * @return
     */
    public int getKey() { return key; }

    /**
     * return the data
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     * chnage the key
     * @param newKey
     */
    public void setKey(int newKey) {
        key = newKey;
    }

    /**
     * returns the list of occurrences
     * @return
     */
    public ArrayList getOccurrencesList(){
        ArrayList result = new ArrayList<>();
        for (Map.Entry entry: afters.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    /**
     * returns a string that describes the entry
     * @return
     */
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
