package hotel.commands;

import hotel.AppContext;
import hotel.Hotel;
import hotel.Room;
import hotel.UnavailablePeriod;

import java.time.LocalDate;
import java.util.Arrays;

public class UnavailableCommand extends Command{
    public UnavailableCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        Hotel hotel = context.getHotel();
        if (args.length < 4) {
            throw new CommandException("Usage: unavailable <room> <from> <to> <note>");
        }

        try {
            int roomNumber = Integer.parseInt(args[0]);
            Room room = hotel.findRoom(roomNumber);
            if (room != null) {
                LocalDate from = dateParser(args[1]);
                LocalDate to = dateParser(args[2]);
                String note = String.join(" ", Arrays.copyOfRange(args, 3, args.length));

                if (to.isBefore(from)) {
                    throw new CommandException("From date cannot be before check-in date.");
                }

                if (from.isBefore(LocalDate.now())) {
                    throw new CommandException("To date cannot be before current date");
                }

                if (room.isAvailableOnDate(from,to)) {
                    room.addUnavailablePeriod(new UnavailablePeriod(from, to, note));
                    System.out.printf("Room %d is marked as unavailable from %s to %s\n", roomNumber, from, to);
                } else {
                    throw new CommandException("Room is already unavailable in this period");
                }
            } else {
                throw new CommandException("No room found");
            }
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid room number");
        } catch (CommandException e) {
            throw new CommandException(e.getMessage());
        }

    }
}
