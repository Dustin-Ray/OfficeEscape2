package model.trivia;

import java.util.ArrayList;
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
     * Constant representing a true false question type.
     */
    private final int TF = 1;
    /**
     * Constant representing a multiple choice question type.
     */
    private final int MC = 2;
    /**
     * Constant representing a short answer question type.
     */
    private final int SA = 3;

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
            String split[] = theIncorrectAnswers.split("\\n");

            for(String line: split) {
                incorrect.add(line);
            }
        }

        myIncorrectAnswers = incorrect;
        myType = theType;
    }

    public String getQuestion() {
        return myQuestion;
    }

    public String getCorrectAnswer() {
        return myCorrectAnswer;
    }

    public boolean shortAnswerCorrect (String theAnswer) {
        return theAnswer.equalsIgnoreCase(myCorrectAnswer);
    }

    public ArrayList<String> getIncorrectAnswers() {
        /*
         * Shuffles the incorrect answers list so that
         * they are not in the same order every time.
         */
        Collections.shuffle(myIncorrectAnswers);
        return myIncorrectAnswers;
    }

    public int getType() {
        return myType;
    }

    public String toString() {
        return "Q: " + myQuestion + " A: " + myCorrectAnswer;
    }
}