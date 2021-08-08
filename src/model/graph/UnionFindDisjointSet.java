/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements a disjoint-set data structure with union by size and path compression.
 * Maintains a collection of disjoint sets.
 *
 * @author Reuben Keller
 */
public class UnionFindDisjointSet<T> {

    /**
     * A List that stores parent relationships. Each node corresponds to an index i.
     * If the value at index i is -1, then (1) i corresponds to a root node (i.e., the
     * representative of the tree) and (2) i is the representative ID for the tree.
     */
    private final List<Integer> pointers;

    /**
     * Maps the type T to Integer indices in pointers.
     */
    private final Map<T, Integer> indices;


    /**
     * Constructs a disjoint set data structure.
     */
    public UnionFindDisjointSet() {
        pointers = new ArrayList<>();
        indices = new HashMap<>();
    }

    /**
     * Creates a new disjoint set containing the given element.
     *
     * @param element The element to create a disjoint set with.
     * @throws IllegalArgumentException if element is already in an existing disjoint set.
     */
    public void makeSet(T element) {
        if (indices.containsKey(element)) {
            throw new IllegalArgumentException();
        }
        indices.put(element, pointers.size());
        pointers.add(-1);
    }


    /**
     * Returns the representative ID of the disjoint set containing the given element.
     *
     * @param element The element to find the ID of.
     * @return The representative ID of the set containing element.
     * @throws IllegalArgumentException if element is not in any existing set.
     */
    public int findSet(T element) {
        // get index for given item in pointers
        Integer index = indices.get(element);
        if (index == null) {
            throw new IllegalArgumentException();
        }

        // loop until repIndex corresponds to the representative ID
        Integer repID = index;
        while (pointers.get(repID) >= 0) {
            repID = pointers.get(repID);
        }

        // optimization: compress path
        while (pointers.get(index) >= 0) {
            int temp = index;
            index = pointers.get(temp);
            pointers.set(temp, repID);
            indices.put(element, repID);
        }
        return repID;
    }


    /**
     * Unions two disjoint sets A and B and returns true if the given elements are
     * members of different sets. Returns false otherwise.
     *
     * @param elementA The element in set A.
     * @param elementB The element in set B.
     * @throws IllegalArgumentException if elementA or elementB are not in an existing set.
     */
    public boolean union(T elementA, T elementB) {
        if (!indices.containsKey(elementA) || !indices.containsKey(elementB)) {
            throw new IllegalArgumentException();
        }

        // get representative IDs of given items
        int id1 = findSet(elementA);
        int id2 = findSet(elementB);

        // check if items are already in the same set
        if (id1 == id2) {
            return false;
        }

        // optimization: point ID with smaller size to ID with larger size
        int id1Size = Math.abs(pointers.get(id1));
        int id2Size = Math.abs(pointers.get(id2));
        if (id1Size >= id2Size) {
            pointers.set(id2, id1);
            pointers.set(id1, -1 * (id1Size + id2Size));
        } else {
            pointers.set(id1, id2);
            pointers.set(id2, -1 * (id1Size + id2Size));
        }
        return true;
    }

}
