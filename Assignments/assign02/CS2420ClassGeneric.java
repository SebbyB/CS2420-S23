package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class CS2420ClassGeneric<Type> {

    private ArrayList<CS2420StudentGeneric<Type>> studentList;

    /**
     * Creates an empty CS 2420 class.
     */
    public CS2420ClassGeneric() {
        this.studentList = new ArrayList<CS2420StudentGeneric<Type>>();
    }

    /**
     * Adds the given student to the collection of students in CS 2420, avoiding duplicates.
     *
     * @param student - student to be added to this CS 2420 class
     * @return true if the student was added,
     *         false if the student was not added because they already exist in the collection
     */
    public boolean addStudent(CS2420StudentGeneric<Type> student) {
        if(studentList.contains(student))
            return false;
        else
            studentList.add(student);

        return true;
    }

    /**
     * Retrieves the CS 2420 student with the given uNID.
     *
     * @param uNID - uNID of student to be retrieved
     * @return the CS 2420 student with the given uNID, or null if no such student exists in the collection
     */
    public CS2420StudentGeneric<Type> lookup(int uNID) {
        if(this.studentList.isEmpty())
            return null;
        for(CS2420StudentGeneric<Type> student : studentList) {
            if(student.getUNID() == uNID)
                return student;
        }
        return null;
    }

    /**
     * Retrieves the CS 2420 student(s) with the given contact information.
     *
     * @param contactInfo - contact information of student(s) to be retrieved
     * @return a list of the CS 2420 student(s) with the given contact information (in any order),
     * 	     or an empty list if no such students exist in the collection
     */
    public ArrayList<CS2420StudentGeneric<Type>> lookup(Type contactInfo) {
        var studentContactList = new ArrayList<CS2420StudentGeneric<Type>>();
        if(this.studentList.isEmpty())
            return studentContactList;
        for(CS2420StudentGeneric<Type> student : studentList) {
            if(student.getContactInfo().equals(contactInfo))
                studentContactList.add(student);
        }
        return studentContactList;
    }

    /**
     * Adds an assignment, exam, lab, or quiz score for the CS 2420 student with the given uNID.
     *
     * NOTE: If the category string is not one of "assignment", "exam", "lab", or "quiz", or
     *       if no student with the uNID exists in the collection, then this method has no effect.
     *
     * @param uNID - uNID of student whose score is to be added
     * @param score - the score to be added
     * @param category - the category in which to add the score
     */
    public void addScore(int uNID, double score, String category) {
        var student = lookup(uNID);
        if(this.studentList.isEmpty())
            return;
        student.addScore(score, category);
    }

    /**
     * Computes the average score of all CS 2420 students in the collection.
     *
     * @return the average score, or 0 if there are no students in the collection
     */
    public double computeClassAverage() {
        double totalScore = 0;
        if(studentList.isEmpty())
            return totalScore;

        for(CS2420StudentGeneric<Type> student : studentList) {
            totalScore += student.computeFinalScore();
        }
        return totalScore/studentList.size();
    }

    /**
     * Creates a list of contact information for all CS 2420 students in the collection.
     *
     * @return the duplicate-free list of contact information, in any order
     */
    public ArrayList<Type> getContactList() {
        var contactList = new ArrayList<Type>();

        for(CS2420StudentGeneric<Type> student : studentList) {
            if(contactList.contains(student.getContactInfo()))
                continue;
            else
                contactList.add(student.getContactInfo());
        }
        return contactList;
    }

    /**
     * Returns the list of CS 2420 students in this class, sorted by uNID in ascending order.
     */
    public ArrayList<CS2420StudentGeneric<Type>> getOrderedByUNID() {
        ArrayList<CS2420StudentGeneric<Type>> studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
        for(CS2420StudentGeneric<Type> student : studentList)
            studentListCopy.add(student);

        sort(studentListCopy, new OrderByUNID());

        return studentListCopy;
    }

    /**
     * Returns the list of CS 2420 students in this class, sorted by last name in lexicographical order.
     * Breaks ties in last names using first names (lexicographical order).
     * Breaks ties in first names using uNIDs (ascending order).
     */
    public ArrayList<CS2420StudentGeneric<Type>> getOrderedByName() {
        var studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
        for(CS2420StudentGeneric<Type> student : studentList)
            studentListCopy.add(student);

        sort(studentListCopy, new OrderByName());

        return studentListCopy;
    }

    /**
     * Returns the list of CS 2420 students in this class with a final score of at least cutoffScore,
     * sorted by final score in descending order.
     * Breaks ties in final scores using uNIDs (ascending order).
     *
     * @param cutoffScore - value that a student's final score must be at or above to be included
     *                      in the returned list
     */
    public ArrayList<CS2420StudentGeneric<Type>> getOrderedByScore(double cutoffScore) {
        var studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
        for(CS2420StudentGeneric<Type> student : studentList) {
            if(student.computeFinalScore() >= cutoffScore)
                studentListCopy.add(student);
        }

        sort(studentListCopy, new Comparator<CS2420StudentGeneric<Type>>() {
            @Override
            public int compare(CS2420StudentGeneric<Type> o1, CS2420StudentGeneric<Type> o2) {
                if (o1.computeFinalScore() != o2.computeFinalScore()) {
                    if(o1.computeFinalScore() < o2.computeFinalScore())
                        return 1;
                    else
                        return -1;
                }
                else
                    return o1.getUNID() - o2.getUNID();
            }
        });

        return studentListCopy;
    }

    /**
     * Performs a SELECTION SORT on the input ArrayList.
     *
     * 1. Finds the smallest item in the list.
     * 2. Swaps the smallest item with the first item in the list.
     * 3. Reconsiders the list be the remaining unsorted portion (second item to Nth item) and
     *    repeats steps 1, 2, and 3.
     */
    private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
        for(int i = 0; i < list.size() - 1; i++) {
            int j, minIndex;
            for(j = i + 1, minIndex = i; j < list.size(); j++)
                if(c.compare(list.get(j), list.get(minIndex)) < 0)
                    minIndex = j;
            ListType temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    /**
     * Comparator that defines an ordering among CS 2420 students using their uNIDs.
     * uNIDs are guaranteed to be unique, making a tie-breaker unnecessary.
     */
    protected class OrderByUNID implements Comparator<CS2420StudentGeneric<Type>> {

        /**
         * Returns a negative value if lhs (left-hand side) is smaller than rhs (right-hand side).
         * Returns a positive value if lhs is larger than rhs.
         * Returns 0 if lhs and rhs are equal.
         */
        public int compare(CS2420StudentGeneric<Type> lhs, CS2420StudentGeneric<Type> rhs) {
            return lhs.getUNID() - rhs.getUNID();
        }
    }

    /**
     * Comparator that defines an ordering among CS 2420 students using their names.
     * Compares by last name, then first name (if last names are the same), then uNID
     * (if both names are the same).  uNIDs are guaranteed to be unique.
     */
    protected class OrderByName implements Comparator<CS2420StudentGeneric<Type>> {

        /**
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return
         */
        public int compare(CS2420StudentGeneric<Type> o1, CS2420StudentGeneric<Type> o2) {
            if(!o1.getLastName().equals(o2.getLastName()))
                return o1.getLastName().compareTo(o2.getLastName());
            else if(!o1.getFirstName().equals(o2.getFirstName()))
                return o1.getFirstName().compareTo(o2.getFirstName());

            return o1.getUNID() - o2.getUNID();
        }
    }

    /**
     * Helper method for parsing the information about a student from file.
     *
     * @param line - string to be parsed
     * @param lineNumber - line number in file, used for error reporting (see above)
     * @return the CS2420Student constructed from the information
     * @throws ParseException if file containing line is not properly formatted (see above)
     */
    private CS2420StudentGeneric<Type> parseStudent(String line, int lineNumber) throws ParseException {
        Scanner lineIn = new Scanner(line);
        lineIn.useDelimiter(" ");

        if(!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("First name", lineNumber);
        }
        String firstName = lineIn.next();

        if(!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("Last name", lineNumber);
        }
        String lastName = lineIn.next();

        if(!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("uNID", lineNumber);
        }
        String uNIDString = lineIn.next();
        int uNID = Integer.parseInt(uNIDString.substring(2, 9));

        if(!lineIn.hasNext()) {
            lineIn.close();
            throw new ParseException("Email address", lineNumber);
        }
        String[] emailAddress = lineIn.next().split("@");

        lineIn.close();

        return new CS2420StudentGeneric<Type>(firstName, lastName, uNID, ((Type)new EmailAddress(emailAddress[0], emailAddress[1])));
    }

    /**
     * Helper method for parsing the scores for a student from file.
     *
     * @param line - string to be parsed
     * @param category - "assignment", "exam", "lab", or "quiz"
     * @param student - the student for which to add the scores
     */
    private void parseScores(String line, String category, CS2420StudentGeneric<Type> student) {
        Scanner lineIn = new Scanner(line);
        lineIn.useDelimiter(" ");
        while(lineIn.hasNextDouble())
            student.addScore(lineIn.nextDouble(), category);
        lineIn.close();
    }
}
