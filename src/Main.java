import java.io.*;
import java.util.*;

public class Main {

    static class Reservation implements Serializable {
        private String reservationId;
        private String guestName;
        private String roomType;

        public Reservation(String reservationId, String guestName, String roomType) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public void display() {
            System.out.println(reservationId + " | " + guestName + " | " + roomType);
        }
    }

    static class RoomInventory implements Serializable {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 1);
        }

        public Map<String, Integer> getInventory() {
            return inventory;
        }

        public void display() {
            System.out.println("Inventory:");
            for (Map.Entry<String, Integer> e : inventory.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }
        }
    }

    static class BookingHistory implements Serializable {
        private List<Reservation> history = new ArrayList<>();

        public void add(Reservation r) {
            history.add(r);
        }

        public List<Reservation> getAll() {
            return history;
        }

        public void display() {
            System.out.println("\nBooking History:");
            for (Reservation r : history) {
                r.display();
            }
        }
    }

    static class PersistenceService {

        public void save(RoomInventory inventory, BookingHistory history) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
                out.writeObject(inventory);
                out.writeObject(history);
                System.out.println("\nData saved successfully.");
            } catch (Exception e) {
                System.out.println("\nError saving data.");
            }
        }

        public Object[] load() {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"))) {
                RoomInventory inventory = (RoomInventory) in.readObject();
                BookingHistory history = (BookingHistory) in.readObject();
                System.out.println("\nData loaded successfully.");
                return new Object[]{inventory, history};
            } catch (Exception e) {
                System.out.println("\nNo valid data found. Starting fresh.");
                return new Object[]{new RoomInventory(), new BookingHistory()};
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("     Welcome to Book My Stay App      ");

        PersistenceService service = new PersistenceService();

        Object[] data = service.load();

        RoomInventory inventory = (RoomInventory) data[0];
        BookingHistory history = (BookingHistory) data[1];

        inventory.display();
        history.display();

        history.add(new Reservation("RES201", "Rahul", "Single Room"));
        history.add(new Reservation("RES202", "Priya", "Double Room"));

        service.save(inventory, history);

        System.out.println("\nSystem recovery and persistence completed.");
    }
}