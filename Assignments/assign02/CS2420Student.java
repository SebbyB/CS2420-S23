package assign02;

import java.util.ArrayList;

public class CS2420Student extends UofUStudent{

    private EmailAddress studentEmail;
    private ArrayList<Double> assignments;
    private ArrayList<Double> labs;
    private ArrayList<Double> exams;
    private ArrayList<Double> quizzes;

    public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
        super(firstName, lastName, uNID);
        this.studentEmail = contactInfo;
        assignments = new ArrayList<Double>();
        labs        = new ArrayList<Double>();
        exams       = new ArrayList<Double>();
        quizzes     = new ArrayList<Double>();
    }

    public EmailAddress getContactInfo() { return this.studentEmail; }

    public void addScore(double score, String category) {
        switch (category) {
            case "assignment" -> assignments.add(score);
            case "lab" -> labs.add(score);
            case "exam" -> exams.add(score);
            case "quiz" -> quizzes.add(score);
        }
    }

    public double computeFinalScore() {
        double assignmentsTotal = 0, examsTotal = 0, labsTotal = 0, quizzesTotal = 0;

        if(assignments.isEmpty() || labs.isEmpty() || exams.isEmpty() || quizzes.isEmpty())
            return 0.0;

        for (Double assignment : assignments) {
            assignmentsTotal += assignment;
        }
        for (Double lab : labs) {
            labsTotal += lab;
        }
        for (Double exam : exams) {
            examsTotal += exam;
        }
        for (Double quiz : quizzes) {
            quizzesTotal += quiz;
        }

        if(examsTotal/exams.size() < 65)
            return examsTotal/exams.size();

        return ((assignmentsTotal/assignments.size() * .4) + (examsTotal/exams.size() * .4) +
                (labsTotal/labs.size()) * .1 + (quizzesTotal/quizzes.size()) * .1);
    }

    public String computeFinalGrade() {
        if(assignments.isEmpty() || labs.isEmpty() || exams.isEmpty() || quizzes.isEmpty())
            return "N/A";

        double weightedGrade = this.computeFinalScore();
        String letterGrade;

        if(weightedGrade      >= 93)
            letterGrade = "A";
        else if(weightedGrade >= 90)
            letterGrade = "A-";
        else if(weightedGrade >= 87)
            letterGrade = "B+";
        else if(weightedGrade >= 83)
            letterGrade = "B";
        else if(weightedGrade >= 80)
            letterGrade = "B-";
        else if(weightedGrade >= 77)
            letterGrade = "C+";
        else if(weightedGrade >= 73)
            letterGrade = "C";
        else if(weightedGrade >= 70)
            letterGrade = "C-";
        else if(weightedGrade >= 67)
            letterGrade = "D+";
        else if(weightedGrade >= 63)
            letterGrade = "D";
        else if(weightedGrade >= 60)
            letterGrade = "D-";
        else
            letterGrade = "E";

        return letterGrade;
    }
}
