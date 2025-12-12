package com.mycompany.task_managment_system;

import AdminModule.Employee;
import DataBase.ArrayLists;
import DataBase.DatabaseManager;
import gui.LoginPage;

public class Task_Managment_System {
    public static void main(String[] args) {
    // Load data when program starts
        DatabaseManager.init();
        
        if (ArrayLists.employee.isEmpty()) {
            System.out.println("No users found! Creating default accounts...");

            Employee admin = new Employee("admin", "123", "Admin", "2020-01-01", 0);
            Employee leader = new Employee("mohamed", "123", "Leader", "2022-01-01", 1);
            Employee ahmed = new Employee("ahmed", "123", "Employee", "2023-06-01", 1);
            Employee sara = new Employee("sara", "123", "Employee", "2023-07-01", 1);

            ArrayLists.employee.add(admin);
            ArrayLists.employee.add(leader);
            ArrayLists.employee.add(ahmed);
            ArrayLists.employee.add(sara);

            // Save data in files
            DatabaseManager.saveEmployees();
            System.out.println("Default users created! (admin/123, mohamed/123, ahmed/123)");
        }
        
    // Open LoginPage
        java.awt.EventQueue.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }
}
