import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        String command = sc.nextLine();

        while (!command.equals("exit")) {
            String[] tokens = command.split(" ");
            if (tokens[0].equals("checkin")) {
                int roomNumber = Integer.parseInt(tokens[1]);
                try {
                    LocalDate from = LocalDate.parse(tokens[2], formatter);
                    LocalDate to = LocalDate.parse(tokens[3], formatter);
                    hotel.checkin(roomNumber, from, to, tokens[4]);
                }catch (DateTimeException e) {
                    System.out.println(e.getMessage());
                }
            }

            command = sc.nextLine();
        }
    }
}