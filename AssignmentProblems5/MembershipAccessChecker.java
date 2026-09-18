class MembershipCard {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

public class MembershipAccessChecker {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        int privateAllowed = 0, privateDenied = 0;
        int defaultAllowed = 0, defaultDenied = 0;
        int protectedAllowed = 0, protectedDenied = 0;
        int publicAllowed = 0, publicDenied = 0;

        for (String[] attempt : attempts) {
            String result = classifyAccess(attempt[0], attempt[1]);

            if (attempt[0].equals("private")) {
                if (result.equals("ALLOWED")) privateAllowed++;
                else privateDenied++;
            } else if (attempt[0].equals("default")) {
                if (result.equals("ALLOWED")) defaultAllowed++;
                else defaultDenied++;
            } else if (attempt[0].equals("protected")) {
                if (result.equals("ALLOWED")) protectedAllowed++;
                else protectedDenied++;
            } else if (attempt[0].equals("public")) {
                if (result.equals("ALLOWED")) publicAllowed++;
                else publicDenied++;
            }
        }

        return "private: " + privateAllowed + " allowed / " + privateDenied +
               " denied | default: " + defaultAllowed + " allowed / " + defaultDenied +
               " denied | protected: " + protectedAllowed + " allowed / " + protectedDenied +
               " denied | public: " + publicAllowed + " allowed / " + publicDenied + " denied";
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));
        System.out.println(summarizeByModifier(attempts));
    }
}