import java.util.*;

public class Main {

    static class Reservation {
        private String reservationId;
        private String guestName;
        private String roomType;

        public Reservation(String reservationId, String guestName, String roomType) {
            this.reservationId = reservationId;
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getReservationId() {
            return reservationId;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }

        public void display() {
            System.out.println("Reservation ID: " + reservationId +
                    " | Guest: " + guestName +
                    " | Room: " + roomType);
        }
    }

    static class BookingHistory {
        private List<Reservation> history = new ArrayList<>();

        public void addReservation(Reservation r) {
            history.add(r);
        }

        public List<Reservation> getAllReservations() {
            return history;
        }
    }

    static class BookingReportService {

        public void displayAllBookings(List<Reservation> reservations) {
            System.out.println("Booking History:\n");
            for (Reservation r : reservations) {
                r.display();
            }
        }

        public void generateSummary(List<Reservation> reservations) {
            Map<String, Integer> summary = new HashMap<>();

            for (Reservation r : reservations) {
                summary.put(r.getRoomType(),
                        summary.getOrDefault(r.getRoomType(), 0) + 1);
            }

            System.out.println("\nBooking Summary:");
            for (Map.Entry<String, Integer> entry : summary.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("     Welcome to Book My Stay App      ");

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("RES101", "Rahul", "Single Room"));
        history.addReservation(new Reservation("RES102", "Priya", "Double Room"));
        history.addReservation(new Reservation("RES103", "Arun", "Suite Room"));
        history.addReservation(new Reservation("RES104", "Kiran", "Single Room"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history.getAllReservations());

        reportService.generateSummary(history.getAllReservations());

        System.out.println("\nReporting completed successfully.");
    }
}