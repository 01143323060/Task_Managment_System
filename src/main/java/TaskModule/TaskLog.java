package TaskModule;

import AdminModule.Employee;
import DataBase.*;
import java.util.*;

public class TaskLog {
    private Date fromTime;
    private Date toTime;
    private Employee employee;
    private TaskPage task;
    private int logId;
    private static int nextId = 1;

    public TaskLog(Date from, Date to, Employee emp, TaskPage task) {
        this.fromTime = from;
        this.toTime = to;
        this.employee = emp;
        this.task = task;
        this.logId = nextId++;
    }

    private TaskLog() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String toFileString() {
        return fromTime.getTime() + "|" + toTime.getTime() + "|" +
               employee.getID() + "|" + task.getTaskId() + "|" + logId;
    }

    public static TaskLog fromFileString(String line) {
        String[] p = line.split("\\|");
        if (p.length < 5) return null;

        TaskLog log = new TaskLog();
        log.fromTime = new Date(Long.parseLong(p[0]));
        log.toTime = new Date(Long.parseLong(p[1]));

        long empId = Long.parseLong(p[2]);
        log.employee = ArrayLists.employee.stream()
                .filter(e -> e.getID() == empId).findFirst().orElse(null);

        int taskId = Integer.parseInt(p[3]);
        log.task = ArrayLists.tasks.stream()
                .filter(t -> t.getTaskId() == taskId).findFirst().orElse(null);

        log.logId = Integer.parseInt(p[4]);
        if (log.logId >= nextId) nextId = log.logId + 1;

        return log;
    }

    public static void saveLogs(String path, List<TaskLog> logs) {
        List<String> lines = new ArrayList<>();
        for (TaskLog l : logs) {
            if (l != null) lines.add(l.toFileString());
        }
        FileManager.writeFile(path, lines);
    }

    public static ArrayList<TaskLog> loadLogs(String path) {
        List<String> lines = FileManager.readFile(path);
        ArrayList<TaskLog> list = new ArrayList<>();
        for (String line : lines) {
            TaskLog log = fromFileString(line);
            if (log != null) list.add(log);
        }
        return list;
    }
}