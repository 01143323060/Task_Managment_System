package AdminModule;

import DataBase.ArrayLists;

public class Projects {
    private String title;
    private long ID;
    private long phaseID;
    private static long counter = 0;

    public Projects() {}

    public Projects(String title, long phaseID) {
        this.title = title;
        this.ID = counter++;
        this.phaseID = phaseID;
    }

    public static void setCounter(long c) { counter = c; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public long getID() { return ID; }
    public void setID(long ID) { this.ID = ID; }
    public long getPhaseID() { return phaseID; }
    public void setPhaseID(long phaseID) { this.phaseID = phaseID; }
}