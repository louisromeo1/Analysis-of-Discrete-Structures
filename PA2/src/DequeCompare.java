/*
 * Louis Romeo
 * CSC 345 PA2
 * Purpose: Contains a small main method used for comparing
 * 			the performance of the Deque and Deque1 objects.
 */
public class DequeCompare {
    
	public static void main(String[] args) {
        
    	int N = 77; // Can be modified for other testing.

        Deque<Integer> deque = new Deque<>();
        Deque1<Integer> deque1 = new Deque1<>();

        // Adding int N elements to deque
        for (int i = 0; i < N; i++) {
            deque.addLast(i);
        }

        // Adding int N elements to deque1
        for (int i = 0; i < N; i++) {
            deque1.addLast(i);
        }

        // retrieving access counts for Deque and Deque1
        int accessCountDeque = deque.getAccessCount();
        int accessCountDeque1 = deque1.getAccessCount();

        // Printing
        System.out.println("Deque access count: " + accessCountDeque);
        System.out.println("Deque1 access count: " + accessCountDeque1);

        // Comparing access counts
        if (accessCountDeque < accessCountDeque1) {
            System.out.println("Deque has better performance based on access count.");
        } else if (accessCountDeque > accessCountDeque1) {
            System.out.println("Deque1 has better performance based on access count.");
        } else {
            System.out.println("Both Deques have similar performance based on access count.");
        }
    }
}
