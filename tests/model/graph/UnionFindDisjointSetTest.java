package model.graph;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Implements unit tests for UnionFindDisjointSet.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class UnionFindDisjointSetTest {

    private final UnionFindDisjointSet<Integer> disjointSet;

    private final Random rand;


    public UnionFindDisjointSetTest() {
        disjointSet = new UnionFindDisjointSet<>();
        rand = new Random();
    }


    @Test
    void makeSet_withElementAlreadyInASet_throwsException() {
        disjointSet.makeSet(3);
        assertThrows(IllegalArgumentException.class, () -> disjointSet.makeSet(3));
    }


    @Test
    void findSet_afterMakeSet_returnsIDZero() {
        disjointSet.makeSet(1);
        assertEquals(0, disjointSet.findSet(1));
    }


    @Test
    void findSet_afterManyMakeSet_returnsCorrectIDs() {
        disjointSet.makeSet(1);
        disjointSet.makeSet(2);
        disjointSet.makeSet(3);
        disjointSet.makeSet(4);
        disjointSet.union(1, 2);
        disjointSet.union(1, 4);
    }


    @Test
    void union_givenInvalidElement_throwsException() {
        disjointSet.makeSet(1);
        disjointSet.makeSet(2);
        disjointSet.union(1, 2);
        assertThrows(IllegalArgumentException.class, () -> disjointSet.union(1, 3));
    }


    @Test
    void findSet_givenElementNotInAnySet_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> disjointSet.findSet(5));
    }


    @Test
    void findSet_afterManyUnionSet_returns() {
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int elementA = rand.nextInt();
            int elementB = rand.nextInt();
            elements.add(elementA);
            elements.add(elementB);
            disjointSet.makeSet(elementA);
        }
    }


    @Test
    void findSet_afterMakeSet_returnsDifferentIDs() {
        disjointSet.makeSet(1);
        disjointSet.makeSet(2);
        assertNotEquals(disjointSet.findSet(1), disjointSet.findSet(2));
    }


    @Test
    void findSet_afterUnion_returnsCorrectID() {
        disjointSet.makeSet(1);
        disjointSet.makeSet(2);
        disjointSet.union(1, 2);
        assertEquals(disjointSet.findSet(1), disjointSet.findSet(2));
    }
}