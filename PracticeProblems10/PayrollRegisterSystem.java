
import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract long calculatePay();
    abstract String getType();
}

class FullTime extends Staff {
    long salary;

    FullTime(String name, long salary) {
        super(name);
        this.salary = salary;
    }

    long calculatePay() {
        return salary;
    }

    String getType() {
        return "FullTime";
    }
}

class PartTime extends Staff {
    int hours;
    long rate;

    PartTime(String name, int hours, long rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    long calculatePay() {
        return hours * rate;
    }

    String getType() {
        return "PartTime";
    }
}

class Intern extends Staff {
    long stipend;

    Intern(String name, long stipend) {
        super(name);
        this.stipend = stipend;
    }

    long calculatePay() {
        return stipend;
    }

    String getType() {
        return "Intern";
    }
}

class Payslip {
    String name;
    String type;
    long pay;

    Payslip(Staff staff) {
        name = staff.name;
        type = staff.getType();
        pay = staff.calculatePay();
    }

    public String toString() {
        return "Payslip[name=" + name + ", type=" + type + ", pay=" + pay + "]";
    }
}

public class PayrollRegisterSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Payslip> list = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String name = sc.next();
            Staff staff = null;

            switch (type) {
                case "FullTime":
                    staff = new FullTime(name, sc.nextLong());
                    break;
                case "PartTime":
                    staff = new PartTime(name, sc.nextInt(), sc.nextLong());
                    break;
                case "Intern":
                    staff = new Intern(name, sc.nextLong());
                    break;
                default:
                    continue;
            }

            list.add(new Payslip(staff));
        }

        Payslip[] payslips = list.toArray(new Payslip[0]);
        long total = 0;
        Payslip topper = null;

        for (Payslip p : payslips) {
            System.out.println(p);
            total += p.pay;

            if (topper == null || p.pay > topper.pay)
                topper = p;
        }

        System.out.println("Total " + total);

        if (topper != null)
            System.out.println("top earner " + topper.name);

        sc.close();
    }
}
