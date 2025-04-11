package hotel;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hotel {
    private static List<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms);
    }

    public void addRoom(Room room) {
        for (Room r : rooms) {
            if (r.getNumber() == room.getNumber()) {
                System.out.println("hotel.Room with this number already exists");
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

    public void checkout(String[] arguments) {
        if (arguments.length > 2) {
            System.out.println("Too many arguments!");
            return;
        }
        try {
            int roomNumber = Integer.parseInt(arguments[1]);
            Room room = Hotel.findRoom(roomNumber);
            if (room != null) {
                boolean success = room.checkout();
                if (success) {
                    System.out.printf("hotel.Room %d is now available\n",roomNumber);
                } else {
                    System.out.println("No reservations found");
                }
            } else {
                System.out.println("No room found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid room number");
        }
    }


    public LocalDate dateValidator(String dateStr) {
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeException e) {
            System.out.println("Invalid date");
            return null;
        }
        return date;
    }
}
