package hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hotel {
    private static List<Room> rooms;

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
}
