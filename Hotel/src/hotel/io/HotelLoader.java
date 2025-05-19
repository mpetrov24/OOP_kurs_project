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

/**
 * Помощен клас, отговорен за зареждането на хотелски данни (стаи, резервации и периоди на недостъпност)
 * от текстови файлове в обекта {@link Hotel}.
 * Всеки метод обработва парсването на специфични формати данни от даден файл.
 */
public class HotelLoader {
    /**
     * Зарежда данни за стаи от указания файл в обекта {@link Hotel}.
     * Всеки ред във файла се очаква да представлява стая във формат:
     * {@code roomNumber,beds}
     *
     * @param filename Пътят до файла, съдържащ данни за стаите.
     * @param hotel    Обектът {@link Hotel}, към който ще бъдат добавени заредените стаи.
     * @throws IOException           Ако възникне I/O грешка при четене на файла.
     * @throws NumberFormatException Ако номерът на стаята или броят легла не могат да бъдат парснати като цели числа.
     */
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

    /**
     * Зарежда данни за резервации от указания файл в обекта {@link Hotel}.
     * Всеки ред във файла се очаква да представлява резервация във формат:
     * {@code roomNumber,from_date,to_date,note,guests}
     * Ако файлът не съществува, методът ще се върне без да хвърля грешка,
     * приемайки, че няма резервации за зареждане.
     *
     * @param filename Пътят до файла, съдържащ данни за резервациите.
     * @param hotel    Обектът {@link Hotel}, към който ще бъдат добавени заредените резервации.
     * @throws IOException                         Ако възникне I/O грешка при четене на файла.
     * @throws NumberFormatException               Ако номерът на стаята или броят гости не могат да бъдат парснати като цели числа.
     * @throws java.time.format.DateTimeParseException Ако {@code from_date} или {@code to_date} не могат да бъдат парснати като LocalDate.
     */
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

    /**
     * Зарежда данни за периоди на недостъпност от указания файл в обекта {@link Hotel}.
     * Всеки ред във файла се очаква да представлява период на недостъпност във формат:
     * {@code roomNumber,from_date,to_date,note}
     * Ако файлът не съществува, методът ще се върне без да хвърля грешка,
     * приемайки, че няма периоди на недостъпност за зареждане.
     *
     * @param filename Пътят до файла, съдържащ данни за периодите на недостъпност.
     * @param hotel    Обектът {@link Hotel}, към който ще бъдат добавени заредените периоди на недостъпност.
     * @throws IOException                         Ако възникне I/O грешка при четене на файла.
     * @throws NumberFormatException               Ако номерът на стаята не може да бъде парснат като цяло число.
     * @throws java.time.format.DateTimeParseException Ако {@code from_date} или {@code to_date} не могат да бъдат парснати като LocalDate.
     */
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
