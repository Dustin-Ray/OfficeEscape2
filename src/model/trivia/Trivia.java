package model.trivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Class to represents Trivia objects.
 * Trivia objects are questions that are true/false, multiple choice
 * or short answer.
 *
 * @author Raz Consta
 */
public class Trivia {

    /**
     * ID of the question.
     */
    private final int myID;
    /**
     * String storing the question.
     */
    private final String myQuestion;
    /**
     * String storing the correct answer.
     */
    private final String myCorrectAnswer;
    /**
     * ArrayList of Strings storing the incorrect answer choices.
     */
    private final ArrayList<String> myIncorrectAnswers;
    /**
     * Integer storing the question type.
     */
    private final int myType;

    /**
     * Constant representing a short answer question type.
     */
    private final int SA = 3;

    /**
     * Constant representing a true false question type.
     */
    int TF = 1;
    /**
     * Constant representing a multiple choice question type.
     */
    int MC = 2;

    /**
     * Constructs a Trivia object.
     *
     * @param theID integer ID of the Trivia
     * @param theQuestion String storing the question
     * @param theCorrectAnswer String storing the correct answer
     * @param theIncorrectAnswers ArrayList storing the incorrect answers
     * @param theType integer representing the type of the question
     */
    public Trivia(int theID,
                  String theQuestion,
                  String theCorrectAnswer,
                  String theIncorrectAnswers,
                  int theType) {
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
     * Returns a boolean whether the passed string matches the correct answer.
     *
     * @param theAnswer String representing the user's answer.
     * @return true if the user's answer matches the correct answer, false otherwise
     */
    public boolean shortAnswerCorrect (String theAnswer) {
        return theAnswer.equalsIgnoreCase(myCorrectAnswer);
    }

    /**
     * Returns the ArrayList of incorrect answers for the Trivia question.
     * In the case of TF and SA type of questions, the ArrayList is empty.
     *
     * @return ArrayList containing Strings of incorrect answers.
     */
    public ArrayList<String> getIncorrectAnswers() {
        Collections.shuffle(myIncorrectAnswers);
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

        // Store the wrong answers.
        ArrayList<String> wrongAnswers = getIncorrectAnswers();


        // Add the wrong answers to the answerList.
        answerList.addAll(myIncorrectAnswers);

        // Shuffle the answerList that will be displayed to the user.
        Collections.shuffle(answerList);

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

    /**
     * Returns a String representation of the Trivia object.
     *
     * @return String that shows the questions and correct answer.
     */
    public String toString() {
        return "Q: " + myQuestion + " A: " + myCorrectAnswer;
    }
}