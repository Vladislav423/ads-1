package ads_1.assignment_2;

import java.util.NoSuchElementException;

/**
 * Custom implementation of a Queue using MyLinkedList.
 * FIFO (First-In, First-Out) data structure.
 * @param <T> generic type of elements
 */
public class MyQueue<T> {
    // internal physical data structure to hold queue elements
    private MyLinkedList<T> list;

    /**
     * Constructor initializes the internal MyLinkedList.
     */
    public MyQueue() {
        list = new MyLinkedList<>();
    }

    /**
     * Inserts the specified element into the queue (at the end).
     * @param item the element to be added
     */
    public void enqueue(T item) {
        // adding to the end of linked list is O(1)
        list.addLast(item);
    }

    /**
     * Retrieves and removes the head (first element) of this queue.
     * @return the element at the front of the queue
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        // get the first element
        T item = list.getFirst();
        // remove it from the list
        list.removeFirst();
        return item;
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     * @return the element at the front of the queue
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.getFirst();
    }

    /**
     * Tests if this queue is empty.
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Returns the number of elements in the queue.
     * @return size of the queue
     */
    public int size() {
        return list.size();
    }
}