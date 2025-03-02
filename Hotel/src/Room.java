import java.time.LocalDate;
import java.util.ArrayList;

public class Room {
    private int number;
    private int beds;
    private boolean isAvailable;
    private ArrayList<Reservation> reservations;

    public boolean isAvailableOnDate(LocalDate date) {
        for (Reservation res : reservations) {
            if (res.isOverlapping(date, date)) {
                return false;
            }
        }
        return true;
    }

    public void addReservation(Reservation res) {
        reservations.add(res);
        this.isAvailable = false;
    }

    public void removeReservation(Reservation res) {
        reservations.remove(res);
        this.isAvailable = true;
    }
}
