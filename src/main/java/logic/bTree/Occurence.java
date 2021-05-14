package logic.bTree;

public class Occurence {
    private Entry before = null;
    private Entry after = null;

    public Occurence(){

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
