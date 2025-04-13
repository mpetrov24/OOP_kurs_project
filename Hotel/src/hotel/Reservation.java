package hotel;

import java.time.LocalDate;

public record Reservation(LocalDate from, LocalDate to, String note, int guests) {

    public boolean isOverlapping(LocalDate checkFrom, LocalDate checkTo) {
        return !(checkTo.isBefore(from) || checkFrom.isAfter(to));
    }
}
