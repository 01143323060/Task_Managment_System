package TaskModule;

import AdminModule.*;
import DataBase.*;
import java.util.*;

public class TaskPage {
    private int taskId;
    private int code;
    private String title;
    private String desc;
    private Employee assignedEmp;
    private TaskPhases taskPhase;
    private Projects project;
    private int priority;
    private Employee creator;
    private Date startDate;
    private Date endDate;
    private double estimationHours;
    private String evaluation = "Not evaluated yet";
    private static int idCounter = 1;

    public TaskPage() {

    }

    public TaskPage(int code, String title, String desc, Employee assignedEmp,
            TaskPhases taskPhase, Projects project, int priority,
            Employee creator, Date start, Date end, double hours) {
        this.taskId = idCounter++;
        this.code = code;
        this.title = title;
        this.desc = desc;
        this.assignedEmp = assignedEmp;
        this.taskPhase = taskPhase;
        this.project = project;
        this.priority = priority;
        this.creator = creator;
        this.startDate = start;
        this.endDate = end;
        this.estimationHours = hours;
    }

    // Getters & Setters (أضفت الكل عشان يبقى كامل)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int id) {
        this.taskId = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Employee getAssignedEmp() {
        return assignedEmp;
    }

    public void setAssignedEmp(Employee assignedEmp) {
        this.assignedEmp = assignedEmp;
    }

    public TaskPhases getTaskPhase() {
        return taskPhase;
    }

    public void setTaskPhase(TaskPhases taskPhase) {
        this.taskPhase = taskPhase;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Employee getCreator() {
        return creator;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getEstimationHours() {
        return estimationHours;
    }

    public void setEstimationHours(double estimationHours) {
        this.estimationHours = estimationHours;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String toFileString() {
        return taskId + "|" + code + "|" + title + "|" + desc + "|" +
                (assignedEmp != null ? assignedEmp.getID() : "") + "|" +
                (taskPhase != null ? taskPhase.getid() : "") + "|" +
                (project != null ? project.getID() : "") + "|" +
                priority + "|" +
                (creator != null ? creator.getID() : "") + "|" +
                startDate.getTime() + "|" + endDate.getTime() + "|" +
                estimationHours + "|" + evaluation;
    }

    public static TaskPage fromFileString(String line) {
        String[] p = line.split("\\|", -1);
        TaskPage t = new TaskPage();
        t.taskId = Integer.parseInt(p[0]);
        t.code = Integer.parseInt(p[1]);
        t.title = p[2];
        t.desc = p[3];

        long empId = p[4].isEmpty() ? 0 : Long.parseLong(p[4]);
        t.assignedEmp = ArrayLists.employee.stream().filter(e -> e.getID() == empId).findFirst().orElse(null);

        long phaseId = p[5].isEmpty() ? 0 : Long.parseLong(p[5]);
        t.taskPhase = new TaskPhases(""); // placeholder
        t.taskPhase.setid(phaseId);

        long projId = p[6].isEmpty() ? 0 : Long.parseLong(p[6]);
        t.project = ArrayLists.projects.stream().filter(pr -> pr.getID() == projId).findFirst().orElse(null);

        t.priority = Integer.parseInt(p[7]);
        long creatorId = p[8].isEmpty() ? 0 : Long.parseLong(p[8]);
        t.creator = ArrayLists.employee.stream().filter(e -> e.getID() == creatorId).findFirst().orElse(null);

        t.startDate = new Date(Long.parseLong(p[9]));
        t.endDate = new Date(Long.parseLong(p[10]));
        t.estimationHours = Double.parseDouble(p[11]);
        t.evaluation = p[12];

        if (t.taskId >= idCounter)
            idCounter = t.taskId + 1;
        return t;
    }

    public static void saveTasks(String path, ArrayList<TaskPage> tasks) {
        List<String> lines = new ArrayList<>();
        for (TaskPage t : tasks)
            lines.add(t.toFileString());
        FileManager.writeFile(path, lines);
    }

    public static ArrayList<TaskPage> loadTasks(String path) {
        List<String> lines = FileManager.readFile(path);
        ArrayList<TaskPage> list = new ArrayList<>();
        for (String line : lines) {
            try {
                list.add(fromFileString(line));
            } catch (Exception ignored) {
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "Task #" + taskId + ": " + title + " → " + (assignedEmp != null ? assignedEmp.getName() : "Unassigned");
    }
}