package hotel;

import java.time.LocalDate;

public class UnavailablePeriod {
    private LocalDate from;
    private LocalDate to;
    private String note;

    public UnavailablePeriod(LocalDate from, LocalDate to, String note) {
        this.from = from;
        this.to = to;
        this.note = note;
    }

    public LocalDate getFrom() {
        return from;
    }

    public boolean isOverlapping(LocalDate checkFrom, LocalDate checkTo) {
        return !(checkTo.isBefore(from) || checkFrom.isAfter(to));
    }
}
