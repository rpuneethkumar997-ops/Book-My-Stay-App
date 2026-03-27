import java.util.*;

public class Main{

    static class Reservation {
        private String reservationId;
        private String roomType;
        private String roomId;
        private boolean active;

        public Reservation(String reservationId, String roomType, String roomId) {
            this.reservationId = reservationId;
            this.roomType = roomType;
            this.roomId = roomId;
            this.active = true;
        }

        public String getReservationId() {
            return reservationId;
        }

        public String getRoomType() {
            return roomType;
        }

        public String getRoomId() {
            return roomId;
        }

        public boolean isActive() {
            return active;
        }

        public void cancel() {
            this.active = false;
        }
    }

    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 1);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 1);
        }

        public void increaseAvailability(String type) {
            inventory.put(type, inventory.getOrDefault(type, 0) + 1);
        }

        public void display() {
            System.out.println("\nCurrent Inventory:");
            for (Map.Entry<String, Integer> e : inventory.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }
        }
    }

    static class BookingHistory {
        private Map<String, Reservation> history = new HashMap<>();

        public void add(Reservation r) {
            history.put(r.getReservationId(), r);
        }

        public Reservation get(String id) {
            return history.get(id);
        }
    }

    static class CancellationService {
        private RoomInventory inventory;
        private BookingHistory history;
        private Stack<String> rollbackStack = new Stack<>();

        public CancellationService(RoomInventory inventory, BookingHistory history) {
            this.inventory = inventory;
            this.history = history;
        }

        public void cancel(String reservationId) {
            Reservation r = history.get(reservationId);

            if (r == null) {
                System.out.println("Cancellation Failed: Reservation not found");
                return;
            }

            if (!r.isActive()) {
                System.out.println("Cancellation Failed: Already cancelled");
                return;
            }

            rollbackStack.push(r.getRoomId());

            inventory.increaseAvailability(r.getRoomType());

            r.cancel();

            System.out.println("Cancellation Successful: " + reservationId + " | Room Released: " + r.getRoomId());
        }
    }

    public static void main(String[] args) {
        System.out.println("     Welcome to Book My Stay App      ");

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("RES101", "Single Room", "SI101");
        Reservation r2 = new Reservation("RES102", "Double Room", "DO201");

        history.add(r1);
        history.add(r2);

        CancellationService service = new CancellationService(inventory, history);

        service.cancel("RES101");
        service.cancel("RES101");
        service.cancel("INVALID");

        inventory.display();

        System.out.println("\nCancellation process completed.");
    }
}