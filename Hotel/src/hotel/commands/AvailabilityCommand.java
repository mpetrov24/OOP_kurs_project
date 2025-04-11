package hotel.commands;

import hotel.Room;
import hotel.Hotel;

import java.time.LocalDate;

public class AvailabilityCommand extends Command{
    public void execute(String[] args, Hotel hotel) throws CommandException {
        LocalDate date;
        if (args.length == 0) {
            date = LocalDate.now();
        } else if (args.length == 1) {
            date = dateParser(args[0]);
        } else {
            throw new CommandException("Usage: availability [<date>]");
        }

        boolean areAvailable = false;
        for (Room room : hotel.getRooms()) {
            if (room.isAvailableOnDate(date)) {
                areAvailable = true;
                System.out.printf("hotel.Room: %d, Beds: %d\n", room.getNumber(), room.getBeds());
            }
        }

        if (!areAvailable) {
            throw new CommandException("There no available rooms on " + date);
        }
    }
}
