package AdminModule;

public class TaskPhases {
    private String phase;
    private long id;
    private static long counter = 0;

    public TaskPhases() {}

    public TaskPhases(String phase) {
        this.phase = phase;
        this.id = counter++;
    }

    public static void setCounter(long c) { counter = c; }

    public String getphase() { return phase; }
    public void setPhase(String phase) { this.phase = phase; }
    public long getid() { return id; }
    public void setid(long id) { this.id = id; }
}