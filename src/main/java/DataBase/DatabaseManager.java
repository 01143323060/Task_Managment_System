package DataBase;

import AdminModule.*;
import TaskModule.*;
import EmployeeModule.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DatabaseManager {

    private static final String PATH = "Resources" + java.io.File.separator;

    // --------------------------
    // TIME CARDS LIST
    // --------------------------
    public static ArrayList<TimeCard> timeCards = new ArrayList<>();

    // --------------------------
    // INIT
    // --------------------------
    public static void init() {
        loadEmployees();
        loadProjects();
        loadTasks();
        loadTaskLogs();
        loadRequests();
        loadTimeCards();   // ← مهم جداً
    }

    public static void saveAll() {
        saveEmployees();
        saveProjects();
        saveTasks();
        saveTaskLogs();
        saveRequests();
        saveTimeCardsToFile();
    }

    // -------------------------------------------------------
    // EMPLOYEES
    // -------------------------------------------------------
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
            if (p.length < 6) continue;
            Employee e = new Employee(p[1], p[2], p[3], p[4], Long.parseLong(p[5]));
            e.setID(Long.parseLong(p[0]));
            ArrayLists.employee.add(e);
            if (e.getID() > maxId) maxId = e.getID();
        }
        Employee.setEmpCounter(maxId + 1);
    }

    // -------------------------------------------------------
    // PROJECTS
    // -------------------------------------------------------
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
            if (p.length < 3) continue;
            Projects pr = new Projects(p[1], Long.parseLong(p[2]));
            pr.setID(Long.parseLong(p[0]));
            ArrayLists.projects.add(pr);
            if (pr.getID() > maxId) maxId = pr.getID();
        }
        Projects.setCounter(maxId + 1);
    }

    // -------------------------------------------------------
    // TASKS
    // -------------------------------------------------------
    public static void saveTasks() {
        TaskPage.saveTasks(PATH + "tasks.txt", ArrayLists.tasks);
    }

    public static void loadTasks() {
        ArrayLists.tasks.clear(); 
        ArrayLists.tasks.addAll(TaskPage.loadTasks(PATH + "tasks.txt")); 
    }

    // -------------------------------------------------------
    // TASK LOGS
    // -------------------------------------------------------
    public static void saveTaskLogs() {
        TaskLog.saveLogs(PATH + "task_logs.txt", ArrayLists.taskLogs);
    }

    public static void loadTaskLogs() {
        ArrayLists.taskLogs.clear();
        ArrayLists.taskLogs.addAll(TaskLog.loadLogs(PATH + "task_logs.txt"));
    }

    // -------------------------------------------------------
    // REQUESTS
    // -------------------------------------------------------
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
        for (String line : lines) {
            if (line.isEmpty()) continue;
            try {
                if (line.startsWith("Vacation|")) ArrayLists.requests.add(LeaveRequest.fromFileString(line));
                else if (line.startsWith("Mission|")) ArrayLists.requests.add(MissionRequest.fromFileString(line));
                else if (line.startsWith("Permission|")) ArrayLists.requests.add(PermissionRequest.fromFileString(line));
                else if (line.startsWith("Request|")) ArrayLists.requests.add(Request.fromFileString(line));
            } catch (Exception ignored) {}
        }
    }

    // -------------------------------------------------------
    // TIME CARDS — SAVE
    // -------------------------------------------------------
    public static void saveTimeCardsToFile() {
        List<String> lines = new ArrayList<>();

        for (TimeCard tc : timeCards) {
            lines.add(tc.toString()); 
        }

        FileManager.writeFile(PATH + "timecards.txt", lines);
    }

    // -------------------------------------------------------
    // TIME CARDS — LOAD
    // -------------------------------------------------------
    public static void loadTimeCards() {
        timeCards.clear();

        List<String> lines = FileManager.readFile(PATH + "timecards.txt");

        for (String line : lines) {
            if (line.isEmpty()) continue;

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

    // -------------------------------------------------------
    // ADD NEW TIME CARD (Check-in)
    // -------------------------------------------------------
    public static void addTimeCard(TimeCard tc) {
        timeCards.add(tc);
        saveTimeCardsToFile();
    }

    // -------------------------------------------------------
    // UPDATE (Check-out)
    // -------------------------------------------------------
    public static void updateTimeCard(TimeCard tc) {
        saveTimeCardsToFile();
    }

    // -------------------------------------------------------
    // GET TODAY CARD FOR EMPLOYEE
    // -------------------------------------------------------
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
