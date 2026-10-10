
abstract class Vehicle {
    String id;
    boolean available = true;

    Vehicle(String id) {
        this.id = id;
    }

    abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String id) {
        super(id);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    SUV(String id) {
        super(id);
    }

    double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    Truck(String id) {
        super(id);
    }

    double calculateCharge(int days) {
        return days * 120;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    Vehicle vehicle;
    Customer customer;
    int days;
    boolean active;

    Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.active = true;
    }

    void returnVehicle() {
        vehicle.available = true;
        active = false;
        System.out.println(vehicle.id + " returned by " + customer.name);
    }
}

class RentalService {
    static Rental rentVehicle(Vehicle v, Customer c, int days) {
        if (!v.available) {
            System.out.println(v.id + " is currently unavailable");
            return null;
        }

        v.available = false;
        Rental r = new Rental(v, c, days);
        System.out.println(v.id + " rented successfully by " + c.name);
        System.out.println("Rental charge: $" + v.calculateCharge(days));
        return r;
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Rental r1 = RentalService.rentVehicle(sedan, c1, 3);
        RentalService.rentVehicle(sedan, c2, 2);

        if (r1 != null) {
            r1.returnVehicle();
        }

        RentalService.rentVehicle(suv, c3, 5);
    }
}
