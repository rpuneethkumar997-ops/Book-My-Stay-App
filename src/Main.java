import java.util.*;

public class Main {

    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }
    }

    static class BookingRequestQueue {
        private Queue<Reservation> queue = new LinkedList<>();

        public void addRequest(Reservation r) {
            queue.offer(r);
        }

        public Reservation getNextRequest() {
            return queue.poll();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 1);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }

        public void reduceAvailability(String type) {
            inventory.put(type, inventory.get(type) - 1);
        }
    }

    static class BookingService {
        private RoomInventory inventory;
        private Set<String> allocatedRoomIds = new HashSet<>();
        private Map<String, Set<String>> roomAllocations = new HashMap<>();

        public BookingService(RoomInventory inventory) {
            this.inventory = inventory;
        }

        public void processQueue(BookingRequestQueue queue) {
            while (!queue.isEmpty()) {
                Reservation r = queue.getNextRequest();
                String type = r.getRoomType();

                if (inventory.getAvailability(type) > 0) {
                    String roomId = generateRoomId(type);

                    allocatedRoomIds.add(roomId);

                    roomAllocations.putIfAbsent(type, new HashSet<>());
                    roomAllocations.get(type).add(roomId);

                    inventory.reduceAvailability(type);

                    System.out.println("Booking Confirmed: " + r.getGuestName() + " -> " + type + " | Room ID: " + roomId);
                } else {
                    System.out.println("Booking Failed: " + r.getGuestName() + " -> " + type + " (No Availability)");
                }
            }
        }

        private String generateRoomId(String type) {
            String id;
            do {
                id = type.substring(0, 2).toUpperCase() + new Random().nextInt(1000);
            } while (allocatedRoomIds.contains(id));
            return id;
        }
    }

    public static void main(String[] args) {

        System.out.println("     Welcome to Book My Stay App      ");

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Rahul", "Single Room"));
        queue.addRequest(new Reservation("Priya", "Single Room"));
        queue.addRequest(new Reservation("Arun", "Single Room"));
        queue.addRequest(new Reservation("Kiran", "Suite Room"));

        RoomInventory inventory = new RoomInventory();

        BookingService service = new BookingService(inventory);

        service.processQueue(queue);

        System.out.println("\nProcessing completed.");
    }
}