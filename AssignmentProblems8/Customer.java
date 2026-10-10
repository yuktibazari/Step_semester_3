
import java.time.*;
import java.util.*;

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

abstract class Seat {
    String id;

    Seat(String id) {
        this.id = id;
    }

    abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String id) {
        super(id);
    }

    double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {
    PremiumSeat(String id) {
        super(id);
    }

    double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String id) {
        super(id);
    }

    double getPrice() {
        return 400;
    }
}

class Show {
    String name;
    LocalDateTime startTime;
    Set<String> bookedSeats = new HashSet<>();

    Show(String name, LocalDateTime startTime) {
        this.name = name;
        this.startTime = startTime;
    }
}

class Booking {
    Customer customer;
    Show show;
    List<Seat> seats;
    boolean active = true;

    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
    }

    double getTotal() {
        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    void cancel() {
        if (!active) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        if (!LocalDateTime.now().isBefore(show.startTime)) {
            System.out.println("Cannot cancel after the show starts.");
            return;
        }

        for (Seat seat : seats) {
            show.bookedSeats.remove(seat.id);
        }

        active = false;

        System.out.println(customer.name + "'s booking cancelled.");
        System.out.println("Seats " + getSeatNames() + " released.");
    }

    String getSeatNames() {
        List<String> names = new ArrayList<>();

        for (Seat seat : seats) {
            names.add(seat.id);
        }

        return String.join(", ", names);
    }
}

class TicketService {
    static Booking book(Customer customer, Show show, List<Seat> seats) {
        if (seats.isEmpty() || seats.size() > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            return null;
        }

        Set<String> requested = new HashSet<>();

        for (Seat seat : seats) {
            if (!requested.add(seat.id)) {
                System.out.println("Duplicate seat " + seat.id);
                return null;
            }

            if (show.bookedSeats.contains(seat.id)) {
                System.out.println("Seat " + seat.id +
                        " is already booked for this show.");
                return null;
            }
        }

        show.bookedSeats.addAll(requested);

        Booking booking = new Booking(customer, show, seats);

        System.out.println("Booking confirmed for " + customer.name +
                ": " + booking.getSeatNames() + ".");
        System.out.printf("Total: ₹%.2f%n", booking.getTotal());

        return booking;
    }
}

public class Main {
    public static void main(String[] args) {
        Show show = new Show("7 PM Show",
                LocalDateTime.of(2027, 12, 31, 19, 0));

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = TicketService.book(asha, show, Arrays.asList(
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5")));

        TicketService.book(ravi, show,
                Arrays.asList(new RegularSeat("A2")));

        TicketService.book(ravi, show,
                Arrays.asList(new ReclinerSeat("R1")));

        if (b1 != null) {
            b1.cancel();
        }

        TicketService.book(neha, show,
                Arrays.asList(new RegularSeat("A2")));
    }
}
