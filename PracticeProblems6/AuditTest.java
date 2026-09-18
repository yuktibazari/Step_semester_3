class NumberedMember {
    public final String memberNumber;
    protected int borrowLimit;
    protected int booksBorrowed;
    private static int membersEnrolled = 100;
    private String lastGenre;

    public NumberedMember(int borrowLimit) {
        membersEnrolled++;
        memberNumber = "LIB-" + membersEnrolled;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        lastGenre = genre;
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    static int getMembersEnrolled() {
        return membersEnrolled - 100;
    }
}

class FacultyBranch extends NumberedMember {
    private String department;

    public FacultyBranch(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}

public class AuditTest {
    static String processNightlyAudit(NumberedMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (NumberedMember member : members) {
            if (member == null) {
                nullSkipped++;
            } else {
                processed++;

                if (member instanceof FacultyBranch) {
                    faculty++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " + nullSkipped
                + " null skipped | " + faculty
                + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        NumberedMember m1 = new NumberedMember(3);

        System.out.println(m1.memberNumber);
        System.out.println(NumberedMember.getMembersEnrolled());

        System.out.println(NumberedMember.isValidRenewalCode("R12A"));
        System.out.println(NumberedMember.isValidRenewalCode("R1A"));
        System.out.println(NumberedMember.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");

        System.out.println(m1.getBooksBorrowed());

        NumberedMember[] members = {
            new FacultyBranch(5, "Physics"),
            null,
            new NumberedMember(3)
        };

        System.out.println(
                AuditTest.processNightlyAudit(members)
        );
    }
}