import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Hotel hotel = new Hotel();
        try {
            hotel.addRoom(new Room(100,4));
            hotel.addRoom(new Room(101,3));
            hotel.addRoom(new Room(102,4));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            String[] tokens = input.split(" ");

            String command = tokens[0];

            switch(command) {
                case "checkin": {
                    int guests = -1;
                    int roomNumber;
                    LocalDate from;
                    LocalDate to;
                    try {
                        roomNumber = Integer.parseInt(tokens[1]);
                        from = dateValidator(tokens[2], formatter);
                        to = dateValidator(tokens[3], formatter);

                        if (from == null || to == null) {
                            break;
                        }

                        if (tokens.length < 5) {
                            System.out.println("Too few arguments");
                            break;
                        }

                        if (to.isBefore(from)) {
                            System.out.println("Check-out date cannot be before check-in date.");
                            break;
                        }

                        if (from.isBefore(LocalDate.now())) {
                            System.out.println("Check-in date cannot be before current date");
                            break;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Room number and guests need to be a valid number");
                        break;
                    }

                    try {
                        guests = Integer.parseInt(tokens[tokens.length - 1]);
                    } catch (NumberFormatException ignored) {

                    }

                    int noteEndIdx = guests == -1 ? tokens.length : tokens.length - 1;
                    String note = String.join(" ", Arrays.copyOfRange(tokens, 4, noteEndIdx));

                    if (guests == -1) {
                        guests = Hotel.findRoom(roomNumber).getBeds();
                    }

                    hotel.checkin(roomNumber, from, to, note, guests);

                    break;
                }

                case "availability": {
                    LocalDate date;
                    if (tokens.length == 1) {
                        date = LocalDate.now();
                    } else {
                        date = dateValidator(tokens[1], formatter);
                    }

                    if (date == null) {
                        break;
                    }
                    hotel.availability(date);
                    break;
                }

                case "checkout": {
                    if (tokens.length > 2) {
                        System.out.println("Too many arguments!");
                        break;
                    }
                    try {
                        int roomNumber = Integer.parseInt(tokens[1]);
                        hotel.checkout(roomNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid room number");
                    }
                    break;
                }

                case "exit":
                    return;
            }
        }
    }

    public static LocalDate dateValidator(String dateStr, DateTimeFormatter formatter) {
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, formatter);
        } catch (DateTimeException e) {
            System.out.println("Invalid date");
            return null;
        }
        return date;
    }
}