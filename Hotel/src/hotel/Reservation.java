package hotel;

import java.time.LocalDate;

public record Reservation(LocalDate from, LocalDate to, String note, int guests) {
    /**
     * Проверява дали периодът на тази резервация
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
