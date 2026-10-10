
import java.util.*;

interface Chargeable {
    boolean bookChargingBay();
}

abstract class Vehicle {
    String passNumber;
    String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int getPassFee();
    abstract String getType();
}

class Bike extends Vehicle {
    Bike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }

    String getType() {
        return "Bike";
    }
}

class Car extends Vehicle {
    Car(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }

    String getType() {
        return "Car";
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }

    String getType() {
        return "EBike";
    }

    public boolean bookChargingBay() {
        return true;
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }

    String getType() {
        return "ECar";
    }

    public boolean bookChargingBay() {
        return true;
    }
}

public class CampusVehiclePassSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String op = sc.next();

            if (op.equals("PASS")) {
                String type = sc.next();
                String pass = sc.next();
                String owner = sc.next();
                Vehicle v = null;

                switch (type) {
                    case "Bike": v = new Bike(pass, owner); break;
                    case "Car": v = new Car(pass, owner); break;
                    case "EBike": v = new EBike(pass, owner); break;
                    case "ECar": v = new ECar(pass, owner); break;
                }

                if (v != null) {
                    vehicles.put(pass, v);
                    System.out.println(pass + " (" + v.getType() + ") pass fee " + v.getPassFee());
                }
            } else if (op.equals("CHARGE")) {
                String pass = sc.next();
                Vehicle v = vehicles.get(pass);

                if (v instanceof Chargeable && ((Chargeable) v).bookChargingBay())
                    System.out.println(pass + " charging bay allotted");
                else
                    System.out.println(pass + " rejected: charging unsupported");
            }
        }
        sc.close();
    }
}
