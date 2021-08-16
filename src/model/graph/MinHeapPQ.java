package model.graph;

import java.util.*;

public class MinHeapPQ<T> {


    private final List<Entry<T>> minHeap;
    private final Map<T, Entry<T>> entryMap;
    private final Map<T, Integer> indexMap;


    /**
     * Constructs an empty Min-PQ.
     */
    public MinHeapPQ() {
        minHeap = new ArrayList<>();
        entryMap = new HashMap<>();
        indexMap = new HashMap<>();
    }


    public void offer(final T element, final double priority) {
        Entry<T> entry = new Entry<>(element, priority);
        entryMap.put(element, entry);
        indexMap.put(element, minHeap.size());
        minHeap.add(entry);
        percolateUp();
    }


    /**
     * Retrieves and removes the head of this Min-PQ, or returns null if
     * this Min-PQ is empty.
     *
     * @return The head of this queue, or null if this queue is empty.
     */
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, minHeap.size() - 1);
        Entry<T> result = minHeap.remove(minHeap.size() - 1);
        T element = result.getElement();
        entryMap.remove(element);
        indexMap.remove(element);
        percolateDown();
        return element;
    }

    public boolean contains(T element) {
        return entryMap.containsKey(element);
    }

    public void changePriority(T element, double priority) {
        Entry<T> entry = entryMap.get(element);
        entry.setPriority(priority);
        int entryIndex = indexMap.get(entry.getElement());
        percolateUp();
        percolateDown();
    }


    /**
     * Returns, but does not remove, the head of this Min-PQ.
     *
     * @return The head of this Min-PQ.
     */
    public Entry<T> peekMin() {
        if (isEmpty()) {
            return null;
        }
        return minHeap.get(0);
    }



    /**
     * Swaps the last element in the Min-Heap with its parent until its parent
     * is smaller or it's the root. Note:'percolate-up' is also commonly known as
     * 'bubble-up' or 'swim-Up'.
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
            int childIdx = leftChild(parentIdx);
            int rightChildIdx = rightChild(parentIdx);
            // if there's a right child and it's smaller then the left, update child swap index
            if (hasRightChild(parentIdx)) {
                double leftChildPriority = minHeap.get(childIdx).getPriority();
                double rightChildPriority = minHeap.get(rightChildIdx).getPriority();
                if (rightChildPriority < leftChildPriority) {
                    childIdx = rightChildIdx;
                }
            }

            // compare parent to smallest child
            if (minHeap.get(parentIdx).getPriority() > minHeap.get(childIdx).getPriority()) {
                swap(parentIdx, childIdx);
                parentIdx = childIdx;
            } else {
                entryIsPlaced = true;
            }
        }
    }



    /**
     * Checks if the (parent) element at the given index has a left child.
     *
     * @param parentIdx The index of the parent.
     * @return true if the element at parentIdx has a left child and false otherwise.
     */
    private boolean hasLeftChild(int parentIdx) {
        return leftChild(parentIdx) < size();
    }


    /**
     * Checks if the (parent) element at the given index has a right child.
     *
     * @param parentIdx The index of the parent.
     * @return true if the element at parentIdx has a right child and false otherwise.
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
        if (a >= size() || a < 0 || b >= size() || b < 0) {
            throw new IndexOutOfBoundsException();
        }
        Entry<T> temp = minHeap.get(a);
        minHeap.set(a, minHeap.get(b));
        minHeap.set(b, temp);
    }




    /**
     * Returns true if the element at the given index has a parent in the Min-Heap.
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
     * @param index The index of a child element.
     * @return The index of the parent.
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }


    /**
     * Returns the number of elements in this Min-PQ.
     *
     * @return The total number of elements in this Min-PQ.
     */
    public int size() {
        return minHeap.size();
    }


    /**
     * Checks if this Min-PQ is empty.
     *
     * @return true if this Min-PQ is empty and false otherwise.
     */
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }




    private static class Entry<T> {
        private final T myElement;
        private double myPriority;

        private Entry(final T theElement, final double thePriority) {
            myElement = theElement;
            myPriority = thePriority;
        }

        private void setPriority(final double thePriority) {
            myPriority = thePriority;
        }

        private double getPriority() {
            return myPriority;
        }

        private T getElement() {
            return myElement;
        }
    }
}
