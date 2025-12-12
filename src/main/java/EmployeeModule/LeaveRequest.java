package EmployeeModule;

import java.time.LocalDateTime;

public class LeaveRequest extends Request {
    private String leaveType;

    public LeaveRequest(String employeeId, String reason, String leaveType) {
        super(employeeId, reason);
        this.leaveType = leaveType;
    }

    @Override
    public String toFileString() {
        return "Vacation|" + requestId + "|" + employeeId + "|" + reason + "|" + status + "|" + createdAt + "|" + leaveType;
    }

    public static LeaveRequest fromFileString(String line) {
        String[] p = line.split("\\|");
        LeaveRequest r = new LeaveRequest(p[2], p[3], p[6]);
        r.requestId = Integer.parseInt(p[1]);
        r.status = p[4];
        r.createdAt = LocalDateTime.parse(p[5]);
        return r;
    }
}