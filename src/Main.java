import java.util.HashMap;
import java.util.Map;

public class Main {

    static class RoomInventory {

        private Map<String, Integer> inventory;


        public RoomInventory() {
            inventory = new HashMap<>();

            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 2);
        }

        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        public void updateAvailability(String roomType, int change) {
            int current = inventory.getOrDefault(roomType, 0);
            inventory.put(roomType, current + change);
        }

        public void displayInventory() {
            System.out.println("----- Current Room Inventory -----");

            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("     Welcome to Book My Stay App      ");


        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\n--- Updating Inventory ---");

        inventory.updateAvailability("Single Room", -1);
        inventory.updateAvailability("Suite Room", +1);

        System.out.println();
        inventory.displayInventory();

        System.out.println("\nApplication executed successfully.");
    }
}