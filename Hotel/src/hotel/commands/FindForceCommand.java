package hotel.commands;

import hotel.AppContext;
import hotel.Reservation;
import hotel.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindForceCommand extends Command{
    public FindForceCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        if (args.length != 3) {
            throw new CommandException("Usage find! <beds> <from> <to>");
        }

        try {
            int beds = Integer.parseInt(args[0]);
            LocalDate from = dateParser(args[1]);
            LocalDate to = dateParser(args[2]);

            List<Room> suitable = new ArrayList<>();
            List<Room> rooms = context.getHotel().getRooms();

            for (Room room : rooms) {
                if (room.isAvailableOnDate(from, to) && room.getBeds() >= beds) {
                    suitable.add(room);
                }
            }
            if (!suitable.isEmpty()) {
                suitable.sort(Comparator.comparing(Room::getBeds));
                suitable.forEach(r -> System.out.printf("%s - %d\n", r.getNumber(), r.getBeds()));
            }

            for (Room room : rooms) {
                if (room.getBeds() < beds) {
                    continue;
                }

                List<Reservation> conflicts = room.getReservations()
                        .stream()
                        .filter(r -> overlaps(from,to,r.from(),r.to()))
                        .toList();

                if (!conflicts.isEmpty() && conflicts.size() <= 2) {
                    System.out.printf("No free room found, but you can relocate guests to free up room %d:%n", room.getNumber());

                    for (Reservation r : conflicts) {
                        System.out.printf(" - Reservation (%s to %s): %s%n\n", r.from(), r.to(), r.note());
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new CommandException("Beds should be a valid number");
        }
    }
    private boolean overlaps(LocalDate from1, LocalDate to1, LocalDate from2, LocalDate to2) {
        return !(to1.isBefore(from2) || from1.isAfter(to2));
    }
}
