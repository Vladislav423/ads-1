package ads_1.assignment_2;

import java.util.EmptyStackException;

/**
 * Custom implementation of a Stack using MyArrayList.
 * LIFO (Last-In, First-Out) data structure.
 * @param <T> generic type of elements
 */
public class MyStack<T> {
    // internal physical data structure to hold stack elements
    private MyArrayList<T> list;

    /**
     * Constructor initializes the internal MyArrayList.
     */
    public MyStack() {
        list = new MyArrayList<>();
    }

    /**
     * Pushes an item onto the top of this stack.
     * @param item the element to be pushed
     */
    public void push(T item) {
        // adding to the end of array is O(1) time complexity
        list.addLast(item);
    }

    /**
     * Removes the object at the top of this stack and returns it.
     * @return the element at the top of the stack
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // get the last element
        T item = list.getLast();
        // remove it from the list
        list.removeLast();
        return item;
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     * @return the element at the top of the stack
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.getLast();
    }

    /**
     * Tests if this stack is empty.
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Returns the number of elements in the stack.
     * @return size of the stack
     */
    public int size() {
        return list.size();
    }
}