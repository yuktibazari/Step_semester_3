
import java.time.*;
import java.time.temporal.ChronoUnit;

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

abstract class Assignment {
    String title;
    int maxMarks;
    LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    abstract double getPenaltyRate();
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double getPenaltyRate() {
        return 0.10;
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double getPenaltyRate() {
        return 0.20;
    }
}

class Submission {
    Student student;
    Assignment assignment;
    LocalDate submissionDate;
    private String status = "Submitted";
    private boolean submitted = true;

    Submission(Student student, Assignment assignment,
               LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
    }

    void grade(int awardedMarks) {
        if (!submitted || !status.equals("Submitted")) {
            System.out.println("Cannot grade this submission.");
            return;
        }

        if (awardedMarks < 0 || awardedMarks > assignment.maxMarks) {
            System.out.println("Invalid marks.");
            return;
        }

        long lateDays = Math.max(0,
                ChronoUnit.DAYS.between(
                        assignment.dueDate, submissionDate));

        double penalty = lateDays * assignment.getPenaltyRate();
        double finalMarks = awardedMarks * (1 - penalty);

        finalMarks = Math.max(0, finalMarks);
        status = "Graded";

        if (lateDays == 0) {
            System.out.printf("%s graded: %.0f/%d.%n",
                    student.name, finalMarks, assignment.maxMarks);
        } else {
            System.out.printf("%s graded: %.0f/%d after %.0f%% late penalty.%n",
                    student.name, finalMarks, assignment.maxMarks,
                    penalty * 100);
        }

        System.out.println("Status: " + status);
    }

    void resubmit(LocalDate newDate) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" +
                    assignment.title + "' has already been graded.");
            return;
        }

        submissionDate = newDate;
        System.out.println("Submission updated for " + student.name);
    }
}

public class Main {
    public static void main(String[] args) {
        LocalDate mar10 = LocalDate.of(2027, 3, 10);
        LocalDate mar12 = LocalDate.of(2027, 3, 12);
        LocalDate mar14 = LocalDate.of(2027, 3, 14);

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
                "Linked List Lab", 50, mar10);

        Assignment written = new WrittenAssignment(
                "Design Essay", 50, mar12);

        Submission s1 = new Submission(asha, coding, mar10);
        Submission s2 = new Submission(ravi, written, mar14);

        System.out.println("Asha's submission for 'Linked List Lab' received (on time). Status: Submitted.");
        System.out.println("Ravi's submission for 'Design Essay' received (2 days late). Status: Submitted.");

        s1.grade(45);
        s2.grade(40);

        s1.resubmit(mar14);
    }
}
