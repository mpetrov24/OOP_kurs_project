package hotel.commands;

import hotel.AppContext;

public class CloseCommand extends Command{
    public CloseCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        if (args.length != 0) {
            throw new CommandException("Usage: close");
        }

        if (context.getReservationFile() == null && context.getUnavailableFile() == null) {
            throw new CommandException("There are no open files");
        }

        context.setReservationFile(null);
        context.setUnavailableFile(null);

        context.getHotel().clearReservations();
        context.getHotel().clearUnavailable();

        System.out.println("Files closed successfully");
    }
}
