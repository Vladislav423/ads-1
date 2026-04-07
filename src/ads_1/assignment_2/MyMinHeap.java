package ads_1.assignment_2;

import java.util.NoSuchElementException;

/**
 * Custom implementation of a Min Heap using MyArrayList.
 * Elements must be comparable to maintain the heap property.
 * @param <T> generic type of elements that implements Comparable
 */
public class MyMinHeap<T extends Comparable<T>> {
    // internal physical data structure to hold heap elements
    private MyArrayList<T> list;

    /**
     * Constructor initializes the internal MyArrayList.
     */
    public MyMinHeap() {
        list = new MyArrayList<>();
    }

    /**
     * Inserts a new item into the heap and maintains the min-heap property.
     * @param item the element to be inserted
     */
    public void insert(T item) {
        // 1. add item to the end of the array
        list.addLast(item);
        // 2. move it up to its correct position
        heapifyUp(list.size() - 1);
    }

    /**
     * Removes and returns the minimum element (the root) of the heap.
     * @return the minimum element
     */
    public T extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        // the minimum element is always at the root (index 0)
        T min = list.getFirst();
        T last = list.getLast();

        // remove the last element
        list.removeLast();

        // if the heap is not empty after removal, move the last element to the root
        if (!isEmpty()) {
            list.set(0, last);
            // move it down to its correct position
            heapifyDown(0);
        }

        return min;
    }

    /**
     * Returns the minimum element without removing it.
     * @return the minimum element
     */
    public T getMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return list.getFirst();
    }

    /**
     * Helper method to maintain heap property by moving an element up.
     * @param index the index of the element to move up
     */
    private void heapifyUp(int index) {
        while (index > 0) {
            // formula to find parent index
            int parentIndex = (index - 1) / 2;

            // if current element is smaller than its parent, swap them
            if (list.get(index).compareTo(list.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex; // update index to continue moving up
            } else {
                // if it's not smaller, it's in the correct position
                break;
            }
        }
    }

    /**
     * Helper method to maintain heap property by moving an element down.
     * @param index the index of the element to move down
     */
    private void heapifyDown(int index) {
        int size = list.size();
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            // check if left child exists and is smaller than current smallest
            if (leftChild < size && list.get(leftChild).compareTo(list.get(smallest)) < 0) {
                smallest = leftChild;
            }

            // check if right child exists and is smaller than current smallest
            if (rightChild < size && list.get(rightChild).compareTo(list.get(smallest)) < 0) {
                smallest = rightChild;
            }

            // if the smallest is not the current node, swap and continue
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                // if the current node is the smallest, it's in the correct position
                break;
            }
        }
    }

    /**
     * Helper method to swap two elements in the internal list.
     */
    private void swap(int index1, int index2) {
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    /**
     * Tests if this heap is empty.
     * @return true if heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Returns the number of elements in the heap.
     * @return size of the heap
     */
    public int size() {
        return list.size();
    }
}