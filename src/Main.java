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

    static class BookingQueue {
        private Queue<Reservation> queue = new LinkedList<>();

        public synchronized void add(Reservation r) {
            queue.offer(r);
        }

        public synchronized Reservation get() {
            return queue.poll();
        }
    }

    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 1);
        }

        public synchronized boolean allocate(String type) {
            int available = inventory.getOrDefault(type, 0);
            if (available > 0) {
                inventory.put(type, available - 1);
                return true;
            }
            return false;
        }

        public synchronized void display() {
            System.out.println("\nFinal Inventory:");
            for (Map.Entry<String, Integer> e : inventory.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }
        }
    }

    static class BookingProcessor extends Thread {
        private BookingQueue queue;
        private RoomInventory inventory;

        public BookingProcessor(BookingQueue queue, RoomInventory inventory) {
            this.queue = queue;
            this.inventory = inventory;
        }

        public void run() {
            while (true) {
                Reservation r = queue.get();
                if (r == null) break;

                boolean success = inventory.allocate(r.getRoomType());

                if (success) {
                    System.out.println(Thread.currentThread().getName() +
                            " Confirmed: " + r.getGuestName() +
                            " -> " + r.getRoomType());
                } else {
                    System.out.println(Thread.currentThread().getName() +
                            " Failed: " + r.getGuestName() +
                            " -> " + r.getRoomType());
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("     Welcome to Book My Stay App      ");

        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();

        queue.add(new Reservation("Rahul", "Single Room"));
        queue.add(new Reservation("Priya", "Single Room"));
        queue.add(new Reservation("Arun", "Single Room"));
        queue.add(new Reservation("Kiran", "Suite Room"));
        queue.add(new Reservation("Sneha", "Suite Room"));

        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inventory.display();

        System.out.println("\nConcurrent processing completed.");
    }
}