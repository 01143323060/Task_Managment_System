package AdminModule;
public class EmployeeType {
    private long  idtype;
    private String name;
    private static long counter = 0;
    public static void setCounter(long c) {
        counter = c;
    }
    public EmployeeType(String Name) {
        this.idtype = counter;
        this.name = Name;
    }
    public static  long getID() {
        return counter;
    }
    public long getId() {
        return idtype;
    }
    public String getname() {
        return name;
    }
    public void setName(String Name) {
        this.name = Name;
    }
}