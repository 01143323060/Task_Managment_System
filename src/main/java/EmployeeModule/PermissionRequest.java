package EmployeeModule;

import java.time.*;

public class PermissionRequest extends Request {
    private LocalDate date;
    private LocalTime from, to;

    public PermissionRequest(String employeeId, String reason, LocalDate d, LocalTime f, LocalTime t) {
        super(employeeId, reason);
        this.date = d;
        this.from = f;
        this.to = t;
    }

    @Override
    public String toFileString() {
        return "Permission|" + requestId + "|" + employeeId + "|" + reason + "|" + status + "|" + createdAt + "|" + date + "|" + from + "|" + to;
    }

    public static PermissionRequest fromFileString(String line) {
        String[] p = line.split("\\|");
        PermissionRequest r = new PermissionRequest(p[2], p[3], LocalDate.parse(p[6]), LocalTime.parse(p[7]), LocalTime.parse(p[8]));
        r.requestId = Integer.parseInt(p[1]);
        r.status = p[4];
        r.createdAt = LocalDateTime.parse(p[5]);
        return r;
    }
}