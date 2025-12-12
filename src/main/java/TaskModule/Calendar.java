package TaskModule;

import AdminModule.*;
import DataBase.ArrayLists;
import java.text.SimpleDateFormat;

public class Calendar {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void showWeeklyCalendar() {
        System.out.println("\n=== WEEKLY TASK CALENDAR ===");
        System.out.println("Employee             | Task Title                 | Project        | Phase        | Due Date");
        System.out.println("-------------------------------------------------------------------------------------------");

        for (TaskPage task : ArrayLists.tasks) {
            String empName = task.getAssignedEmp() != null ? task.getAssignedEmp().getName() : "Unassigned";
            String title = task.getTitle().length() > 25 ? task.getTitle().substring(0, 22) + "..." : task.getTitle();
            String project = task.getProject() != null ? task.getProject().getTitle() : "No Project";
            String phase = task.getTaskPhase() != null ? task.getTaskPhase().getphase() : "No Phase";
            String due = sdf.format(task.getEndDate());

            System.out.printf("%-20s | %-25s | %-14s | %-12s | %s%n",
                    empName, title, project, phase, due);
        }
    }

    public static void showEmployeeTasks(Employee emp) {
        System.out.println("\nTasks for: " + emp.getName());
        for (TaskPage t : ArrayLists.tasks) {
            if (t.getAssignedEmp() != null && t.getAssignedEmp().getID() == emp.getID()) {
                System.out.println(" • " + t.getTitle() + " → Due: " + sdf.format(t.getEndDate()));
            }
        }
    }
}