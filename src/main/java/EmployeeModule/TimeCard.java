package EmployeeModule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeCard {

    private static int next = 1;

    private int cardId;
    private String employeeId;
    private LocalDate date;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public TimeCard(String employeeId) {
        this.cardId = next++;
        this.employeeId = employeeId;
        this.date = LocalDate.now();
        this.checkIn = LocalDateTime.now();
        this.checkOut = null;
    }

    public TimeCard(int cardId, String employeeId, LocalDate date,
                    LocalDateTime checkIn, LocalDateTime checkOut) {
        this.cardId = cardId;
        this.employeeId = employeeId;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

        if (cardId >= next) {
            next = cardId + 1;
        }
    }

    // Getters / Setters
    public int getCardId() { return cardId; }
    public String getEmployeeId() { return employeeId; }
    public LocalDate getDate() { return date; }

    public LocalDateTime getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDateTime checkIn) { this.checkIn = checkIn; }

    public LocalDateTime getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDateTime checkOut) { this.checkOut = checkOut; }

    @Override
    public String toString() {
        return cardId + "|" + employeeId + "|" + date + "|" +
               checkIn + "|" + (checkOut != null ? checkOut : "null");
    }

    public String getUserId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
