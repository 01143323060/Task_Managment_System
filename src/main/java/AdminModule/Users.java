package AdminModule;

import DataBase.ArrayLists;
import DataBase.DatabaseManager;

public class Users {
    protected String name;
    protected String password;
    protected String role;
    protected long ID;
    protected static long counter = 0;

    public Users() {}

    public Users(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.ID = counter++;
    }

    public static void setCounter(long c) { counter = c; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public long getID() { return ID; }
    public void setID(long ID) { this.ID = ID; }

    public Users login(String username, String password) {
        for (Users u : ArrayLists.users) {
            if (u.name.equals(username) && u.password.equals(password)) return u;
        }
        for (Employee e : ArrayLists.employee) {
            if (e.getName().equals(username) && e.getPassword().equals(password)) return e;
        }
        return null;
    }
}