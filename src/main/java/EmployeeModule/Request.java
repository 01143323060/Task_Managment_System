package EmployeeModule;

import java.time.LocalDateTime;
import DataBase.DatabaseManager;

public class Request {
    protected int requestId;
    protected String employeeId;
    protected LocalDateTime createdAt;
    protected String reason;
    protected String status = "Pending";

    private static int nextId = 1;

    public Request(String employeeId, String reason) {
        this.requestId = nextId++;
        this.employeeId = employeeId;
        this.reason = reason;
        this.createdAt = LocalDateTime.now();
    }

    public String toFileString() {
        return "Request|" + requestId + "|" + employeeId + "|" + reason + "|" + status + "|" + createdAt;
    }

    public static Request fromFileString(String line) {
        String[] p = line.split("\\|");
        if (p.length < 6 || !p[0].equals("Request"))
            return null;

        Request r = new Request(p[2], p[3]);
        r.requestId = Integer.parseInt(p[1]);
        r.status = p[4];
        r.createdAt = LocalDateTime.parse(p[5]);
        return r;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void approve() {
        this.status = "Approved";
        DatabaseManager.saveRequests();
    }

    public void reject() {
        this.status = "Rejected";
        DatabaseManager.saveRequests();
    }

    public static void updateNextId(int maxId) {
        nextId = maxId + 1;
    }
}