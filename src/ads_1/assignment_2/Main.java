package ads_1.assignment_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MyStack<Integer> stack = new MyStack<>();
        MyQueue<Integer> queue = new MyQueue<>();
        MyMinHeap<Integer> heap = new MyMinHeap<>();

        boolean running = true;

        System.out.println("Welcome to Custom Data Structures Tester!");

        while (running) {
            System.out.println("\n=================================");
            System.out.println("Select a Data Structure to test:");
            System.out.println("1. Test MyStack (LIFO)");
            System.out.println("2. Test MyQueue (FIFO)");
            System.out.println("3. Test MyMinHeap (Min at top)");
            System.out.println("0. Exit");
            System.out.println("=================================");
            System.out.print("Your choice: ");

            int mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    testStack(scanner, stack);
                    break;
                case 2:
                    testQueue(scanner, queue);
                    break;
                case 3:
                    testMinHeap(scanner, heap);
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void testStack(Scanner scanner, MyStack<Integer> stack) {
        boolean stackRunning = true;
        while (stackRunning) {
            System.out.println("\n--- MyStack Menu ---");
            System.out.println("1. Push (Add element)");
            System.out.println("2. Pop (Remove & get top)");
            System.out.println("3. Peek (Get top without removing)");
            System.out.println("4. Check size");
            System.out.println("0. Back to main menu");
            System.out.print("Choose action: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter an integer to push: ");
                    int val = scanner.nextInt();
                    stack.push(val);
                    System.out.println("-> Pushed " + val + " to stack.");
                    break;
                case 2:
                    try {
                        System.out.println("-> Popped: " + stack.pop());
                    } catch (Exception e) {
                        System.out.println("-> Error: Stack is empty!");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("-> Top element is: " + stack.peek());
                    } catch (Exception e) {
                        System.out.println("-> Error: Stack is empty!");
                    }
                    break;
                case 4:
                    System.out.println("-> Stack size is: " + stack.size());
                    break;
                case 0:
                    stackRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void testQueue(Scanner scanner, MyQueue<Integer> queue) {
        boolean queueRunning = true;
        while (queueRunning) {
            System.out.println("\n--- MyQueue Menu ---");
            System.out.println("1. Enqueue (Add to end)");
            System.out.println("2. Dequeue (Remove from front)");
            System.out.println("3. Peek (Check front)");
            System.out.println("4. Check size");
            System.out.println("0. Back to main menu");
            System.out.print("Choose action: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter an integer to enqueue: ");
                    int val = scanner.nextInt();
                    queue.enqueue(val);
                    System.out.println("-> Enqueued " + val + " to queue.");
                    break;
                case 2:
                    try {
                        System.out.println("-> Dequeued: " + queue.dequeue());
                    } catch (Exception e) {
                        System.out.println("-> Error: Queue is empty!");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("-> Front element is: " + queue.peek());
                    } catch (Exception e) {
                        System.out.println("-> Error: Queue is empty!");
                    }
                    break;
                case 4:
                    System.out.println("-> Queue size is: " + queue.size());
                    break;
                case 0:
                    queueRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private static void testMinHeap(Scanner scanner, MyMinHeap<Integer> heap) {
        boolean heapRunning = true;
        while (heapRunning) {
            System.out.println("\n--- MyMinHeap Menu ---");
            System.out.println("1. Insert (Add element)");
            System.out.println("2. Extract Min (Remove & get minimum)");
            System.out.println("3. Get Min (Check minimum without removing)");
            System.out.println("4. Check size");
            System.out.println("0. Back to main menu");
            System.out.print("Choose action: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter an integer to insert: ");
                    int val = scanner.nextInt();
                    heap.insert(val);
                    System.out.println("-> Inserted " + val + " into Min-Heap.");
                    break;
                case 2:
                    try {
                        System.out.println("-> Extracted Minimum: " + heap.extractMin());
                    } catch (Exception e) {
                        System.out.println("-> Error: Heap is empty!");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("-> Current Minimum is: " + heap.getMin());
                    } catch (Exception e) {
                        System.out.println("-> Error: Heap is empty!");
                    }
                    break;
                case 4:
                    System.out.println("-> Heap size is: " + heap.size());
                    break;
                case 0:
                    heapRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}