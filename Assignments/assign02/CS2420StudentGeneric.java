package assign02;

import java.util.ArrayList;

/**
 * This Java class represents a UofU student who is taking CS2420
 *
 * @author Joshua Schell and Sebstain Barney
 * @version January 22, 2022
 */
public class CS2420StudentGeneric<Type> extends UofUStudent {

    private Type studentContact;
    private ArrayList<Double> assignments;
    private ArrayList<Double> labs;
    private ArrayList<Double> exams;
    private ArrayList<Double> quizzes;

    /**
     * Constructs a generic CS2420 student object
     *
     * @param firstName first name of student
     * @param lastName last name of student
     * @param uNID student's uNID
     * @param contactInfo how to contact student
     */
    public CS2420StudentGeneric(String firstName, String lastName, int uNID, Type contactInfo) {
        super(firstName, lastName, uNID);
        this.studentContact = contactInfo;
        assignments = new ArrayList<Double>();
        labs        = new ArrayList<Double>();
        exams       = new ArrayList<Double>();
        quizzes     = new ArrayList<Double>();
    }

    /**
     * Retrieves Student's contact info
     *
     * @return student's contact info
     */
    public Type getContactInfo() { return this.studentContact; }

    /**
     * Adds the score student receive into appropriate category
     *
     * @param score the score the student received
     * @param category which category score should be added to
     */
    public void addScore(double score, String category) {
        switch (category) {
            case "assignment" -> assignments.add(score);
            case "lab" -> labs.add(score);
            case "exam" -> exams.add(score);
            case "quiz" -> quizzes.add(score);
        }
    }

    /**
     * Computes the final score of a student
     *
     * @return the final score the will student receive at the end of the class
     */
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

    /**
     * Computes the student's final letter grade
     *
     * @return student's final letter grade
     */
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