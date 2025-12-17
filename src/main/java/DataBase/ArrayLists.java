package DataBase;

import AdminModule.*;
import TaskModule.*;
import EmployeeModule.*;
import java.util.ArrayList;

public class ArrayLists {
    public static ArrayList<Employee> employee = new ArrayList<>();
    public static ArrayList<Projects> projects = new ArrayList<>();
    public static ArrayList<TaskPage> tasks = new ArrayList<>();           
    public static ArrayList<TaskLog> taskLogs = new ArrayList<>();         
    public static ArrayList<Request> requests = new ArrayList<>();
    public static ArrayList<Request> requestsVacation = new ArrayList<>();
    public static ArrayList<Request> requestsMission = new ArrayList<>();
    public static ArrayList<Request> requestsPermission = new ArrayList<>();
    public static ArrayList<Users> users = new ArrayList<>();
    public static ArrayList<TimeCard> timeCards = new ArrayList<>();
    public static ArrayList<TaskPhases> taskPhases = new ArrayList<>();
    public static TaskPage getTaskById(int taskId) {
        for (TaskPage task : tasks) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }
}