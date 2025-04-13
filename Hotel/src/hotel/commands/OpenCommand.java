package hotel.commands;

import hotel.AppContext;
import hotel.io.HotelLoader;

import java.io.File;
import java.io.IOException;

public class OpenCommand extends Command{
    public OpenCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        if (args.length != 2) {
            throw new CommandException("Usage: open <reservationsFile> <unavailableFile>");
        }

        File reservations = new File(args[0]);
        File unavailable = new File(args[1]);

        if (!reservations.exists() || !unavailable.exists()) {
            throw new CommandException("One or both files do not exist.");
        }

        context.getHotel().clearReservations();
        context.getHotel().clearUnavailable();

        try {
            HotelLoader.loadReservations(reservations.getAbsolutePath(), context.getHotel());
            HotelLoader.loadUnavailable(unavailable.getAbsolutePath(), context.getHotel());
        } catch (IOException e) {
            throw new CommandException("Failed to load hotel data: " + e.getMessage());
        }

        context.setReservationFile(reservations);
        context.setUnavailableFile(unavailable);

        System.out.println("Files opened successfully.");
    }
}
