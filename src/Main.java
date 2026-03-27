import java.util.LinkedList;
import java.util.Queue;

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

        public void display() {
            System.out.println("Guest: " + guestName + " | Room: " + roomType);
        }
    }

    static class BookingRequestQueue {
        private Queue<Reservation> queue;

        public BookingRequestQueue() {
            queue = new LinkedList<>();
        }

        public void addRequest(Reservation reservation) {
            queue.offer(reservation);
        }

        public void displayQueue() {
            System.out.println("Booking Requests (FIFO Order):\n");
            for (Reservation r : queue) {
                r.display();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("     Welcome to Book My Stay App      ");

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.addRequest(new Reservation("Rahul", "Single Room"));
        requestQueue.addRequest(new Reservation("Priya", "Double Room"));
        requestQueue.addRequest(new Reservation("Arun", "Suite Room"));

        requestQueue.displayQueue();

        System.out.println("\nRequests queued successfully.");
    }
}