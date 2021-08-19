/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.graph;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Implements unit tests for UnionFindDisjointSet.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class UnionFindDisjointSetTest {

    /** An efficient disjoint set data structure ot use in tests. */
    private final UnionFindDisjointSet<Integer> disjointSets;

    /** A Random object for generating pseudo-random numbers. */
    private final Random rand;

    /**
     * Constructs a UnionFindDisjointSetTest.
     */
    public UnionFindDisjointSetTest() {
        disjointSets = new UnionFindDisjointSet<>();
        rand = new Random();
    }


    /**
     * Checks that makeSet() throws and IllegalArgumentException when given an
     * element already in a set.
     */
    @Test
    void makeSet_withElementAlreadyInASet_throwsException() {
        String msg = "makeSet() should throw exception when given an element" +
                "in an existing disjoint set";
        disjointSets.makeSet(3);
        assertThrows(IllegalArgumentException.class,
                () -> disjointSets.makeSet(3), msg);
    }


    /**
     * Checks that findSet() after makeSet() with a single element returns the
     * correct ID (0).
     */
    @Test
    void findSet_afterMakeSet_returnsIDZero() {
        disjointSets.makeSet(1);
        assertEquals(0, disjointSets.findSet(1));
    }


    /**
     * Checks that findSet() after unioning disjoint sets returns the correct
     * set ID.
     */
    @Test
    void findSet_afterUnion_returnsCorrectIDs() {
        String msg = "findSet() should return same ID for " +
                "unioned disjoint sets";
        disjointSets.makeSet(1);
        disjointSets.makeSet(2);
        disjointSets.makeSet(3);
        disjointSets.makeSet(4);
        disjointSets.union(1, 2);
        disjointSets.union(1, 4);
        disjointSets.union(2, 3);
        assertEquals( disjointSets.findSet(1), disjointSets.findSet(2), msg);
        assertEquals(disjointSets.findSet(1), disjointSets.findSet(4), msg);
        assertEquals(disjointSets.findSet(1), disjointSets.findSet(3), msg);
    }


    /**
     * Checks that union() throws IllegalArgumentException when given an
     * element not in any existing set.
     */
    @Test
    void union_givenInvalidElement_throwsException() {
        String msg = "union() should throw exception when given an element" +
                " that's not in any existing set";
        disjointSets.makeSet(1);
        disjointSets.makeSet(2);
        disjointSets.union(1, 2);
        assertThrows(IllegalArgumentException.class,
                () -> disjointSets.union(4, 1), msg);
        assertThrows(IllegalArgumentException.class,
                () -> disjointSets.union(1, 3), msg);
    }


    /**
     * Checks that union() on two elements already in the same set returns
     * false.
     */
    @Test
    void union_givenElementsInSameSet_returnsFalse() {
        String msg = "union() should return false if given elements already" +
                " unioned";
        disjointSets.makeSet(3);
        disjointSets.makeSet(4);
        disjointSets.union(3, 4);
        assertFalse(disjointSets.union(3, 4), msg);
    }


    /**
     * Checks that findSet() throws IllegalArgumentException when given an
     * element not in any set.
     */
    @Test
    void findSet_givenElementNotInAnySet_throwsException() {
        String msg = "findSet() should throw exception when given an element" +
                " that's not in any disjoint set";
        assertThrows(IllegalArgumentException.class,
                () -> disjointSets.findSet(5), msg);
    }

    /**
     * Checks that findSet() returns different IDs for elements from different
     * disjoint sets.
     */
    @Test
    void findSet_afterMakeSet_returnsDifferentIDs() {
        String msg = "findSet() should return distinct IDs for elements of" +
                " different disjoint sets";
        disjointSets.makeSet(1);
        disjointSets.makeSet(2);
        assertNotEquals(disjointSets.findSet(1),
                disjointSets.findSet(2), msg);
    }

}