package AdminModule;

import DataBase.*;
import TaskModule.*;
import java.util.Date;

public class Leader extends Employee {

    public Leader(String name, String password) {
        super(name, password, "Leader", "2020-01-01", 1);
    }

    public void createTask(int code, String title, String desc, Employee assignedTo,
                           TaskPhases phase, Projects project, int priority,
                           Date start, Date end, double hours) {
        TaskPage task = new TaskPage(code, title, desc, assignedTo, phase, project, priority, this, start, end, hours);
        ArrayLists.tasks.add(task);
        DatabaseManager.saveTasks();
    }

    public void viewAllTasks() {
        System.out.println("\n=== ALL TASKS (Leader View) ===");
        if (ArrayLists.tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        for (TaskPage task : ArrayLists.tasks) {
            String status = task.getTaskPhase() != null ? task.getTaskPhase().getphase() : "No Phase";
            String assigned = task.getAssignedEmp() != null ? task.getAssignedEmp().getName() : "Unassigned";
            System.out.printf("ID: %d | Title: %s | Assigned: %s | Status: %s | Due: %s%n",
                    task.getTaskId(), task.getTitle(), assigned, status,
                    new java.text.SimpleDateFormat("dd/MM/yyyy").format(task.getEndDate()));
        }
    }
}