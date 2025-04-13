package hotel.commands;

import hotel.AppContext;
import hotel.io.HotelSaver;

import java.io.File;
import java.io.IOException;

public class SaveCommand extends Command{
    public SaveCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        if (context.getReservationFile() == null || context.getUnavailableFile() == null) {
           throw new CommandException("No open files. Use save as or open first");
        }

        if (args.length != 0) {
            throw new CommandException("Usage: save");
        }

        File reservations = context.getReservationFile();
        File unavailable = context.getUnavailableFile();

        try {
            HotelSaver.saveReservations(reservations.getAbsolutePath(), context.getHotel());
            HotelSaver.saveUnavailable(unavailable.getAbsolutePath(), context.getHotel());
            System.out.println("Date saved successfully");
        } catch (IOException e) {
            throw new CommandException("Failed to save hotel data " + e.getMessage());
        }

    }
}
