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
                case "checkin":
                    int guests = -1;
                    int roomNumber = 0;
                    LocalDate from = null;
                    LocalDate to = null;
                    try {
                        roomNumber = Integer.parseInt(tokens[1]);
                        from = LocalDate.parse(tokens[2], formatter);
                        to = LocalDate.parse(tokens[3], formatter);

                        if (to.isBefore(from)) {
                            System.out.println("Check-out date cannot be before check-in date.");
                            break;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Room number and guests need to be a valid number");
                    } catch (DateTimeException e) {
                        System.out.println("Invalid date");
                    }

                    try {
                        guests = Integer.parseInt(tokens[tokens.length - 1]);
                    } catch (NumberFormatException _) {

                    }

                    int noteEndIdx = guests == -1 ? tokens.length : tokens.length - 1;
                    String note = String.join(" ", Arrays.copyOfRange(tokens, 4, noteEndIdx));

                    if (guests == -1) {
                        guests = Hotel.findRoom(roomNumber).getBeds();
                    }

                    hotel.checkin(roomNumber, from, to, note, guests);

                    break;

                case "exit":
                    return;
            }
        }
    }
}