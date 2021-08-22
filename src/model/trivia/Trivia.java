/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.trivia;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Class to represents Trivia objects.
 * Trivia objects are questions that are true/false, multiple choice
 * or short answer.
 *
 * @author Raz Consta
 * @version Summer 2021
 */
public class Trivia implements Serializable {

    @Serial
    private static final long serialVersionUID = 8471019687092293378L;

    /** ID of the question. */
    private final int myID;

    /** String storing the question. */
    private final String myQuestion;

    /** String storing the correct answer. */
    private final String myCorrectAnswer;

    /** ArrayList of Strings storing the incorrect answer choices. */
    private final ArrayList<String> myIncorrectAnswers;

    /** Integer storing the question type. */
    private final int myType;

    /**
     * Constructs a Trivia object.
     *
     * @param theID integer ID of the Trivia
     * @param theQuestion String storing the question
     * @param theCorrectAnswer String storing the correct answer
     * @param theIncorrectAnswers ArrayList storing the incorrect answers
     * @param theType integer representing the type of the question
     */
    public Trivia(final int theID,
                  final String theQuestion,
                  final String theCorrectAnswer,
                  final String theIncorrectAnswers,
                  final int theType) {
        myID = theID;
        myQuestion = theQuestion;
        myCorrectAnswer = theCorrectAnswer;
        ArrayList<String> incorrect = new ArrayList<>();

        if (theType == 1) {
            if (theCorrectAnswer.equals("True")) {
                incorrect.add("False");
            }
            else {
                incorrect.add("True");
            }
        }
        else if (theType == 2) {
            int i = 0, j;
            for (j = 0; j < theIncorrectAnswers.length(); j++) {
                if (theIncorrectAnswers.charAt(j) == ';') {
                    incorrect.add(theIncorrectAnswers.substring(i, j));
                    i = j + 1;
                    j = j + 1;
                }
            }
            incorrect.add(theIncorrectAnswers.substring(i, j));
        }

        myIncorrectAnswers = incorrect;
        myType = theType;
    }

    /**
     * Returns the question of the Trivia object.
     *
     * @return String representing the question.
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     * Returns the correct answer of the Trivia object.
     *
     * @return String representing the correct answer.
     */
    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

    /**
     * Returns the ArrayList of incorrect answers for the Trivia question.
     * In the case of TF and SA type of questions, the ArrayList is empty.
     *
     * @return ArrayList containing Strings of incorrect answers.
     */
    public ArrayList<String> getIncorrectAnswers() {
        return myIncorrectAnswers;
    }

    /**
     * Returns an ArrayList containing the correct and incorrect answers,
     * whose order is shuffled.
     *
     * @return shuffled ArrayList of correct and incorrect answers
     */
    public ArrayList<String> getAnswers() {
        ArrayList<String> answerList = new ArrayList<>();
        // Add the correct answer to the answer list.
        answerList.add(getCorrectAnswer());
        /*
         Store the wrong answers.
         In case of TF, there is 1.
         In case of SA, there is 1.
         */
        // ArrayList<String> wrongAnswers = getIncorrectAnswers();
        // Add the wrong answers to the answerList.
        answerList.addAll(myIncorrectAnswers);
        return answerList;
    }

    /**
     * Returns an integer that indicates the type of the Trivia question.
     *
     * @return integer representing the type of the question.
     */
    public int getType() {
        return myType;
    }

    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            Trivia o = (Trivia) other;
            result = myQuestion.equals(o.myQuestion)
                    && myCorrectAnswer.equals(o.myCorrectAnswer)
                    && myID == o.myID
                    && myType == o.myType;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myQuestion, myCorrectAnswer, myID, myType);
    }

    /**
     * Returns a String representation of the Trivia object.
     *
     * @return String that shows the question and correct answer.
     */
    public String toString() {
        return "Q: " + myQuestion + " A: " + myCorrectAnswer;
    }
}