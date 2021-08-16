package model.trivia;

import model.trivia.Trivia;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TriviaTest {


    private static final int SHORT_ID = 1;
    private static final String SHORT_QUESTION = "Hello, World?";
    private static final String SHORT_ANSWER = "Hi";
    private static final String SHORT_INCORRECT_ANSWERS = "";
    private static final int SHORT_TYPE = 3;


    private static final int MULTIPLE_ID = 3;
    private static final String MULTIPLE_QUESTION = SHORT_QUESTION;
    private static final String MULTIPLE_ANSWER = SHORT_ANSWER;
    private static final String MULTIPLE_INCORRECT_ANSWERS = "Bye;Cya;Nah";
    private static final int MULTIPLE_TYPE = 2;

    private static final int TRUE_FALSE_ID = 4;
    private static final String TRUE_FALSE_QUESTION = SHORT_QUESTION;
    private static final String TRUE_FALSE_ANSWER = "true";
    private static final String TRUE_FALSE_INCORRECT_ANSWERS = "false";
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

    @Test
    void getQuestion_returnsCorrectQuestion() {
        assertEquals(SHORT_QUESTION, shortAnswerTrivia.getQuestion());
    }

    /**
     * Checks that getCorrectAnswer() returns the correct answer.
     */
    @Test
    void getCorrectAnswer_returnsCorrectAnswer() {
        assertEquals(SHORT_ANSWER, shortAnswerTrivia.getCorrectAnswer());
    }


    /**
     * Checks that getCorrectAnswer ignores capitalization.
     */
    @Test
    void getCorrectAnswer_ignoresCase() {

    }


    /**
     * Checks that getIncorrectAnswers() for a short answer Trivia returns
     * an empty List.
     */
    @Test
    void getIncorrectAnswers_returnsIncorrectAnswers() {
        assertTrue(shortAnswerTrivia.getIncorrectAnswers().isEmpty());
    }

    /**
     * Checks that getType() returns the correct type of Trivia.
     */
    @Test
    void getType_returnsCorrectType() {
        assertEquals(SHORT_TYPE, shortAnswerTrivia.getType());
    }


    /**
     *
     */
    @Test
    void getCorrectAnswers_returnsCorrectAnswers() {

    }


    /**
     * Checks that getIncorrectAnswers() returns a list of incorrect answers.
     */
    @Test
    void getIncorrectAnswers_onMultipleChoice_returnsIncorrectAnswers() {
        String[] incorrectAnswers = MULTIPLE_INCORRECT_ANSWERS.split(";");
        assertEquals(Arrays.asList(incorrectAnswers),
                multipleChoiceTrivia.getIncorrectAnswers());
    }

    /**
     *
     */
    @Test
    void getIncorrectAnswers_onTrueFalse_returnsIncorrectAnswer() {
        List<String> incorrectAnswer;
        incorrectAnswer = Arrays.asList(TRUE_FALSE_INCORRECT_ANSWERS.split(";"));
        System.out.println(trueFalseTrivia.getAnswers());
        assertEquals(incorrectAnswer, trueFalseTrivia.getIncorrectAnswers());
    }

}