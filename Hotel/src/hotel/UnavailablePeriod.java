package hotel;

import java.time.LocalDate;

public record UnavailablePeriod(LocalDate from, LocalDate to, String note) {

    public boolean isOverlapping(LocalDate checkFrom, LocalDate checkTo) {
        return !(checkTo.isBefore(from) || checkFrom.isAfter(to));
    }
}
