import java.util.HashMap;
import java.util.Map;

public class Main {

    abstract static class Room {
        protected String roomType;
        protected int beds;
        protected double price;

        public Room(String roomType, int beds, double price) {
            this.roomType = roomType;
            this.beds = beds;
            this.price = price;
        }

        public String getRoomType() {
            return roomType;
        }

        public abstract void displayDetails();
    }

    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 1500.0);
        }

        public void displayDetails() {
            System.out.println(roomType + " | Beds: " + beds + " | Price: ₹" + price);
        }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 2500.0);
        }

        public void displayDetails() {
            System.out.println(roomType + " | Beds: " + beds + " | Price: ₹" + price);
        }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 5000.0);
        }

        public void displayDetails() {
            System.out.println(roomType + " | Beds: " + beds + " | Price: ₹" + price);
        }
    }

    static class RoomInventory {
        private Map<String, Integer> inventory;

        public RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 0);
            inventory.put("Suite Room", 2);
        }

        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }
    }

    static class SearchService {
        private RoomInventory inventory;

        public SearchService(RoomInventory inventory) {
            this.inventory = inventory;
        }

        public void search(Room[] rooms) {
            System.out.println("Available Rooms:\n");
            for (Room room : rooms) {
                int available = inventory.getAvailability(room.getRoomType());
                if (available > 0) {
                    room.displayDetails();
                    System.out.println("Available: " + available);
                    System.out.println("----------------------");
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("     Welcome to Book My Stay App      ");


        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        SearchService searchService = new SearchService(inventory);

        searchService.search(rooms);

        System.out.println("\nSearch completed successfully.");
    }
}