package hotel.commands;

import java.time.LocalDate;
import java.util.Arrays;

import hotel.AppContext;
import hotel.Hotel;
import hotel.Room;
import hotel.Reservation;

public class CheckinCommand extends Command{
    public CheckinCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        int guests = -1;
        int roomNumber;
        LocalDate from;
        LocalDate to;
        Hotel hotel = context.getHotel();

        if (args.length < 4) {
            throw new CommandException("Usage: checkin <room> <from> <to> <note> [guests]");
        }

        try {
            roomNumber = Integer.parseInt(args[0]);
            from = dateParser(args[1]);
            to = dateParser(args[2]);

            if (to.isBefore(from)) {
                throw new CommandException("Check-out date cannot be before check-in date.");
            }

            if (from.isBefore(LocalDate.now())) {
                throw new CommandException("Check-in date cannot be before current date");
            }

        } catch (NumberFormatException e) {
            throw new CommandException("Invalid room number");
        } catch (CommandException e) {
            throw new CommandException(e.getMessage());
        }

        try {
            guests = Integer.parseInt(args[args.length - 1]);
        } catch (NumberFormatException ignored) {

        }

        int noteEndIdx = guests == -1 ? args.length : args.length - 1;
        String note = String.join(" ", Arrays.copyOfRange(args, 3, noteEndIdx));

        Room room = hotel.findRoom(roomNumber);

        if (room != null) {
            if (guests == -1) {
                guests = room.getBeds();
            }

            if (room.isAvailableOnDate(from, to)) {
                Reservation reservation = new Reservation(from, to, note, guests);
                room.addReservation(reservation);
                System.out.println("Reservation successful");
            } else {
                System.out.println("Room is not available.");
            }
        } else {
            System.out.println("No room found.");
        }
    }
}
