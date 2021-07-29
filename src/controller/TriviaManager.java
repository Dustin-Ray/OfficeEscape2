package controller;

import model.trivia.Trivia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class TriviaManager {

    /**
     *
     */
    private ArrayList<Trivia> myTriviaList;

    /**
     *
     */
    public TriviaManager() {

        myTriviaList = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/res/database/questions-answers.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

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

        System.out.println("Testing if the questions have been loaded from database to ArrayList.");
        for (Trivia t: myTriviaList) {
            System.out.println(t);
        }
    }

    /**
     *
     * @return
     */
    public Trivia getTrivia() {
        // returns a random trivia object and removes it from the trivia pool
        Random rnd = new Random();
        System.out.println(myTriviaList.size());
        return myTriviaList.get(Math.abs(rnd.nextInt(myTriviaList.size())));
    }
}