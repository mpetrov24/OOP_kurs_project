package hotel;

import java.io.File;

public class AppContext {
    private Hotel hotel;
    private File reservationFile;
    private File unavailableFile;

    public AppContext(Hotel hotel) {
        this.hotel = hotel;
    }

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
