package AdminModule;

import DataBase.*;
import TaskModule.TaskPage;

public class Admin extends Employee {

    public Admin(String name, String password) {
        super(name, password, "Admin", "2020-01-01", 0);
    }

    public void addEmployee(String name, String password, String role, String hireDate, long typeID) {
        Employee newEmp = new Employee(name, password, role, hireDate, typeID);
        ArrayLists.employee.add(newEmp);
        DatabaseManager.saveEmployees();
        System.out.println("Employee added: " + name);
    }

    public void removeEmployee(long id) {
        boolean removed = ArrayLists.employee.removeIf(e -> e.getID() == id);
        if (removed) {
            DatabaseManager.saveEmployees();
            System.out.println("Employee with ID " + id + " removed.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public void generateReport() {
        System.out.println("\n========== SYSTEM REPORT ==========");
        System.out.println("Total Employees : " + ArrayLists.employee.size());
        System.out.println("Total Projects  : " + ArrayLists.projects.size());
        System.out.println("Total Tasks     : " + ArrayLists.tasks.size());
        System.out.println("Total Requests  : " + ArrayLists.requests.size());
        System.out.println("==================================\n");
    }

    public void viewAllTasks() {
        new Leader("temp", "temp").viewAllTasks(); 
    }
}