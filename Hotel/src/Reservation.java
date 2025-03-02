import java.time.LocalDate;

public class Reservation {
    private int roomNumber;
    private LocalDate from;
    private LocalDate to;
    private String note;
    private int guests;

    public boolean isOverlapping(LocalDate checkFrom, LocalDate checkTo) {
        return !(checkTo.isBefore(from) || checkFrom.isAfter(to));
    }
}
