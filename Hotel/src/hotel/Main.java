package hotel;

import hotel.commands.CommandRegistry;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel();
        CommandRegistry registry = new CommandRegistry();
        Scanner sc = new Scanner(System.in);

        try {
            hotel.addRoom(new Room(100,4));
            hotel.addRoom(new Room(101,3));
            hotel.addRoom(new Room(102,4));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            registry.executeCommand(input, hotel);
            }
        }
    }