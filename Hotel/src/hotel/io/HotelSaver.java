package hotel.io;

import hotel.Hotel;
import hotel.Reservation;
import hotel.Room;
import hotel.UnavailablePeriod;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Помощен клас, отговорен за запазването на хотелски данни (резервации и периоди на недостъпност)
 * във текстови файлове. Всеки метод записва конкретни типове данни в даден файл в предварително дефиниран формат.
 */
public class HotelSaver {
    /**
     * Запазва всички текущи резервации от обекта {@link Hotel} в указания файл.
     * Всяка резервация се записва на нов ред във формат:
     * {@code roomNumber,from_date,to_date,note,guests}
     *
     * @param filename Пътят до файла, където ще бъдат запазени данните за резервациите.
     * @param hotel    Обектът {@link Hotel}, съдържащ резервациите за запазване.
     * @throws IOException Ако възникне I/O грешка при запис във файла.
     */
    public static void saveReservations(String filename, Hotel hotel) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Room room : hotel.getRooms()) {
                for (Reservation r : room.getReservations()) {
                    writer.write(String.format("%d,%s,%s,%s,%d",
                            room.getNumber(),
                            r.from(),
                            r.to(),
                            r.note(),
                            r.guests()));
                    writer.newLine();
                }
            }
        }
    }

    /**
     * Запазва всички текущи периоди на недостъпност от обекта {@link Hotel} в указания файл.
     * Всеки период на недостъпност се записва на нов ред във формат:
     * {@code roomNumber,from_date,to_date,note}
     *
     * @param filename Пътят до файла, където ще бъдат запазени данните за периодите на недостъпност.
     * @param hotel    Обектът {@link Hotel}, съдържащ периодите на недостъпност за запазване.
     * @throws IOException Ако възникне I/O грешка при запис във файла.
     */
    public static void saveUnavailable(String filename, Hotel hotel) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Room room : hotel.getRooms()) {
                for (UnavailablePeriod u : room.getUnavailablePeriods()) {
                    writer.write(String.format("%d,%s,%s,%s",
                            room.getNumber(),
                            u.from(),
                            u.to(),
                            u.note()));
                    writer.newLine();
                }
            }
        }
    }
}
