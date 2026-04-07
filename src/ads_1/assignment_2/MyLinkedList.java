package ads_1.assignment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom implementation of a Doubly Linked List.
 * @param <T> generic type of elements
 */
public class MyLinkedList<T> implements MyList<T> {

    /**
     * Inner class representing a node in the doubly linked list.
     */
    private class MyNode {
        T element;
        MyNode next;
        MyNode prev;

        public MyNode(T element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    /**
     * Default constructor creates an empty list.
     */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Helper method to check if the index is valid.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Helper method to find a node at a specific index.
     * Optimized to search from the closest end (head or tail).
     */
    private MyNode getNode(int index) {
        checkIndex(index);
        MyNode current;
        // if index is in the first half, start from head
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        // if index is in the second half, start from tail
        else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }

    @Override
    public void set(int index, T item) {
        MyNode node = getNode(index);
        // replace the data inside the node
        node.element = item;
    }

    @Override
    public void add(int index, T item) {
        // handle adding at the very end
        if (index == size) {
            addLast(item);
            return;
        }
        // handle adding at the very beginning
        if (index == 0) {
            addFirst(item);
            return;
        }

        MyNode current = getNode(index);
        MyNode newNode = new MyNode(item);
        MyNode previousNode = current.prev;

        // connect the new node between previousNode and current
        previousNode.next = newNode;
        newNode.prev = previousNode;
        newNode.next = current;
        current.prev = newNode;

        size++;
    }

    @Override
    public void addFirst(T item) {
        MyNode newNode = new MyNode(item);
        // if list is empty, new node becomes both head and tail
        if (head == null) {
            head = tail = newNode;
        } else {
            // attach new node before the current head
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode newNode = new MyNode(item);
        // if list is empty, new node becomes both head and tail
        if (tail == null) {
            head = tail = newNode;
        } else {
            // attach new node after the current tail
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        return getNode(index).element;
    }

    @Override
    public T getFirst() {
        if (head == null) throw new NoSuchElementException();
        return head.element;
    }

    @Override
    public T getLast() {
        if (tail == null) throw new NoSuchElementException();
        return tail.element;
    }

    @Override
    public void remove(int index) {
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }

        MyNode current = getNode(index);
        MyNode previousNode = current.prev;
        MyNode nextNode = current.next;

        // link previous and next nodes together, skipping the current one
        previousNode.next = nextNode;
        nextNode.prev = previousNode;

        // clear references to prevent loops and help Garbage Collector
        current.next = null;
        current.prev = null;

        size--;
    }

    @Override
    public void removeFirst() {
        if (head == null) throw new NoSuchElementException();

        // move head to the next node
        head = head.next;
        size--;

        if (head == null) {
            // if list becomes empty, tail should be null too
            tail = null;
        } else {
            // remove reference to the deleted node
            head.prev = null;
        }
    }

    @Override
    public void removeLast() {
        if (tail == null) throw new NoSuchElementException();

        // move tail to the previous node
        tail = tail.prev;
        size--;

        if (tail == null) {
            // if list becomes empty, head should be null too
            head = null;
        } else {
            // remove reference to the deleted node
            tail.next = null;
        }
    }

    @Override
    public void sort() {
        // simple bubble sort: swapping data instead of node pointers to prevent loops
        if (size > 1) {
            boolean wasChanged;
            do {
                MyNode current = head;
                wasChanged = false;
                while (current.next != null) {
                    Comparable<T> c1 = (Comparable<T>) current.element;
                    T c2 = current.next.element;

                    // swap elements if the first is greater than the second
                    if (c1.compareTo(c2) > 0) {
                        T temp = current.element;
                        current.element = current.next.element;
                        current.next.element = temp;
                        wasChanged = true;
                    }
                    current = current.next;
                }
            } while (wasChanged);
        }
    }

    @Override
    public int indexOf(Object object) {
        MyNode current = head;
        int index = 0;
        // traverse from head to tail
        while (current != null) {
            if (object == null ? current.element == null : object.equals(current.element)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        MyNode current = tail;
        int index = size - 1;
        // traverse from tail to head
        while (current != null) {
            if (object == null ? current.element == null : object.equals(current.element)) {
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyNode current = head;
        int index = 0;
        // copy elements to array
        while (current != null) {
            array[index++] = current.element;
            current = current.next;
        }
        return array;
    }

    @Override
    public void clear() {
        // remove all references to prevent memory leaks and loops
        MyNode current = head;
        while (current != null) {
            MyNode next = current.next;
            current.prev = null;
            current.next = null;
            current.element = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.element;
                current = current.next; // move to the next node
                return data;
            }
        };
    }
}