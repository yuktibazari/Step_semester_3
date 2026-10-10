
enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}

abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canApplyLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canApplyLeave(int days) {
        return days > 0 && days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canApplyLeave(int days) {
        return days > 0 && days <= 10;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    boolean canApplyLeave(int days) {
        return days > 0 && days <= 5;
    }
}

class LeaveRequest {
    Employee employee;
    String startDate;
    String endDate;
    int days;
    private LeaveStatus status = LeaveStatus.PENDING;

    LeaveRequest(Employee employee, String startDate,
                 String endDate, int days) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    LeaveStatus getStatus() {
        return status;
    }

    void review(boolean approve) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot review a request that is already " + status);
            return;
        }

        if (approve) {
            status = LeaveStatus.APPROVED;
            System.out.println(employee.name + "'s leave request (" +
                    startDate + "-" + endDate + ") approved.");
        } else {
            status = LeaveStatus.REJECTED;
            System.out.println(employee.name + "'s leave request (" +
                    startDate + "-" + endDate + ") rejected.");
        }
    }

    void changeStatus(LeaveStatus newStatus) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " +
                    status + " to " + newStatus);
            return;
        }

        if (newStatus == LeaveStatus.PENDING) {
            return;
        }

        status = newStatus;
    }
}

class LeaveService {
    static LeaveRequest submit(Employee e, String start,
                               String end, int days) {
        if (!e.canApplyLeave(days)) {
            System.out.println("Leave request not allowed for " + e.name);
            return null;
        }

        LeaveRequest r = new LeaveRequest(e, start, end, days);
        System.out.println("Leave request submitted for " + e.name +
                " (" + start + "-" + end + ").");
        System.out.println("Status: " + r.getStatus());
        return r;
    }
}

public class Main {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 = LeaveService.submit(
                john, "Jan 1", "Jan 5", 5);

        if (r1 != null) {
            r1.review(true);
            System.out.println("Status: " + r1.getStatus());
            r1.changeStatus(LeaveStatus.PENDING);
        }

        LeaveRequest r2 = LeaveService.submit(
                jane, "Feb 10", "Feb 11", 2);

        if (r2 != null) {
            r2.review(false);
            System.out.println("Status: " + r2.getStatus());
        }
    }
}
