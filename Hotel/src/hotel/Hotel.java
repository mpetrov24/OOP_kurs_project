package hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hotel {
    private final List<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return Collections.unmodifiableList(rooms);
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

    public Room findRoom(int roomNumber) {
        Room room = null;
        for (Room r: rooms) {
            if (r.getNumber() == roomNumber) {
                room = r;
                break;
            }
        }
        return room;
    }

    public void clearReservations() {
        for (Room r : rooms) {
            r.clearReservations();
        }
    }

    public void clearUnavailable() {
        for (Room r : rooms) {
            r.clearUnavailable();
        }
    }
}
