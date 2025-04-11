package commands;

import java.time.DateTimeException;
import java.time.LocalDate;

public abstract class Command {
   public abstract void execute(String[] args) throws CommandException;

   protected LocalDate dateParser(String dateStr) throws CommandException {
      LocalDate date;
      try {
         date = LocalDate.parse(dateStr);
      } catch (DateTimeException e) {
         throw new CommandException("Invalid date: " + dateStr + ". Use YYYY-MM-DD.");
      }
      return date;
   }
}
