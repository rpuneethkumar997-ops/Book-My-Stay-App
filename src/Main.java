import java.util.*;

public class Main {

    static class AddOnService {
        private String name;
        private double price;

        public AddOnService(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    static class AddOnServiceManager {
        private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

        public void addService(String reservationId, AddOnService service) {
            serviceMap.putIfAbsent(reservationId, new ArrayList<>());
            serviceMap.get(reservationId).add(service);
        }

        public double calculateTotalCost(String reservationId) {
            double total = 0;
            List<AddOnService> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());
            for (AddOnService s : services) {
                total += s.getPrice();
            }
            return total;
        }

        public void displayServices(String reservationId) {
            List<AddOnService> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());
            System.out.println("Services for Reservation ID: " + reservationId);
            for (AddOnService s : services) {
                System.out.println(s.getName() + " - ₹" + s.getPrice());
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("     Welcome to Book My Stay App      ");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES101";

        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1200));
        manager.addService(reservationId, new AddOnService("Extra Bed", 800));

        manager.displayServices(reservationId);

        double total = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-On Cost: ₹" + total);

        System.out.println("\nAdd-on services processed successfully.");
    }
}