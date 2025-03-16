import java.time.LocalDate;

public class Reservation {
    private int roomNumber;
    private LocalDate from;
    private LocalDate to;
    private String note;
    private int guests;

    public Reservation(int roomNumber, LocalDate from, LocalDate to, String note) {
        this.roomNumber = roomNumber;
        this.from = from;
        this.to = to;
        this.note = note;
    }

    public Reservation(int roomNumber, LocalDate from, LocalDate to, String note, int guests) {
        this.roomNumber = roomNumber;
        this.from = from;
        this.to = to;
        this.note = note;
        this.guests = guests;
    }

    public boolean isOverlapping(LocalDate checkFrom, LocalDate checkTo) {
        return !(checkTo.isBefore(from) || checkFrom.isAfter(to));
    }
}
