package hotel;

import hotel.commands.CommandRegistry;
import hotel.io.HotelLoader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel();
        AppContext context = new AppContext(hotel);
        CommandRegistry registry = new CommandRegistry(context);
        Scanner sc = new Scanner(System.in);


        try {
            HotelLoader.loadRooms("data/rooms.txt", hotel);
            HotelLoader.loadReservations("data/reservations.txt", hotel);
            HotelLoader.loadUnavailable("data/unavailable.txt", hotel);

            context.setReservationFile(new File("./data/reservations.txt"));
            context.setUnavailableFile(new File("./data/unavailable.txt"));

            System.out.println("Hotel data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Failed to load hotel data: " + e.getMessage());
        }

        System.out.println("Type help to see all available commands");

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            registry.executeCommand(input);
        }
    }
}