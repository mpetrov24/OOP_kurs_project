package hotel.commands;

/**
 * Специфично изключение, което се хвърля при възникване на грешки по време на изпълнение на команди.
 */
public class CommandException extends Exception{
    /**
     * Създава нова инстанция на CommandException със зададеното съобщение за грешка.
     *
     * @param message Съобщението, описващо грешката.
     */
    public CommandException(String message) {
        super(message);
    }
}
