package model.trivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Creates trivia objects.
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
     * ArrrayList of Strings storing the incorrect answer choices.
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
     *
     * @param theID
     * @param theQuestion
     * @param theCorrectAnswer
     * @param theIncorrectAnswers
     * @param theType
     */
    public Trivia(int theID,
                  String theQuestion,
                  String theCorrectAnswer,
                  String theIncorrectAnswers,
                  int theType) {
        myID = theID;
        myQuestion = theQuestion;
        myCorrectAnswer = theCorrectAnswer;
        ArrayList<String> incorrect = new ArrayList<String>();

        if (theType == TF) {
            if (theCorrectAnswer.equals("T")) {
                incorrect.add("F");
            }
            else {
                incorrect.add("T");
            }
        }
        else if (theType == MC) {
            String[] split = theIncorrectAnswers.split("\\n");

            incorrect.addAll(Arrays.asList(split));
        }

        myIncorrectAnswers = incorrect;
        myType = theType;
    }

    /**
     *
     * @return
     */
    public String getQuestion() {
        return myQuestion;
    }

    /**
     *
     * @return
     */
    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

    /**
     *
     * @param theAnswer
     * @return
     */
    public boolean shortAnswerCorrect (String theAnswer) {
        return theAnswer.equalsIgnoreCase(myCorrectAnswer);
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getIncorrectAnswers() {
        Collections.shuffle(myIncorrectAnswers);
        return myIncorrectAnswers;
    }

    /**
     *
     * @return
     */
    public int getType() {
        return myType;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "Q: " + myQuestion + " A: " + myCorrectAnswer;
    }
}