package EmployeeModule;

import java.time.LocalDateTime;

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
        Request r = new Request(p[2], p[3]);
        r.requestId = Integer.parseInt(p[1]);
        r.status = p[4];
        r.createdAt = LocalDateTime.parse(p[5]);
        return r;
    }

    public int getRequestId() { return requestId; }
    public String getStatus() { return status; }
    public void approve() { status = "Approved"; }
    public void reject() { status = "Rejected"; }
}