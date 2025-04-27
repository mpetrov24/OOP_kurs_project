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

        System.out.println(this);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Available commands:\n");
        builder.append("  open <reservationsFile> <unavailableFile>    - Load reservation and unavailable data from files.\n");
        builder.append("  save                                         - Save the current reservation and unavailable data to the last used files.\n");
        builder.append("  saveas <reservationsFile> <unavailableFile>  - Save data to new files and update the current file references.\n");
        builder.append("  close                                        - Unload current hotel data from memory.\n");
        builder.append("  help                                         - Show this help message.\n");
        builder.append("  exit                                         - Exit the application.\n");
        builder.append("  checkin <room> <from> <to> <note> [guests]   - Check in a guest to a room.\n");
        builder.append("  availability [date]                          - List available rooms for a specific date or today.\n");
        builder.append("  checkout <room>                              - Check out guests from a room.\n");
        builder.append("  report <from> <to>                           - Show usage report of rooms for a date range.\n");
        builder.append("  find <beds> <from> <to>                      - Find a free room with at least the given number of beds.\n");
        builder.append("  find! <beds> <from> <to>                     - Emergency search for room (may suggest moving guests).\n");
        builder.append("  unavailable <room> <from> <to> <note>        - Mark a room as temporarily unavailable.\n");

        return builder.toString();
    }
}
