package hotel.commands;

import hotel.AppContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Регистър за командите, поддържани от приложението.
 * Отговаря за парсването на потребителския вход и делегирането на изпълнението
 * към съответния обект Command.
 */
public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();
    /**
     * Конструктор, инициализира регистъра и регистрира всички стандартни команди.
     *
     * @param context Контекстът на приложението, който ще бъде подаден на командите.
     */
    public CommandRegistry(AppContext context) {
        register("checkin", new CheckinCommand(context));
        register("availability", new AvailabilityCommand(context));
        register("checkout", new CheckoutCommand(context));
        register("report", new ReportCommand(context));
        register("find", new FindCommand(context));
        register("find!", new FindForceCommand(context));
        register("unavailable", new UnavailableCommand(context));
        register("open", new OpenCommand(context));
        register("save", new SaveCommand(context));
        register("saveas", new SaveAsCommand(context));
        register("close", new CloseCommand(context));
        register("help", new HelpCommand(context));
    }

    public void register(String name, Command command) {
        commands.put(name.toLowerCase(), command);
    }

    /**
     * Изпълнява команда въз основа на подадения текстов ред от потребителя.
     * Парсва реда, намира командата в регистъра и извиква нейния execute метод.
     * Обработва CommandException.
     *
     * @param line Целият ред, въведен от потребителя.
     */
    public void executeCommand(String line) {
        String[] parts = line.trim().split("\\s+");
        if (line.isEmpty()) return;

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
