package DataBase;

import AdminModule.*;
import static DataBase.ArrayLists.tasks;
import TaskModule.*;
import EmployeeModule.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DatabaseManager {
    private static final String PATH = "Resources" + java.io.File.separator;
    public static ArrayList<TimeCard> timeCards = new ArrayList<>();

    public static void init() {
        loadEmployees();
        loadProjects();
        loadTasks();
        loadTaskLogs();
        loadRequests();
        loadTimeCards();
    }

    public static void saveAll() {
        saveEmployees();
        saveProjects();
        saveTasks();
        saveTaskLogs();
        saveRequests();
        saveTimeCardsToFile();
    }

    public static void saveEmployees() {
        List<String> lines = new ArrayList<>();
        for (Employee e : ArrayLists.employee) {
            lines.add(e.getID() + "|" + e.getName() + "|" + e.getPassword() + "|" +
                    e.getRole() + "|" + e.getHireDate() + "|" + e.getTypeID());
        }
        FileManager.writeFile(PATH + "employees.txt", lines);
    }

    public static void loadEmployees() {
        ArrayLists.employee.clear();
        List<String> lines = FileManager.readFile(PATH + "employees.txt");
        long maxId = 0;
        for (String line : lines) {
            String[] p = line.split("\\|", -1);
            if (p.length < 6)
                continue;
            Employee e = new Employee(p[1], p[2], p[3], p[4], Long.parseLong(p[5]));
            e.setID(Long.parseLong(p[0]));
            ArrayLists.employee.add(e);
            if (e.getID() > maxId)
                maxId = e.getID();
        }
        Employee.setEmpCounter(maxId + 1);
    }

    public static void saveProjects() {
        List<String> lines = new ArrayList<>();
        for (Projects p : ArrayLists.projects) {
            lines.add(p.getID() + "|" + p.getTitle() + "|" + p.getPhaseID());
        }
        FileManager.writeFile(PATH + "projects.txt", lines);
    }

    public static void loadProjects() {
        ArrayLists.projects.clear();
        List<String> lines = FileManager.readFile(PATH + "projects.txt");
        long maxId = 0;
        for (String line : lines) {
            String[] p = line.split("\\|", -1);
            if (p.length < 3)
                continue;
            Projects pr = new Projects(p[1], Long.parseLong(p[2]));
            pr.setID(Long.parseLong(p[0]));
            ArrayLists.projects.add(pr);
            if (pr.getID() > maxId)
                maxId = pr.getID();
        }
        Projects.setCounter(maxId + 1);
    }

    public static void saveTasks() {
        TaskPage.saveTasks(PATH + "tasks.txt", ArrayLists.tasks);
    }

    public static void loadTasks() {
        ArrayLists.tasks.clear();
        ArrayLists.tasks.addAll(TaskPage.loadTasks(PATH + "tasks.txt"));
    }

    private static void loadTasksIfEmpty() {
        if (tasks.isEmpty()) {
            tasks = TaskPage.loadTasks("DataBase/Tasks.txt");
        }
    }

    public static TaskPage getTaskById(int taskId) {
        loadTasksIfEmpty();
        for (TaskPage t : tasks) {
            if (t.getTaskId() == taskId) {
                return t;
            }
        }
        return null;
    }

    public static String changeTaskCode(int taskId, int newCode) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setCode(newCode);
            saveTasks();
            return "Code updated";
        }
        return "Task not found";
    }

    public static String changeTaskTitle(int taskId, String newTitle) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setTitle(newTitle);
            saveTasks();
            return "Title updated";
        }
        return "Task not found";
    }

    public static String changeTaskDesc(int taskId, String newDesc) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setDesc(newDesc);
            saveTasks();
            return "Description updated";
        }
        return "Task not found";
    }

    public static String changeTaskAssignedEmp(int taskId, Employee newAssignedEmp) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setAssignedEmp(newAssignedEmp);
            saveTasks();
            return "Assigned employee updated";
        }
        return "Task not found";
    }

    public static String changeTaskPhase(int taskId, TaskPhases newTaskPhase) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setTaskPhase(newTaskPhase);
            saveTasks();
            return "TaskPhase updated";
        }
        return "Task not found";
    }

    public static String changeTaskProject(int taskId, Projects newProject) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setProject(newProject);
            saveTasks();
            return "Project updated";
        }
        return "Task not found";
    }

    public static String changeTaskPriority(int taskId, int newPriority) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setPriority(newPriority);
            saveTasks();
            return "Priority updated";
        }
        return "Task not found";
    }

    public static String changeTaskCreatorName(int taskId, Employee newCreatorName) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setCreator(newCreatorName);
            saveTasks();
            return "CreatorName updated";
        }
        return "Task not found";
    }

    public static String changeTaskStartDate(int taskId, Date newStartDate) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setStartDate(newStartDate);
            saveTasks();
            return "Start date updated";
        }
        return "Task not found";
    }

    public static String changeTaskEndDate(int taskId, Date newEndDate) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setEndDate(newEndDate);
            saveTasks();
            return "End date updated";

        }
        return "Task not found";
    }

    public static String changeTaskEstimationHours(int taskId, double newEstimationHours) {
        loadTasksIfEmpty();
        TaskPage task = getTaskById(taskId);
        if (task != null) {
            task.setEstimationHours(newEstimationHours);
            saveTasks();
            return "Estimation hours updated";
        }
        return "Task not found";
    }

    public static void saveTaskLogs() {
        TaskLog.saveLogs(PATH + "task_logs.txt", ArrayLists.taskLogs);
    }

    public static void loadTaskLogs() {
        ArrayLists.taskLogs.clear();
        ArrayLists.taskLogs.addAll(TaskLog.loadLogs(PATH + "task_logs.txt"));
    }

    public static void saveRequests() {
        List<String> lines = new ArrayList<>();
        for (Request r : ArrayLists.requests) {
            lines.add(r.toFileString());
        }
        FileManager.writeFile(PATH + "requests.txt", lines);
    }

    public static void loadRequests() {
        ArrayLists.requests.clear();
        List<String> lines = FileManager.readFile(PATH + "requests.txt");
        int maxId = 0;
        for (String line : lines) {
            if (line.isEmpty())
                continue;
            try {
                Request r = null;
                if (line.startsWith("Vacation|"))
                    r = LeaveRequest.fromFileString(line);
                else if (line.startsWith("Mission|"))
                    r = MissionRequest.fromFileString(line);
                else if (line.startsWith("Permission|"))
                    r = PermissionRequest.fromFileString(line);
                else if (line.startsWith("Request|"))
                    r = Request.fromFileString(line);
                if (r != null) {
                    ArrayLists.requests.add(r);
                    if (r.getRequestId() > maxId)
                        maxId = r.getRequestId();
                }
            } catch (Exception ignored) {
            }
        }
        Request.updateNextId(maxId);
    }

    public static void getRequestsTypeVacation() {
        ArrayLists.requestsVacation.clear();
        List<String> lines = FileManager.readFile(PATH + "requests.txt");
        for (String line : lines) {
            if (line.isEmpty())
                continue;
            try {
                if (line.startsWith("Vacation|"))
                    ArrayLists.requestsVacation.add(LeaveRequest.fromFileString(line));
            } catch (Exception ignored) {
            }
        }
    }

    public static void getRequestsTypeMission() {
        ArrayLists.requestsMission.clear();
        List<String> lines = FileManager.readFile(PATH + "requests.txt");
        for (String line : lines) {
            if (line.isEmpty())
                continue;
            try {
                if (line.startsWith("Mission|"))
                    ArrayLists.requestsMission.add(MissionRequest.fromFileString(line));
            } catch (Exception ignored) {
            }
        }
    }

    public static void getRequestsTypePermission() {
        ArrayLists.requestsPermission.clear();
        List<String> lines = FileManager.readFile(PATH + "requests.txt");
        for (String line : lines) {
            if (line.isEmpty())
                continue;
            try {
                if (line.startsWith("Permission|"))
                    ArrayLists.requestsPermission.add(PermissionRequest.fromFileString(line));
            } catch (Exception ignored) {
            }
        }
    }

    public static void saveTimeCardsToFile() {
        List<String> lines = new ArrayList<>();
        for (TimeCard tc : timeCards) {
            lines.add(tc.toString());
        }
        FileManager.writeFile(PATH + "timecards.txt", lines);
    }

    public static void loadTimeCards() {
        timeCards.clear();
        List<String> lines = FileManager.readFile(PATH + "timecards.txt");
        for (String line : lines) {
            if (line.isEmpty())
                continue;
            try {
                String[] p = line.split("\\|");
                int id = Integer.parseInt(p[0]);
                String empId = p[1];
                LocalDate date = LocalDate.parse(p[2]);
                LocalDateTime checkIn = LocalDateTime.parse(p[3]);
                LocalDateTime checkOut = p[4].equals("null") ? null : LocalDateTime.parse(p[4]);
                timeCards.add(new TimeCard(id, empId, date, checkIn, checkOut));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addTimeCard(TimeCard tc) {
        timeCards.add(tc);
        saveTimeCardsToFile();
    }

    public static void updateTimeCard(TimeCard tc) {
        saveTimeCardsToFile();
    }

    public static TimeCard getTodayTimeCard(String empId) {
        LocalDate today = LocalDate.now();
        for (TimeCard tc : timeCards) {
            if (tc.getEmployeeId().equals(empId) &&
                    tc.getDate().equals(today)) {
                return tc;
            }
        }
        return null;
    }
}
