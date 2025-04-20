package hotel.commands;

import hotel.AppContext;

public class HelpCommand extends Command{
    public HelpCommand(AppContext context) {
        super(context);
    }

    public void execute(String[] args) throws CommandException {
        if (args.length != 0) {
            throw new CommandException("Usage: help");
        }

        System.out.println("Available commands:");
        System.out.println("  open <reservationsFile> <unavailableFile>    - Load reservation and unavailable data from files.");
        System.out.println("  save                                         - Save the current reservation and unavailable data to the last used files.");
        System.out.println("  saveas <reservationsFile> <unavailableFile>  - Save data to new files and update the current file references.");
        System.out.println("  close                                        - Unload current hotel data from memory.");
        System.out.println("  help                                         - Show this help message.");
        System.out.println("  exit                                         - Exit the application.");
        System.out.println("  checkin <room> <from> <to> <note> [guests]   - Check in a guest to a room.");
        System.out.println("  availability [date]                          - List available rooms for a specific date or today.");
        System.out.println("  checkout <room>                              - Check out guests from a room.");
        System.out.println("  report <from> <to>                           - Show usage report of rooms for a date range.");
        System.out.println("  find <beds> <from> <to>                      - Find a free room with at least the given number of beds.");
        System.out.println("  find! <beds> <from> <to>                     - Emergency search for room (may suggest moving guests).");
        System.out.println("  unavailable <room> <from> <to> <note>        - Mark a room as temporarily unavailable.");
    }
}
