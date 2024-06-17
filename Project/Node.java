/*
 * Author: Aarush Parvataneni
 * Purpose: defines the Node class that is the base of the binary tree
 * */	
public class Node<K> {
	
	private Node left;
	private Node right;
	private K val;
	private int count; // or frequency
	
	public Node(K input, int c) {
		val = input;
		count = c;
	}
	
	public Node(int c) {
		val = null;
		count = c;
	}
	
	public void setLeft(Node toLeft) {
		left = toLeft;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setRight(Node toRight) {
		right = toRight;
	}
	
	public Node getRight() {
		return right;
	}
	
	public K value() {
		return val;
	}
	
	public int count() {
		return count;
	}
	
	public void incrementCount() {
		count++;
	}
	
	// if the character's frequency/ count is higher, it returns a positive value
	public int compare(Node other) { 
		if (this.count > other.count()) {
			return 1;
		}
		if (this.count == other.count()) {
			return 0;
		}
		return -1;
	}
	
}

	
	
	

