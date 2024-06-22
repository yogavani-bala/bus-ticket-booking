import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusBookingSystem {
    public static class Bus {
        public int busID;
        public int seats;
        public double fare;
        public String busType;
        public List<String> passengerDetails;

        public Bus(int busID, int seats, double fare, String busType) {
            this.busID = busID;
            this.seats = seats;
            this.fare = fare;
            this.busType = busType;
            this.passengerDetails = new ArrayList<>();
        }

        public void bookSeat(String passengerName, int tickets) {
            if (tickets > seats) {
                System.out.println("Not Enough Seats Available.");
                return;
            }
            String passengerDetail = "Passenger: " + passengerName + " -- Bus Type: " + busType +
                    " -- Number of Tickets: " + tickets + " -- Total Fare: Rs1" + (fare * tickets);
            passengerDetails.add(passengerDetail);
            seats -= tickets; // Reduce available seats
            System.out.println("Booking Successful!");
        }

        public void cancelBooking(String passengerName) {
            boolean found = false;
            for (String details : passengerDetails) {
                if (details.contains(passengerName)) {
                    passengerDetails.remove(details);
                    seats += getTicketsFromDetails(details); // Increase available seats
                    System.out.println("Booking for Passenger: " + passengerName + " canceled.");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No booking found for Passenger: " + passengerName);
            }
        }

        private int getTicketsFromDetails(String details) {
            String[] parts = details.split("--");
            String ticketsPart = parts[2].trim(); // "Number of Tickets: X"
            String ticketsValue = ticketsPart.split(":")[1].trim(); // "X"
            return Integer.parseInt(ticketsValue);
        }

        public void busSummary() {
            System.out.println("Bus ID: " + busID + ", Type: " + busType +
                    ", Seats Available: " + seats + ", Fare per Ticket: Rs" + fare);
        }

        public void printDetails() {
            System.out.println("Bus Details for Bus ID " + busID + ":");
            if (passengerDetails.isEmpty()) {
                System.out.println("No passenger details available.");
            } else {
                for (String details : passengerDetails) {
                    System.out.println(details);
                }
            }
        }
    }

    public static void bookSeat(Bus currentBus, String passengerName, int tickets) {
        currentBus.bookSeat(passengerName, tickets);
        currentBus.busSummary();
        currentBus.printDetails();
    }

    public static void cancelBooking(Bus currentBus, String passengerName) {
        currentBus.cancelBooking(passengerName);
        currentBus.busSummary();
        currentBus.printDetails();
    }

    public static void printDetails(Bus b) {
        b.printDetails();
    }

    public static void main(String[] args) {
        ArrayList<Bus> buses = new ArrayList<>();
        buses.add(new Bus(1, 40, 200.0, "AC")); // Example bus with ID 1, 40 seats, Rs200 fare per ticket, AC
        buses.add(new Bus(2, 30, 250.0, "Non-AC")); // Example bus with ID 2, 30 seats, Rs250 fare per ticket, Non-AC

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.println("BUS BOOKING");
            System.out.println("\nMenu:");
            System.out.println("1. Book Seat");
            System.out.println("2. Cancel Booking");
            System.out.println("3. Print Bus Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.print("Enter Bus ID: ");
                    int busID = sc.nextInt();

                    if (busID < 1 || busID > buses.size()) {
                        System.out.println("Invalid Bus ID.");
                        break;
                    }

                    Bus currentBus = buses.get(busID - 1);

                    sc.nextLine(); // Consume newline left-over
                    System.out.print("Enter passenger name: ");
                    String passengerName = sc.nextLine();

                    System.out.print("Enter number of tickets to book: ");
                    int ticketsToBook = sc.nextInt();

                    bookSeat(currentBus, passengerName, ticketsToBook);
                    break;

                case 2:
                    System.out.print("Enter Bus ID: ");
                    int cancelBusID = sc.nextInt();

                    if (cancelBusID < 1 || cancelBusID > buses.size()) {
                        System.out.println("Invalid Bus ID.");
                        break;
                    }

                    Bus busToCancel = buses.get(cancelBusID - 1);

                    sc.nextLine(); // Consume newline left-over
                    System.out.print("Enter passenger name to cancel booking: ");
                    String cancelPassengerName = sc.nextLine();

                    cancelBooking(busToCancel, cancelPassengerName);
                    break;

                case 3:
                    for (Bus bus : buses) {
                        System.out.println("\nBus Details for Bus ID " + bus.busID + ":");
                        printDetails(bus);
                    }
                    break;

                case 4:
                    System.out.println("Exiting Program.");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice. Please enter a valid choice.");
                    break;
            }
        }
    }
}

