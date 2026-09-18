class ReportMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public ReportMember(String memberId, int borrowLimit) {
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

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class CourseMember extends ReportMember {
    private String course;

    public CourseMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course
                + " | Books: " + booksBorrowed;
    }
}

public class ReportTest {
    static String batchPrint(ReportMember[] members) {
        StringBuilder report = new StringBuilder();

        for (ReportMember member : members) {
            report.append(member.displayInfo());

            if (member instanceof CourseMember) {
                CourseMember student = (CourseMember) member;
                report.append(" [Course via downcast: ")
                        .append(student.getCourse())
                        .append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {
        ReportMember[] members = {
            new ReportMember("LB5", 3),
            new CourseMember("STU6", 3, "ECE")
        };

        System.out.println(batchPrint(members));
    }
}