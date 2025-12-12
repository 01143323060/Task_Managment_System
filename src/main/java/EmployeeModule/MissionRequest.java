package EmployeeModule;

import java.time.*;

public class MissionRequest extends Request {
    private LocalDate missionDate;
    private String description;

    public MissionRequest(String employeeId, String reason, LocalDate date, String desc) {
        super(employeeId, reason);
        this.missionDate = date;
        this.description = desc;
    }

    @Override
    public String toFileString() {
        return "Mission|" + requestId + "|" + employeeId + "|" + reason + "|" + status + "|" + createdAt + "|" + missionDate + "|" + description;
    }

    public static MissionRequest fromFileString(String line) {
        String[] p = line.split("\\|");
        MissionRequest r = new MissionRequest(p[2], p[3], LocalDate.parse(p[6]), p[7]);
        r.requestId = Integer.parseInt(p[1]);
        r.status = p[4];
        r.createdAt = LocalDateTime.parse(p[5]);
        return r;
    }
}