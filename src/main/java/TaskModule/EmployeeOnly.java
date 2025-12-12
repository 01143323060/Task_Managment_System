package TaskModule;

import AdminModule.Employee;
import DataBase.ArrayLists;
import java.util.ArrayList;

public class EmployeeOnly {

    public static void showMyTasks(Employee employee) {
        System.out.println("=== My Tasks - " + employee.getName() + " ===");
        ArrayList<TaskPage> myTasks = new ArrayList<>();
        for (TaskPage task : ArrayLists.tasks) {
            if (task.getAssignedEmp() != null && task.getAssignedEmp().getID() == employee.getID()) {
                myTasks.add(task);
            }
        }

        if (myTasks.isEmpty()) {
            System.out.println("You have no assigned tasks.");
            return;
        }

        for (TaskPage t : myTasks) {
            System.out.println("ID: " + t.getTaskId() + " | " + t.getTitle() +
                    " | Project: " + (t.getProject() != null ? t.getProject().getTitle() : "None") +
                    " | Phase: " + (t.getTaskPhase() != null ? t.getTaskPhase().getphase() : "None"));
        }
    }
}