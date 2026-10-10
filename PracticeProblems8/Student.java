
import java.util.*;

class Student {
    String name;
    Set<String> submittedExams = new HashSet<>();

    Student(String name) {
        this.name = name;
    }
}

abstract class Question {
    String id;
    String text;
    int points;

    Question(String id, String text, int points) {
        this.id = id;
        this.text = text;
        this.points = points;
    }

    abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    String correctOption;

    MultipleChoiceQuestion(String id, String text,
                           int points, String correctOption) {
        super(id, text, points);
        this.correctOption = correctOption;
    }

    boolean evaluate(String answer) {
        return correctOption.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    String correctAnswer;

    TrueFalseQuestion(String id, String text,
                      int points, String correctAnswer) {
        super(id, text, points);
        this.correctAnswer = correctAnswer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class ShortAnswerQuestion extends Question {
    String correctAnswer;

    ShortAnswerQuestion(String id, String text,
                        int points, String correctAnswer) {
        super(id, text, points);
        this.correctAnswer = correctAnswer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class Examination {
    String id;
    List<Question> questions = new ArrayList<>();

    Examination(String id) {
        this.id = id;
    }

    void addQuestion(Question q) {
        questions.add(q);
    }
}

class Attempt {
    Student student;
    Examination exam;
    Map<String, String> answers = new LinkedHashMap<>();
    boolean submitted = false;

    Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
    }

    void recordAnswer(String questionId, String answer) {
        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        for (Question q : exam.questions) {
            if (q.id.equals(questionId)) {
                answers.put(questionId, answer);
                System.out.println("Answer recorded for " + questionId);
                return;
            }
        }

        System.out.println("Question not found");
    }

    void submit() {
        if (submitted) {
            System.out.println("Examination already submitted");
            return;
        }

        if (!student.submittedExams.add(exam.id)) {
            System.out.println("Student has already submitted this examination");
            return;
        }

        submitted = true;
        int score = 0;
        int total = 0;

        System.out.println(exam.id + " submitted by " + student.name);

        for (Question q : exam.questions) {
            total += q.points;
            String answer = answers.get(q.id);

            if (answer != null && q.evaluate(answer)) {
                score += q.points;
                System.out.println(q.id + ": Correct (" +
                        q.points + " points)");
            } else {
                System.out.println(q.id + ": Incorrect (0 points)");
            }
        }

        System.out.println("Total score: " + score + "/" + total);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student("Student 1");
        Examination e = new Examination("Exam A");

        e.addQuestion(new MultipleChoiceQuestion(
                "Question 1", "Choose the correct option",
                5, "C"));

        e.addQuestion(new TrueFalseQuestion(
                "Question 2", "Java is not object-oriented",
                5, "False"));

        Attempt a = new Attempt(s, e);

        System.out.println(e.id + " started by " + s.name);
        a.recordAnswer("Question 1", "C");
        a.recordAnswer("Question 2", "True");
        a.submit();
        a.recordAnswer("Question 1", "B");
    }
}
