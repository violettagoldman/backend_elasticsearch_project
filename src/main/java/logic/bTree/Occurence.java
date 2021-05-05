package logic.bTree;

public class Occurence {
    public final int id;
    private Entry before = null;
    private Entry after = null;

    public Occurence(int id){
        this.id = id;
    }

    public Entry getBefore() {
        return before;
    }

    public void setBefore(Entry before) {
        this.before = before;
    }

    public Entry getAfter() {
        return after;
    }

    public void setAfter(Entry after) {
        this.after = after;
    }
}
