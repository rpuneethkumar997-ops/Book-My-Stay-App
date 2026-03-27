import java.util.*;

public class Main {

    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 1);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 0);
        }

        public int getAvailability(String type) throws InvalidBookingException {
            if (!inventory.containsKey(type)) {
                throw new InvalidBookingException("Invalid room type: " + type);
            }
            return inventory.get(type);
        }

        public void reduceAvailability(String type) throws InvalidBookingException {
            int current = getAvailability(type);
            if (current <= 0) {
                throw new InvalidBookingException("No availability for room type: " + type);
            }
            inventory.put(type, current - 1);
        }
    }

    static class BookingService {
        private RoomInventory inventory;

        public BookingService(RoomInventory inventory) {
            this.inventory = inventory;
        }

        public void book(String guestName, String roomType) {
            try {
                if (guestName == null || guestName.trim().isEmpty()) {
                    throw new InvalidBookingException("Guest name cannot be empty");
                }

                int available = inventory.getAvailability(roomType);

                if (available <= 0) {
                    throw new InvalidBookingException("Room not available: " + roomType);
                }

                inventory.reduceAvailability(roomType);

                System.out.println("Booking Confirmed: " + guestName + " -> " + roomType);

            } catch (InvalidBookingException e) {
                System.out.println("Booking Failed: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("     Welcome to Book My Stay App      ");

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.book("Rahul", "Single Room");
        service.book("", "Double Room");
        service.book("Priya", "Suite Room");
        service.book("Arun", "Invalid Room");

        System.out.println("\nValidation completed.");
    }
}