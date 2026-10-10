
import java.time.*;
import java.util.*;

abstract class Room {
    String id;

    Room(String id) {
        this.id = id;
    }

    abstract double calculatePrice(long nights);
}

class StandardRoom extends Room {
    StandardRoom(String id) {
        super(id);
    }

    double calculatePrice(long nights) {
        return nights * 100;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String id) {
        super(id);
    }

    double calculatePrice(long nights) {
        return nights * 200;
    }
}

class Suite extends Room {
    Suite(String id) {
        super(id);
    }

    double calculatePrice(long nights) {
        return nights * 350;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {
    Customer customer;
    Room room;
    LocalDate start;
    LocalDate end;
    boolean active = true;

    Reservation(Customer customer, Room room,
                LocalDate start, LocalDate end) {
        this.customer = customer;
        this.room = room;
        this.start = start;
        this.end = end;
    }

    double getPrice() {
        return room.calculatePrice(
                ChronoUnit.DAYS.between(start, end));
    }

    boolean overlaps(LocalDate newStart, LocalDate newEnd) {
        return active && start.isBefore(newEnd)
                && newStart.isBefore(end);
    }

    void cancel() {
        LocalDate deadline = start.minusDays(2);

        if (!active) {
            System.out.println("Reservation is already cancelled");
        } else if (LocalDate.now().isAfter(deadline)) {
            System.out.println("Cancellation deadline has passed");
        } else {
            active = false;
            System.out.println("Reservation for " + customer.name +
                    ", " + room.id + " (" + start + "-" + end +
                    ") cancelled successfully.");
        }
    }
}

class HotelService {
    List<Reservation> reservations = new ArrayList<>();

    boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        if (!start.isBefore(end)) {
            return false;
        }

        for (Reservation r : reservations) {
            if (r.room.id.equals(room.id) &&
                    r.overlaps(start, end)) {
                return false;
            }
        }

        return true;
    }

    Reservation book(Customer customer, Room room,
                     LocalDate start, LocalDate end) {
        if (!isAvailable(room, start, end)) {
            System.out.println(room.id + " is not available from " +
                    start + " to " + end);
            return null;
        }

        Reservation r = new Reservation(customer, room, start, end);
        reservations.add(r);

        System.out.println("Reservation confirmed for " +
                customer.name + ", " + room.id +
                " (" + start + "-" + end + ").");
        System.out.println("Price: $" + r.getPrice());
        return r;
    }
}

public class Main {
    public static void main(String[] args) {
        HotelService hotel = new HotelService();

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        Room standard = new StandardRoom("Standard Room 101");
        Room deluxe = new DeluxeRoom("Deluxe Room 201");

        LocalDate jan1 = LocalDate.of(2027, 1, 1);
        LocalDate jan5 = LocalDate.of(2027, 1, 5);
        LocalDate jan3 = LocalDate.of(2027, 1, 3);
        LocalDate jan7 = LocalDate.of(2027, 1, 7);

        Reservation r = hotel.book(a, standard, jan1, jan5);
        hotel.book(b, standard, jan3, jan7);

        if (r != null) {
            r.cancel();
        }

        hotel.book(c, deluxe,
                LocalDate.of(2027, 2, 10),
                LocalDate.of(2027, 2, 12));
    }
}
