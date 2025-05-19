package hotel;

import java.time.LocalDate;

public record UnavailablePeriod(LocalDate from, LocalDate to, String note) {
    /**
     * Проверява дали периодът на тази период на недостъпност
     * се застъпва с подадения като аргумент период.
     *
     * @param checkFrom Начална дата на периода за проверка (включително).
     * @param checkTo Крайна дата на периода за проверка (включително).
     * @return true, ако има застъпване, false в противен случай.
     */
    public boolean isOverlapping(LocalDate checkFrom, LocalDate checkTo) {
        return !(checkTo.isBefore(from) || checkFrom.isAfter(to));
    }
}
