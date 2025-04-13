package hotel.commands;

import hotel.AppContext;
import hotel.Hotel;
import hotel.Room;

public class CheckoutCommand extends Command{
    public CheckoutCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        Hotel hotel = context.getHotel();
        if (args.length != 1) {
            throw new CommandException("Usage: checkout <room>");
        }
        try {
            int roomNumber = Integer.parseInt(args[0]);
            Room room = hotel.findRoom(roomNumber);
            if (room != null) {
                boolean success = room.checkout();
                if (success) {
                    System.out.printf("Room %d is now available\n",roomNumber);
                } else {
                    System.out.println("No reservations found");
                }
            } else {
                throw new CommandException("No room found");
            }
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid room number");
        }

    }
}
