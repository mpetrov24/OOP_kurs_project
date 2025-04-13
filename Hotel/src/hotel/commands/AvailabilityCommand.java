package hotel.commands;

import hotel.AppContext;
import hotel.Room;
import hotel.Hotel;

import java.time.LocalDate;

public class AvailabilityCommand extends Command{
    public AvailabilityCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        LocalDate date;
        Hotel hotel = context.getHotel();
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
                System.out.printf("Room: %d, Beds: %d\n", room.getNumber(), room.getBeds());
            }
        }

        if (!areAvailable) {
            throw new CommandException("There no available rooms on " + date);
        }
    }
}
