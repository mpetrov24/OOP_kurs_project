import java.util.ArrayList;

public class Hotel {
    private ArrayList<Room> rooms;

    Hotel() {
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        for (Room r : rooms) {
            if (r.getNumber() == room.getNumber()) {
                System.out.println("Room with this number already exists");
                return;
            }
        }
        rooms.add(room);
    }
}
