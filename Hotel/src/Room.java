import java.time.LocalDate;
import java.util.ArrayList;

public class Room {
    private int number;
    private int beds;
    private boolean isAvailable;
    private ArrayList<Reservation> reservations;

    Room(int number, int beds) throws Exception {
        setNumber(number);
        setBeds(beds);
        this.isAvailable = true;
        this.reservations = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws Exception {
        if (number > 0) {
            this.number = number;
            return;
        }
        throw new Exception("Invalid room number");
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) throws Exception {
        if (beds > 0) {
            this.beds = beds;
            return;
        }
        throw new Exception("Invalid number of beds");
    }

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
