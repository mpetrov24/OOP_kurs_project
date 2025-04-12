package hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Room {
    private int number;
    private int beds;
    private List<Reservation> reservations;
    private List<UnavailablePeriod> unavailablePeriods;

    public Room(int number, int beds) throws Exception {
        setNumber(number);
        setBeds(beds);
        this.reservations = new ArrayList<>();
        this.unavailablePeriods = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public int getBeds() {
        return beds;
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public List<UnavailablePeriod> getUnavailablePeriods() {
        return Collections.unmodifiableList(unavailablePeriods);
    }

    public void setNumber(int number) throws Exception {
        if (number > 0) {
            this.number = number;
            return;
        }
        throw new Exception("Invalid room number");
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

        for (UnavailablePeriod unavailablePeriod : unavailablePeriods) {
            if (unavailablePeriod.isOverlapping(date, date)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAvailableOnDate(LocalDate dateFrom, LocalDate dateTo) {
        for (Reservation res : reservations) {
            if (res.isOverlapping(dateFrom, dateTo)) {
                return false;
            }
        }

        for (UnavailablePeriod unavailablePeriod : unavailablePeriods) {
            if (unavailablePeriod.isOverlapping(dateFrom, dateTo)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkout() {
        LocalDate today = LocalDate.now();

        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (!today.isBefore(reservation.getFrom()) && !today.isAfter(reservation.getTo())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public void addReservation(Reservation res) {
        reservations.add(res);
    }

    public void addUnavailablePeriod(UnavailablePeriod period) {
        unavailablePeriods.add(period);
    }

}
