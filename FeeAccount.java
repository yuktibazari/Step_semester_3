class FeeAccount {
    String name;

    FeeAccount(String name) {
        this.name = name;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String name) {
        super(name);
    }
}

public class Main {

    static int hostelCount = 0;
    static int dayScholarCount = 0;

    static void processPayment(FeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {
            System.out.println(
                "Paid in two installments (hostel account)"
            );
            hostelCount++;
        } else {
            System.out.println(
                "Paid in one go (day-scholar account)"
            );
            dayScholarCount++;
        }
    }

    public static void main(String[] args) {

        FeeAccount[] accounts = {
            new HostelFeeAccount("Hostel 1"),
            new HostelFeeAccount("Hostel 2"),
            new FeeAccount("Student 3"),
            new FeeAccount("Student 4")
        };

        double amount = 60000;

        for (FeeAccount account : accounts) {
            processPayment(account, amount);
        }

        System.out.println(
            "Hostel accounts processed: " + hostelCount
            + " | Day-scholar accounts processed: "
            + dayScholarCount
        );
    }
}