package hotel.commands;

import hotel.AppContext;

import java.time.DateTimeException;
import java.time.LocalDate;

public abstract class Command {
   protected AppContext context;

   public Command(AppContext context) {
      this.context = context;
   }
   /**
    * Изпълнява логиката на конкретната команда.
    * Този метод трябва да бъде имплементиран от всички конкретни класове за команди.
    *
    * @param args Масив от аргументи, подадени към командата.
    * @throws CommandException Ако възникне грешка при изпълнението на командата (напр. невалидни аргументи, липсващи данни).
    */
   public abstract void execute(String[] args) throws CommandException;

   /**
    * Парсва текстов низ в обект LocalDate.
    * Очаква формат YYYY-MM-DD.
    *
    * @param dateStr Текстовият низ, представляващ датата.
    * @return Обект LocalDate, съответстващ на парснатата дата.
    * @throws CommandException Ако низът не е във валиден формат за дата.
    */
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
