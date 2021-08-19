/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.trivia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 * Uses an ArrayList to store Trivia objects that are instantiated using
 * data from a SQLite database.
 *
 * @author Raz Consta
 */
public class TriviaManager {

    /**
     * The ArrayList that stores all of the Trivia objects.
     */
    private final ArrayList<Trivia> myTriviaList;

    /**
     * Constructs a TriviaManager that stores all of the Trivia objects from
     * a database.
     */
    public TriviaManager() {

        myTriviaList = new ArrayList<>();
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/res/database/questions-answers.db");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rst = stmt.executeQuery( "SELECT * FROM trivia;" );
            while ( rst.next() ) {
                int id = rst.getInt("id");
                String question = rst.getString("question");
                String correct = rst.getString("answer");
                String incorrect = rst.getString("wrong");
                int type = rst.getInt("type");
                myTriviaList.add(new Trivia(id, question, correct, incorrect, type));
            }
            rst.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        // Testing code below:

//        System.out.println("Testing if the questions have been loaded from database to ArrayList.");
//        for (Trivia t: myTriviaList) {
//            System.out.println(t);
//        }

//        Trivia mc = myTriviaList.get(1);
//        System.out.println(mc.getAnswers());

        // Testing hint system

//        for (Trivia t: myTriviaList) {
//            System.out.println(t.getAnswers());
//            System.out.println(t.getHints() + "\n");
//        }
    }

    /**
     * Returns a random trivia object and removes it from the trivia pool.
     * @return a Trivia object from the ArrayList of Trivia objects.
     */
    public Trivia getTrivia() {
        Random rnd = new Random();
        return myTriviaList.remove(Math.abs(rnd.nextInt(myTriviaList.size())));
    }


}