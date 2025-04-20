package hotel.commands;

import hotel.AppContext;
import hotel.io.HotelSaver;

import java.io.File;
import java.io.IOException;

public class SaveAsCommand extends Command{
    public SaveAsCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        if (args.length != 2) {
            throw new CommandException("Usage: saveas <reservationsFile> <unavailableFile>");
        }

        File reservations = new File(args[0]);
        File unavailable = new File(args[1]);

        try {
            HotelSaver.saveReservations(reservations.getAbsolutePath(), context.getHotel());
            HotelSaver.saveUnavailable(unavailable.getAbsolutePath(), context.getHotel());
            System.out.println("Date saved successfully");
        } catch (IOException e) {
            throw new CommandException("Failed to save hotel data " + e.getMessage());
        }
    }
}
