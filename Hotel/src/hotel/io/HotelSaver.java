package hotel.io;

import hotel.Hotel;
import hotel.Reservation;
import hotel.Room;
import hotel.UnavailablePeriod;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HotelSaver {
    public static void saveReservations(String filename, Hotel hotel) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Room room : hotel.getRooms()) {
                for (Reservation r : room.getReservations()) {
                    writer.write(String.format("%d,%s,%s,%s,%d",
                            room.getNumber(),
                            r.getFrom(),
                            r.getTo(),
                            r.getNote(),
                            r.getGuests()));
                    writer.newLine();
                }
            }
        }
    }

    public static void saveUnavailable(String filename, Hotel hotel) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Room room : hotel.getRooms()) {
                for (UnavailablePeriod u : room.getUnavailablePeriods()) {
                    writer.write(String.format("%d,%s,%s,%s",
                            room.getNumber(),
                            u.getFrom(),
                            u.getTo(),
                            u.getNote()));
                    writer.newLine();
                }
            }
        }
    }
}
