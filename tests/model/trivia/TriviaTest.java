/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.trivia;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Implements tests for Trivia.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class TriviaTest {

    private static final int SHORT_ID = 1;
    private static final String SHORT_QUESTION = "Hello, World?";
    private static final String SHORT_ANSWER = "hi";
    private static final String SHORT_INCORRECT_ANSWERS = "";
    private static final int SHORT_TYPE = 3;

    private static final int MULTIPLE_ID = 3;
    private static final String MULTIPLE_QUESTION = SHORT_QUESTION;
    private static final String MULTIPLE_ANSWER = SHORT_ANSWER;
    private static final String MULTIPLE_INCORRECT_ANSWERS = "Bye;Cya;Nah";
    private static final int MULTIPLE_TYPE = 2;

    private static final int TRUE_FALSE_ID = 4;
    private static final String TRUE_FALSE_QUESTION = SHORT_QUESTION;
    private static final String TRUE_FALSE_ANSWER = "True";
    private static final String TRUE_FALSE_INCORRECT_ANSWERS = "False";
    private static final int TRUE_FALSE_TYPE = 1;


    private final Trivia shortAnswerTrivia;
    private final Trivia multipleChoiceTrivia;
    private final Trivia trueFalseTrivia;

    public TriviaTest() {
        shortAnswerTrivia = new Trivia(
                SHORT_ID,
                SHORT_QUESTION,
                SHORT_ANSWER,
                SHORT_INCORRECT_ANSWERS,
                SHORT_TYPE
        );

        multipleChoiceTrivia = new Trivia(
                MULTIPLE_ID,
                MULTIPLE_QUESTION,
                MULTIPLE_ANSWER,
                MULTIPLE_INCORRECT_ANSWERS,
                MULTIPLE_TYPE
        );

        trueFalseTrivia = new Trivia(
                TRUE_FALSE_ID,
                TRUE_FALSE_QUESTION,
                TRUE_FALSE_ANSWER,
                TRUE_FALSE_INCORRECT_ANSWERS,
                TRUE_FALSE_TYPE
        );



    }


    /**
     * Checks that getQuestion() returns the correct question for a true/false
     * question.
     */
    @Test
    void getQuestion_onTrueFalse_returnsCorrectQuestion() {
        assertEquals(TRUE_FALSE_QUESTION, trueFalseTrivia.getQuestion(),
                "getQuestion() for true/false does not return" +
                        " correct question");
    }

    /**
     * Checks that getCorrectAnswer() returns the correct answer for a
     * true/false question.
     */
    @Test
    void getCorrectAnswer_onTrueFalse_returnsCorrectAnswer() {
        assertEquals(TRUE_FALSE_ANSWER, trueFalseTrivia.getCorrectAnswer(),
                "getCorrectAnswer() for true/false does not return " +
                        "correct answer");
    }


    /**
     * Checks that getQuestion() returns the correct question for a multiple
     * choice question.
     */
    @Test
    void getQuestion_onMultipleChoice_returnsCorrectQuestion() {
        assertEquals(MULTIPLE_QUESTION, multipleChoiceTrivia.getQuestion(),
                "getQuestion() for multiple choice does not" +
                        " return correct question");
    }


    /**
     * Checks that getCorrectAnswer() returns the correct answer for a multiple
     * choice question.
     */
    @Test
    void getCorrectAnswer_onMultipleChoice_returnsCorrectAnswer() {
        assertEquals(MULTIPLE_ANSWER, multipleChoiceTrivia.getCorrectAnswer(),
                "getCorrectAnswer() for multiple choice does not return " +
                        "correct answer");
    }


    /**
     * Checks that getQuestion() returns the correct question for a short answer
     * question.
     */
    @Test
    void getQuestion_onShortAnswer_returnsCorrectQuestion() {
        assertEquals(SHORT_QUESTION, shortAnswerTrivia.getQuestion(),
                "getQuestion() for short answer does not" +
                        " return correct question");
    }


    /**
     * Checks that getCorrectAnswer() returns the correct answer.
     */
    @Test
    void getCorrectAnswer_onShortAnswer_returnsCorrectAnswer() {
        assertEquals(SHORT_ANSWER, shortAnswerTrivia.getCorrectAnswer());
    }


    @Test
    void getCorrectAnswer_forShortAnswer_ignoresCase() {

    }


    /**
     * Checks that getType() returns the correct type of Trivia.
     */
    @Test
    void getType_returnsCorrectType() {
        assertEquals(SHORT_TYPE, shortAnswerTrivia.getType());
    }


    /**
     * Checks that getIncorrectAnswers() for a short answer Trivia returns
     * an empty List.
     */
    @Test
    void getIncorrectAnswers_onShortyAnswer_returnsEmptyList() {
        assertTrue(shortAnswerTrivia.getIncorrectAnswers().isEmpty(),
                "getIncorrectAnswers() for short answer should return" +
                        " an empty List");
    }


    /**
     * Checks that getIncorrectAnswers() for multiple choice returns a list of
     * incorrect answers.
     */
    @Test
    void getIncorrectAnswers_onMultipleChoice_returnsIncorrectAnswers() {
        String[] incorrectAnswers = MULTIPLE_INCORRECT_ANSWERS.split(";");
        assertEquals(Arrays.asList(incorrectAnswers),
                multipleChoiceTrivia.getIncorrectAnswers(),
                "getIncorrectAnswers() on multiple choice does not" +
                        " return incorrect answers");
    }

    /**
     * Checks that getIncorrectAnswers() for true/false returns the incorrect
     * answers.
     */
    @Test
    void getIncorrectAnswers_onTrueFalse_returnsIncorrectAnswer() {
        List<String> incorrectAnswer;
        incorrectAnswer = Arrays.asList(
                TRUE_FALSE_INCORRECT_ANSWERS.split(";")
        );
        assertEquals(incorrectAnswer, trueFalseTrivia.getIncorrectAnswers(),
                "getIncorrectAnswers() on true/false does not return" +
                        " incorrect answers");
    }


    /**
     * Checks that equals is reflexive.
     */
    @Test
    void equals_isReflexive() {
        assertEquals(shortAnswerTrivia, shortAnswerTrivia,
                "equals() is not reflexive");
    }


    /**
     * Checks that equals is symmetric.
     */
    @Test
    void equals_isSymmetric() {
        Trivia other = new Trivia(
                SHORT_ID,
                SHORT_QUESTION,
                SHORT_ANSWER,
                SHORT_INCORRECT_ANSWERS,
                SHORT_TYPE
        );
        String msg = "equals() is not symmetric";
        assertEquals(shortAnswerTrivia, other, msg);
        assertEquals(other, shortAnswerTrivia, msg);
    }


    /**
     * Checks that hashCode() for equal Trivia returns the same value.
     */
    @Test
    void hashCode_onEqualTrivia_isSame() {
        Trivia other = new Trivia(
                MULTIPLE_ID,
                MULTIPLE_QUESTION,
                MULTIPLE_ANSWER,
                MULTIPLE_INCORRECT_ANSWERS,
                MULTIPLE_TYPE
        );
        assertEquals(multipleChoiceTrivia.hashCode(), other.hashCode(),
                "equal objects should have same hashCode()");
    }

    /**
     * Checks that toString() returns the correct String.
     */
    @Test
    void toString_onTrivia_returnsCorrectString() {
        assertEquals("Q: " + SHORT_QUESTION + " A: " + SHORT_ANSWER,
                shortAnswerTrivia.toString(),
                "toString() does not return correct String");
    }

}