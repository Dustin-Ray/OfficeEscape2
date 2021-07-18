package model;

// Trivia manager will make Trivia objects using the database and fill them
// into an ArrayList. When we select a Trivia to ask the user, we will
// swap that Trivia with the last Trivia and remove it from the end since
// it is constant access.

/**
 * Creates trivia objects.
 * @author Raz Consta
 */
public class Trivia {

    /**
     * ID of the question.
     * Range: 1 to # of questions.
     */
    private final int myID;
    /**
     * String storing the question.
     */
    private final String myQuestion;
    /**
     * String storing the answer.
     */
    private final String myAnswer;
    /**
     * Integer storing the question type.
     * 1 - True False (T, F)
     * 2 - Multiple Choice (A, B, C, D)
     * 3 - Short answer (text box)
     */
    private final int myType;


    public Trivia(int theID, String theQuestion, String theAnswer, int theType) {
        myID = theID;
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myType = theType;
    }

    public String getQuestion() {
        return myQuestion;
    }

    public String getAnswer() {
        return myAnswer; }

    public int getType() {
        return myType;
    }

}
