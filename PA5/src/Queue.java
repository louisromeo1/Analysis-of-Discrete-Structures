public class Queue<Item> {
    private int n;
    private Node front;
    private Node back;

    /***
     *constructor: create a new
     *empty Queue
     ***/
    public Queue() {
	this.n = 0;
    }

    /***
     *add a new item to the back 
     *of the Queue
     ***/
    public void enqueue(Item item) {
	if(front == null) {
	    front = new Node();
	    front.item = item;
	    back = front;
	} else {
	    back.next = new Node();
	    back = back.next;
	    back.item = item;
	}  
	n++;
    }

    /***
     *remove and return the front
     *item from the Queue;
     *throw an EmptyQueueException
     *if the Queue is empty
     ***/
    public Item dequeue() throws EmptyQueueException {
	if(isEmpty()) {
	    throw new EmptyQueueException();
	}
	Item item = front.item;
	front = front.next;
	n--;
	return item;
    }

    /***
     *return true if Queue is empty
     *and false otherwise 
     ***/
    public boolean isEmpty() {
	return front == null;
    }

    /***
     *return the number of items
     *currently in the Queue
     ***/
    public int size() {
	return n;
    }

    /***
     *return but do not remove the 
     *front item in the Queue;
     *throw an EmptyQueueException
     *if the Queue is empty
     ***/
    public Item peek() throws EmptyQueueException {
	if(isEmpty()) {
	    throw new EmptyQueueException();
	}
	return front.item;
    }

    private class Node {
	Item item;
	Node next;
    }
}
