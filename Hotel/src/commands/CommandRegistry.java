package commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry() {
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
