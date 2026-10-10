
enum MembershipStatus {
    ACTIVE, FROZEN, EXPIRED
}

interface MembershipPlan {
    double calculateFee();
    String getName();
}

class MonthlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000;
    }

    public String getName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 3 * 1000 * 0.90;
    }

    public String getName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {
    public double calculateFee() {
        return 12 * 1000 * 0.75;
    }

    public String getName() {
        return "Annual";
    }
}

class Member {
    String name;

    Member(String name) {
        this.name = name;
    }
}

class Membership {
    Member member;
    MembershipPlan plan;
    private MembershipStatus status = MembershipStatus.ACTIVE;

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;

        System.out.println(plan.getName() +
                " membership created for " + member.name + ".");
        System.out.printf("Fee: ₹%.2f%n", plan.calculateFee());
        System.out.println("Status: " + status);
    }

    void checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.println(member.name + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + member.name +
                    "'s membership is " + status + ".");
        }
    }

    void freeze() {
        if (status == MembershipStatus.ACTIVE) {
            status = MembershipStatus.FROZEN;
            System.out.println(member.name + "'s membership frozen.");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Cannot freeze an " + status +
                    " membership.");
        }
    }

    void unfreeze() {
        if (status == MembershipStatus.FROZEN) {
            status = MembershipStatus.ACTIVE;
            System.out.println(member.name + "'s membership unfrozen.");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Cannot unfreeze a " + status +
                    " membership.");
        }
    }

    void expire() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Membership is already expired.");
            return;
        }

        status = MembershipStatus.EXPIRED;
        System.out.println(member.name + "'s membership expired.");
        System.out.println("Status: " + status);
    }
}

public class Main {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership m1 = new Membership(asha, new QuarterlyPlan());
        Membership m2 = new Membership(ravi, new MonthlyPlan());

        m1.checkIn();
        m1.freeze();
        m1.checkIn();

        m2.expire();
        m2.freeze();
        m2.checkIn();
    }
}
