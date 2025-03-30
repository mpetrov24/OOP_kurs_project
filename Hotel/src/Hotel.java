import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private static List<Room> rooms;

    Hotel() {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        for (Room r : rooms) {
            if (r.getNumber() == room.getNumber()) {
                System.out.println("Room with this number already exists");
                return;
            }
        }
        rooms.add(room);
    }

    public static Room findRoom(int roomNumber) {
        Room room = null;
        for (Room r: rooms) {
            if (r.getNumber() == roomNumber) {
                room = r;
                break;
            }
        }
        return room;
    }

    public void checkin(int roomNumber, LocalDate from, LocalDate to, String note, int guests) {
        Room room = Hotel.findRoom(roomNumber);
        if (room != null) {
            if (room.isAvailableOnDate(from, to)) {
                Reservation reservation = new Reservation(roomNumber, from, to, note, guests);
                room.addReservation(reservation);
                System.out.println("Reservation successful");
            } else {
                System.out.println("Room is not available.");
            }
        } else {
            System.out.println("There is no such room.");
        }
    }

    public void availability(LocalDate date) {
        boolean areAvailable = false;
        for (Room room : rooms) {
            if (room.isAvailableOnDate(date)) {
                areAvailable = true;
                System.out.printf("Room: %s, Beds: %d\n", room.getNumber(), room.getBeds());
            }
        }

        if (!areAvailable) {
            System.out.printf("There no available rooms on %s", date);
        }
    }
}
