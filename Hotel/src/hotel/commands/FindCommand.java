package hotel.commands;

import hotel.AppContext;
import hotel.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindCommand extends Command{
    public FindCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        if (args.length != 3) {
            throw new CommandException("Usage find <beds> <from> <to>");
        }

        try {
            int beds = Integer.parseInt(args[0]);
            LocalDate from = dateParser(args[1]);
            LocalDate to = dateParser(args[2]);

            List<Room> suitable = new ArrayList<>();

            for (Room room : context.getHotel().getRooms()) {
                if (room.isAvailableOnDate(from, to) && room.getBeds() >= beds) {
                    suitable.add(room);
                }
            }
            if (suitable.isEmpty()) {
                throw new CommandException("No suitable rooms found");
            }
            suitable.sort(Comparator.comparing(Room::getBeds));
            suitable.forEach(r -> System.out.printf("%s - %d\n", r.getNumber(), r.getBeds()));
        } catch (NumberFormatException e) {
            throw new CommandException("Beds should be a valid number");
        }

    }
}
