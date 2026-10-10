
import java.util.*;

class StudentMarks {
    String name;
    int[] marks;

    StudentMarks(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    int total() {
        return marks[0] + marks[1] + marks[2];
    }
}

public class ClassMarksGridAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<StudentMarks> students = new ArrayList<>();
        int[] subjectTotals = new int[3];

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+", 2);
            String name = parts[0];
            String values = parts[1].replaceAll("[\\[\\]]", "");
            String[] nums = values.split(",");
            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(nums[i].trim());
                subjectTotals[i] += marks[i];
            }

            students.add(new StudentMarks(name, marks));
        }

        if (students.isEmpty()) {
            sc.close();
            return;
        }

        System.out.print("Totals ");
        for (int i = 0; i < students.size(); i++) {
            StudentMarks s = students.get(i);
            System.out.print(s.name + " " + s.total());
            if (i < students.size() - 1) System.out.print(", ");
        }
        System.out.println();

        System.out.printf(Locale.US, "averages %.2f, %.2f, %.2f%n",
            subjectTotals[0] / (double) students.size(),
            subjectTotals[1] / (double) students.size(),
            subjectTotals[2] / (double) students.size());

        StudentMarks topper = students.get(0);
        for (StudentMarks s : students) {
            if (s.total() > topper.total())
                topper = s;
        }

        System.out.println("topper " + topper.name + " (" + topper.total() + ")");
        sc.close();
    }
}
