# Assignment 2 - Custom Data Structures

## Overview
This project contains custom implementations of core data structures from scratch, without using `java.util.*` collections.

## Physical Data Structures
* **MyArrayList**: A dynamic array that automatically resizes (doubles capacity) when full.
* **MyLinkedList**: A doubly linked list with `head` and `tail` pointers for O(1) insertions and deletions at both ends. Circular references are prevented to help the Garbage Collector.

## Logical Data Structures
* **MyStack (LIFO)**: Based on `MyArrayList`. Adding and removing from the end (`addLast`, `removeLast`) is O(1).
* **MyQueue (FIFO)**: Based on `MyLinkedList`. We enqueue at the `tail` and dequeue from the `head` in O(1) time without shifting elements.
* **MyMinHeap**: Based on `MyArrayList`. A complete binary tree optimally stored in an array using index formulas (`2i+1`, `2i+2`).