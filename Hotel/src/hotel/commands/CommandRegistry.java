package hotel.commands;

import hotel.Hotel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry() {
        register("checkin", new CheckinCommand());
        register("availability", new AvailabilityCommand());
        register("checkout", new CheckoutCommand());
    }

    public void register(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }

    public void executeCommand(String line, Hotel hotel) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length == 0) return;

        String cmd = parts[0].toLowerCase();
        Command command = commands.get(cmd);
        if (command != null) {
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);
            try {
                command.execute(args, hotel);
            } catch (CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Unknown command: " + cmd);
        }
    }
}
