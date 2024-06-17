public class LinkedList {
    private Node head;

    public LinkedList() {
    	this.head = null;
    }

    public LinkedList(Node head) {
    	this.head = head;
    }

    //adds a new node to the head of the list
    public void add(int val) {
    	Node newNode = new Node(val);
    	newNode.setNext(head);
    	this.head = newNode;
    }

    //get the head
    public Node head() {
	return this.head;
    }

    //get the tail
    public LinkedList tail() {
	if(this.head != null) {
		return new LinkedList(this.head.next());
	}
	return null;
    }

    //check if the list is empty
    public boolean isEmpty() {
    	return this.head == null;
    }
}
