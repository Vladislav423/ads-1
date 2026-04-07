package ads_1.assignment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom implementation of a dynamic array.
 * @param <T> generic type of elements
 */
public class MyArrayList<T> implements MyList<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Default constructor creates an array with initial capacity 10.
     */
    public MyArrayList() {
        // initialize array with default capacity
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Helper method to double the size of the array when it is full.
     */
    private void increaseBuffer() {
        // create new array with double capacity
        Object[] newElements = new Object[elements.length * 2];

        // copy old elements to the new array
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        // replace old array with the new one
        elements = newElements;
    }

    /**
     * Helper method to check if the index is valid.
     */
    private void checkIndex(int index) {
        // check if index is out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(T item) {
        // check if array is full
        if (size == elements.length) {
            increaseBuffer();
        }
        // add item to the end and increase size
        elements[size++] = item;
    }

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        // replace element at specific index
        elements[index] = item;
    }

    @Override
    public void add(int index, T item) {
        // check if index is valid for insertion
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        // increase capacity if needed
        if (size == elements.length) {
            increaseBuffer();
        }

        // shifting elements to the right to make room
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        // insert new item
        elements[index] = item;
        // increase size of elements
        size++;
    }

    @Override
    public void addFirst(T item) {
        // add item to the beginning (index 0)
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        // add item to the end
        add(item);
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        // cast Object to generic type T and return
        return (T) elements[index];
    }

    @Override
    public T getFirst() {
        // check if list is empty
        if (size == 0) throw new NoSuchElementException();
        return get(0);
    }

    @Override
    public T getLast() {
        // check if list is empty
        if (size == 0) throw new NoSuchElementException();
        return get(size - 1);
    }

    @Override
    public void remove(int index) {
        checkIndex(index);

        // shifting elements to the left to close the gap
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        // delete last element (clear reference)
        elements[size - 1] = null;
        // decrease size of elements
        size--;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size - 1);
    }

    @Override
    public void sort() {
        // simple bubble sort algorithm
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                Comparable<T> c1 = (Comparable<T>) elements[j];
                T c2 = (T) elements[j + 1];

                // swap elements if the first is greater than the second
                if (c1.compareTo(c2) > 0) {
                    Object temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        // search from start to end
        for (int i = 0; i < size; i++) {
            if (object == null ? elements[i] == null : object.equals(elements[i])) {
                return i; // return index if found
            }
        }
        return -1; // return -1 if not found
    }

    @Override
    public int lastIndexOf(Object object) {
        // search from end to start
        for (int i = size - 1; i >= 0; i--) {
            if (object == null ? elements[i] == null : object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        // returns true if index is not -1
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        // create new array with exact size
        Object[] array = new Object[size];
        // copy elements
        for (int i = 0; i < size; i++) {
            array[i] = elements[i];
        }
        return array;
    }

    @Override
    public void clear() {
        // reset array and size
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        // create custom iterator for our list
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                // check if there are more elements
                return currentIndex < size;
            }

            @Override
            public T next() {
                // throw exception if no elements left
                if (!hasNext()) throw new NoSuchElementException();
                // return current element and move to the next
                return (T) elements[currentIndex++];
            }
        };
    }
}