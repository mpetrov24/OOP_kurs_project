package hotel.commands;

import hotel.AppContext;
import hotel.Hotel;
import hotel.Reservation;
import hotel.Room;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReportCommand extends Command{
    public ReportCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        if (args.length != 2) {
            throw new CommandException("Usage: report <from> <to>");
        }

        Hotel hotel = context.getHotel();
        LocalDate from = dateParser(args[0]);
        LocalDate to = dateParser(args[1]);

        if (from.isAfter(to)) {
            throw new CommandException("From date cannot be after to date");
        }

        System.out.printf("Usage report from %s to %s: \n", from, to);
        boolean usedFound = false;
        for (Room room : hotel.getRooms()) {
            for (Reservation res : room.getReservations()) {
                if (isWithinRange(from, to, res.from(), res.to())) {
                    usedFound = true;
                    System.out.printf("Room %s used for %d days\n", room.getNumber(), getUsageDays(res.from(), res.to()));
                }
            }
        }

        if (!usedFound) {
            System.out.print("No rooms used in this period");
        }

    }

    private boolean isWithinRange(LocalDate from, LocalDate to, LocalDate checkFrom, LocalDate checkTo) {
        boolean checkFirst = !checkFrom.isBefore(from) && !checkFrom.isAfter(to);
        boolean checkSecond = !checkTo.isBefore(from) && !checkTo.isAfter(to);

        return checkFirst && checkSecond;
    }

    private long getUsageDays(LocalDate from, LocalDate to) {
       return from.until(to, ChronoUnit.DAYS) + 1;
    }

}
