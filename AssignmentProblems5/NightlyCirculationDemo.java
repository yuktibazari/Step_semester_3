class LoanRecord {
    private final String memberId;
    private final String[] bookIds;

    public LoanRecord(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanRecord withCorrectedBookId(int index, String newId) {
        String[] updated = bookIds.clone();
        updated[index] = newId;
        return new LoanRecord(memberId, updated);
    }
}

class ReferenceOnlyRecord extends LoanRecord {
    private final String roomNumber;

    public ReferenceOnlyRecord(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

class CirculationLedger {
    static String branchCode;

    static {
        branchCode = "PT-001";
    }
}

public class NightlyCirculationDemo {
    static String processNightlyCirculation(LoanRecord[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanRecord receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
            } else {
                processed++;

                if (receipt instanceof ReferenceOnlyRecord) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " + nullSkipped +
               " null skipped | " + referenceOnly +
               " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        LoanRecord r = new LoanRecord(
            "LIB-8841",
            new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanRecord corrected = r.withCorrectedBookId(1, "BK-102");

        System.out.println(java.util.Arrays.toString(r.getBookIds()));
        System.out.println(java.util.Arrays.toString(corrected.getBookIds()));

        LoanRecord[] receipts = {
            new ReferenceOnlyRecord(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"
            ),
            null,
            new LoanRecord(
                "LIB-002",
                new String[]{"BK-201"}
            )
        };

        System.out.println(processNightlyCirculation(receipts));
    }
}