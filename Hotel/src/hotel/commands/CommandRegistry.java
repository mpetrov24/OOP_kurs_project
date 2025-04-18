package hotel.commands;

import hotel.AppContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry(AppContext context) {
        register("checkin", new CheckinCommand(context));
        register("availability", new AvailabilityCommand(context));
        register("checkout", new CheckoutCommand(context));
        register("unavailable", new UnavailableCommand(context));
        register("open", new OpenCommand(context));
        register("save", new SaveCommand(context));
    }

    public void register(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }

    public void executeCommand(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length == 0) return;

        String cmd = parts[0].toLowerCase();
        Command command = commands.get(cmd);
        if (command != null) {
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);
            try {
                command.execute(args);
            } catch (CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Unknown command: " + cmd);
        }
    }
}
