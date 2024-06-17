public class Node {
    
	private int val;
    private Node next;

    public Node(int val) {
    	this.val = val;
    	this.next = null;
    }

    public int val() {
    	return this.val;
    }

    public Node next() {
    	return this.next;
    }

    public void setNext(Node n) {
    	this.next = n;
    }

    public String toString() {
    	return "" + val;
    }
}
