package AdminModule;

import DataBase.ArrayLists;

public class Employee extends Users {

    private String hireDate;
    private long typeID;
    private static long empCounter = 0;

    public Employee() {
        super();
    }

    public Employee(String name, String password, String role, String hireDate, long typeID) {
        super(name, password, role);
        this.ID = empCounter++;
        this.hireDate = hireDate;
        this.typeID = typeID;
    }

    // مهم جدًا لما بنحمل من الملف
    public static void setEmpCounter(long counter) {
        empCounter = counter;
    }

    // Getters & Setters
    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }

    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}