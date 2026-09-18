class MemberBase {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public MemberBase(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: " + booksBorrowed);
    }
}

class StudentBranch extends MemberBase {
    protected String course;

    public StudentBranch(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Member | Course: " + course
                + " | Books Borrowed: " + booksBorrowed);
    }
}

class HonorsMember extends StudentBranch {
    private int bonusLimit;

    public HonorsMember(String memberId, int borrowLimit,
                        String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public void displayInfo() {
        System.out.println("Honors Student Member | Course: " + course
                + " | Bonus Limit: " + bonusLimit
                + " | Books Borrowed: " + booksBorrowed);
    }
}

class StaffMember extends MemberBase {
    private String department;

    public StaffMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println("Faculty Member | Department: " + department
                + " | Books Borrowed: " + booksBorrowed);
    }
}

public class InheritanceTest {
    static String classifyGeneration(MemberBase member) {
        if (member instanceof HonorsMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof StaffMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof StudentBranch) {
            return "Student branch";
        }

        return "General Member";
    }

    static int getTotalBooksBorrowed(MemberBase[] members) {
        int total = 0;

        for (MemberBase member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }

    public static void main(String[] args) {
        MemberBase general = new MemberBase("STU1", 3);
        StudentBranch student = new StudentBranch("STU2", 3, "CSE");
        HonorsMember honors = new HonorsMember("STU3", 3, "ECE", 2);
        StaffMember faculty = new StaffMember("STU4", 5, "Physics");

        general.displayInfo();
        student.displayInfo();
        honors.displayInfo();
        faculty.displayInfo();

        System.out.println(classifyGeneration(honors));
        System.out.println(classifyGeneration(faculty));

        student.borrowBook();
        student.borrowBook();

        honors.borrowBook();

        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        MemberBase[] members = {student, honors, faculty};

        System.out.println(getTotalBooksBorrowed(members));
    }
}