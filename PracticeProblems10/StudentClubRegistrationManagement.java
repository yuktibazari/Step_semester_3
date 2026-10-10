
import java.util.*;

class Student {
    String rollNumber;
    String name;

    Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student s = (Student) obj;
        return rollNumber.equals(s.rollNumber);
    }

    public int hashCode() {
        return rollNumber.hashCode();
    }
}

public class StudentClubRegistrationManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Student> members = new HashSet<>();
        List<String> addResults = new ArrayList<>();
        List<String> containsResults = new ArrayList<>();

        while (sc.hasNext()) {
            String op = sc.next();
            String roll = sc.next();
            String name = sc.next();
            Student s = new Student(roll, name);

            if (op.equals("ADD")) {
                if (members.add(s))
                    addResults.add("Added");
                else
                    addResults.add("duplicate rejected");
            } else if (op.equals("CONTAINS")) {
                containsResults.add("contains: " + members.contains(s));
            }
        }

        for (String result : addResults)
            System.out.println(result);

        System.out.println("member count " + members.size());

        for (String result : containsResults)
            System.out.println(result);

        sc.close();
    }
}