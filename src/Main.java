public class Main {

    abstract static class Room {
        protected String roomType;
        protected int numberOfBeds;
        protected double pricePerNight;

        public Room(String roomType, int numberOfBeds, double pricePerNight) {
            this.roomType = roomType;
            this.numberOfBeds = numberOfBeds;
            this.pricePerNight = pricePerNight;
        }
        public abstract void displayDetails();
    }
    static class SingleRoom extends Room {

        public SingleRoom() {
            super("Single Room", 1, 1500.0);
        }

        @Override
        public void displayDetails() {
            System.out.println("Room Type : " + roomType);
            System.out.println("Beds      : " + numberOfBeds);
            System.out.println("Price     : ₹" + pricePerNight);
        }
    }
    static class DoubleRoom extends Room {

        public DoubleRoom() {
            super("Double Room", 2, 2500.0);
        }

        @Override
        public void displayDetails() {
            System.out.println("Room Type : " + roomType);
            System.out.println("Beds      : " + numberOfBeds);
            System.out.println("Price     : ₹" + pricePerNight);
        }
    }

    static class SuiteRoom extends Room {

        public SuiteRoom() {
            super("Suite Room", 3, 5000.0);
        }

        @Override
        public void displayDetails() {
            System.out.println("Room Type : " + roomType);
            System.out.println("Beds      : " + numberOfBeds);
            System.out.println("Price     : ₹" + pricePerNight);
        }
    }

    public static void main(String[] args) {

        System.out.println("     Welcome to Book My Stay App      ");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;
        System.out.println("----- Room Details & Availability -----\n");

        single.displayDetails();
        System.out.println("Available : " + singleAvailability);

        doubleRoom.displayDetails();
        System.out.println("Available : " + doubleAvailability);

        suite.displayDetails();
        System.out.println("Available : " + suiteAvailability);

        System.out.println("\nApplication executed successfully.");
    }
}