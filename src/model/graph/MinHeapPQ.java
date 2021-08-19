/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.graph;

import java.util.*;

/**
 * Implements a min-heap priority queue that maintains the following
 * invariants:
 *      (1) Binary Tree Invariant: Every node has at most 2 children;
 *      (2) Min-Heap Invariant: Every node is less than or equal to its
 *          children;
 *      (3) Min-Heap Structure Invariant: A heap is always a complete tree.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class MinHeapPQ<T> {

    /** The list representation of the min-heap. */
    private final List<Entry<T>> minHeap;

    /** A mapping of each element to its Entry. */
    private final Map<T, Entry<T>> entryMap;


    /**
     * Constructs an empty MinHeapPQ.
     */
    public MinHeapPQ() {
        minHeap = new ArrayList<>();
        entryMap = new HashMap<>();
    }


    /**
     * Adds the given element to this MinHeapPQ.
     *
     * @param element The element to add.
     * @param priority The priority to assign this element.
     * @throws NullPointerException if element is null.
     * @throws IllegalArgumentException if element is already in this MinHeapPQ
     */
    public void offer(final T element, final double priority) {
        if (element == null) {
            throw new NullPointerException("given element was null");
        }
        if (contains(element)) {
            throw new IllegalArgumentException("element already in queue");
        }
        Entry<T> entry = new Entry<>(element, priority);
        entryMap.put(element, entry);
        minHeap.add(entry);
        percolateUp();
    }


    /**
     * Retrieves and removes the head of this MinHeapPQ or returns null if
     * this MinHeapPQ is empty.
     *
     * @return The head of this queue or null if this queue is empty.
     */
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, minHeap.size() - 1);
        Entry<T> result = minHeap.remove(minHeap.size() - 1);
        T element = result.getElement();
        entryMap.remove(element);
        percolateDown();
        return element;
    }


    /**
     * Returns true if this MinHeapPQ contains the given element and false
     * otherwise.
     *
     * @param element The element to check for in this MinHeapPQ.
     * @return true if element is in this MinHeapPQ and false otherwise.
     * @throws NullPointerException if element is null.
     */
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        return entryMap.containsKey(element);
    }


    /**
     * Changes the current priority of the given element to the given priority.
     *
     * @param element The element to change the priority of.
     * @param priority The new priority to assign the element.
     */
    public void changePriority(T element, double priority) {
        if (element == null) {
            throw new NullPointerException("given element was null");
        }
        if (!contains(element)) {
            throw new NoSuchElementException("given element was not in queue");
        }

        Entry<T> entry = entryMap.get(element);
        entry.setPriority(priority);
        int idx = minHeap.indexOf(entry);
        swap(0, idx);
        percolateDown();
    }


    /**
     * Returns, but does not remove, the head of this MinHeapPQ.
     *
     * @return The head of this MinHeapPQ.
     */
    public Entry<T> peekMin() {
        if (isEmpty()) {
            return null;
        }
        return minHeap.get(0);
    }


    /**
     * Swaps the last element in this MinHeapPQ with its parent until its
     * parent is smaller or it's the root.
     */
    private void percolateUp() {
        int idx = minHeap.size() - 1;
        double parentPriority = minHeap.get(parent(idx)).getPriority();
        double childPriority = minHeap.get(idx).getPriority();
        /*
        Swap the recently added element with its parent while it has a parent
        and is less than that parent.
         */
        while (hasParent(idx) && parentPriority > childPriority) {
            swap(idx, parent(idx));
            idx = parent(idx);
            parentPriority = minHeap.get(parent(idx)).getPriority();
            childPriority = minHeap.get(idx).getPriority();
        }
    }


    /**
     * Swaps the entry at the given index with its smallest child until
     * invariant (2) is restored.
     */
    private void percolateDown() {
        int parentIdx = 0;
        boolean entryIsPlaced = false;
        /*
        If the Min-Heap has a single child, then it only has a left child. This
        is because the Heap structure invariant requires that a Heap is always
        a complete tree (i.e., that every level is full except possibly the last,
        which is filled from left to right with no gaps).
         */
        while (hasLeftChild(parentIdx) && !entryIsPlaced) {
            int smallest = leftChild(parentIdx);
            int rightChildIdx = rightChild(parentIdx);
            if (hasRightChild(parentIdx)) {
                double leftPri = minHeap.get(smallest).getPriority();
                double rightPri = minHeap.get(rightChildIdx).getPriority();
                if (rightPri < leftPri) {
                    smallest = rightChildIdx;
                }
            }
            // compare parent to smallest child
            double parentPri = minHeap.get(parentIdx).getPriority();
            double smallestPri = minHeap.get(smallest).getPriority();
            if (parentPri > smallestPri) {
                swap(parentIdx, smallest);
                parentIdx = smallest;
            } else {
                entryIsPlaced = true;
            }
        }
    }


    /**
     * Checks if the (parent) element at the given index has a left child.
     *
     * @param parentIdx The index of the parent.
     * @return true if the element at parentIdx has a left child and false
     *     otherwise.
     */
    private boolean hasLeftChild(int parentIdx) {
        return leftChild(parentIdx) < size();
    }


    /**
     * Checks if the (parent) element at the given index has a right child.
     *
     * @param parentIdx The index of the parent.
     * @return true if the element at parentIdx has a right child and false
     *     otherwise.
     */
    private boolean hasRightChild(int parentIdx) {
        return rightChild(parentIdx) < size();
    }


    /**
     * Returns the index of the left child for the given parent index.
     * NOTE: If indexing starts at 1, left index = 2 * parentIndex.
     *
     * @param parentIdx The index of the parent.
     * @return The index of the left child.
     */
    private int leftChild(int parentIdx) {
        return 2 * parentIdx + 1;
    }


    /**
     * Returns the index of the right child for the given parent index.
     * NOTE: If indexing starts at 1, right index = (2 * parent index) + 1.
     *
     * @param parentIdx The index of the parent.
     * @return The index of the right child.
     */
    private int rightChild(int parentIdx) {
        return leftChild(parentIdx) + 1;
    }


    /**
     * Swaps the elements at the given indices.
     *
     * @param a The index of the first element.
     * @param b The index of the second element.
     */
    private void swap(int a, int b) {
        Entry<T> temp = minHeap.get(a);
        minHeap.set(a, minHeap.get(b));
        minHeap.set(b, temp);
    }


    /**
     * Returns true if the element at the given index has a parent in this
     * MinHeapPQ
     *
     * @param index The index of the element to check the parent of.
     * @return true if the element at index has a parent and false otherwise.
     */
    private boolean hasParent(int index) {
        return parent(index) >= 0;
    }


    /**
     * Returns the parent index of the given index.
     *
     * @param childIndex The index of a child element.
     * @return The index of the parent.
     */
    private int parent(final int childIndex) {
        if (childIndex == 0) {
            return 0;
        } else {
            return (childIndex - 1) / 2;
        }
    }


    /**
     * Returns the number of elements in this MinHeapPQ.
     *
     * @return The total number of elements in this MinHeapPQ.
     */
    public int size() {
        return minHeap.size();
    }


    /**
     * Checks if this MinHeapPQ is empty.
     *
     * @return true if this MinHeapPQ is empty and false otherwise.
     */
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }


    /**
     * Implements an Entry in the MinHeapPQ.
     */
    private static class Entry<T> {

        /** The element of this Entry. */
        private final T myElement;

        /** The priority of this Entry. */
        private double myPriority;


        /**
         * Constructs an Entry containing the given element and priority.
         *
         * @param theElement The element of this Entry.
         * @param thePriority The priority of this Entry.
         */
        private Entry(final T theElement, final double thePriority) {
            myElement = theElement;
            myPriority = thePriority;
        }


        /**
         * Sets the priority of this Entry.
         *
         * @param thePriority The value to assign the priority to.
         */
        private void setPriority(final double thePriority) {
            myPriority = thePriority;
        }


        /**
         * Returns the priority of this Entry.
         *
         * @return The priority of this Entry.
         */
        private double getPriority() {
            return myPriority;
        }


        /**
         * Returns the element of this Entry.
         *
         * @return The element of this Entry.
         */
        private T getElement() {
            return myElement;
        }


        @Override
        public String toString() {
            return "(" + myElement + ", " + myPriority + ")";
        }
    }

}
