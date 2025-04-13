package hotel.io;

import hotel.Hotel;
import hotel.Reservation;
import hotel.Room;
import hotel.UnavailablePeriod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class HotelLoader {
    public static void loadRooms(String filename, Hotel hotel) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",", -1);
                int roomNumber = Integer.parseInt(tokens[0]);
                int beds = Integer.parseInt(tokens[1]);
                hotel.addRoom(new Room(roomNumber, beds));
            }
        }
    }

    public static void loadReservations(String filename, Hotel hotel) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",", -1);
                int roomNumber = Integer.parseInt(tokens[0]);
                LocalDate from = LocalDate.parse(tokens[1]);
                LocalDate to = LocalDate.parse(tokens[2]);
                String note = tokens[3];
                int guests = Integer.parseInt(tokens[4]);
                Room room = hotel.findRoom(roomNumber);
                if (room != null) {
                    room.addReservation(new Reservation(from, to, note, guests));
                }
            }
        }
    }

    public static void loadUnavailable(String filename, Hotel hotel) throws IOException {
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",", -1);
                int roomNumber = Integer.parseInt(tokens[0]);
                LocalDate from = LocalDate.parse(tokens[1]);
                LocalDate to = LocalDate.parse(tokens[2]);
                String note = tokens[3];
                Room room = hotel.findRoom(roomNumber);
                if (room != null) {
                    room.addUnavailablePeriod(new UnavailablePeriod(from, to, note));
                }
            }
        }
    }
}
