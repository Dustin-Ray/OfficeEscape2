/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.trivia;

import java.io.Serializable;
import java.util.*;

/**
 * Class to represents Trivia objects.
 * Trivia objects are questions that are true/false, multiple choice
 * or short answer.
 *
 * @author Raz Consta
 */
public class Trivia implements Serializable {

    /** ID of the question. */
    private final int myID;

    /** String storing the question. */
    private final String myQuestion;

    /** String storing the correct answer. */
    private final String myCorrectAnswer;

    /** ArrayList of Strings storing the incorrect answer choices. */
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
         In case of MC, there are 3.
         In case of SA, there is 1.
         */
        ArrayList<String> wrongAnswers = getIncorrectAnswers();
        // Add the wrong answers to the answerList.
        answerList.addAll(myIncorrectAnswers);
        return answerList;
    }

    /**
     * Returns a shuffled ArrayList with answers changed as part of using the
     * hint system.
     *
     * @return shuffled ArrayList of correct and incorrect answers
     */
    public ArrayList<String> getHints() {
        ArrayList<String> hintsList = new ArrayList<>();

        if (getType() == TF) {
            hintsList.add("Maybe " + myCorrectAnswer);
            hintsList.add("Maybe " + myIncorrectAnswers.get(0));
        } else if (getType() == MC) {
            hintsList.add(myCorrectAnswer);
            hintsList.add(myIncorrectAnswers.get(new Random().nextInt(3)));
        } else if (getType() == SA) {
            StringBuilder saHint = new StringBuilder();
            for (int i = 0; i < myCorrectAnswer.length(); i++) {
                if (Math.random() < 0.5) {
                    saHint.append(myCorrectAnswer.charAt(i));
                } else {
                    if (myCorrectAnswer.charAt(i) != ' ') {
                        saHint.append('_');
                    } else {
                        saHint.append(' ');
                    }
                }
            }
            hintsList.add(saHint.toString());
        }
        Collections.shuffle(hintsList);
        return hintsList;
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
     * @return String that shows the questions and correct answer.
     */
    public String toString() {
        return "Q: " + myQuestion + " A: " + myCorrectAnswer;
    }
}