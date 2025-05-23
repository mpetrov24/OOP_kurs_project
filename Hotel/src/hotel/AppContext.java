package hotel;

import java.io.File;

public class AppContext {
    private final Hotel hotel;
    private File reservationFile;
    private File unavailableFile;
    /**
     * Конструктор на AppContext.
     *
     * @param hotel Обектът Hotel, който ще бъде част от контекста.
     */
    public AppContext(Hotel hotel) {
        this.hotel = hotel;
    }
    /**
     * Връща обекта Hotel от контекста.
     *
     * @return Обектът Hotel.
     */
    public Hotel getHotel() {
        return hotel;
    }

    public File getReservationFile() {
        return reservationFile;
    }

    public void setReservationFile(File file) {
        this.reservationFile = file;
    }

    public File getUnavailableFile() {
        return unavailableFile;
    }

    public void setUnavailableFile(File file) {
        this.unavailableFile = file;
    }
}
