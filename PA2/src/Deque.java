/*
 * Louis Romeo
 * CSC 345 PA2
 * Purpose: An implementation of the Double-Ended Queue,
 * 			in which the Deque is re-sized by doubling
 * 			the capacity.
 */
public class Deque<T> {
    
	private Array<T> array; // Underlying array
    private int front;
    private int rear;
    private int size;
    
    private int accessCount;

    //constructor: default size of Deque should be 16
    public Deque() {        
    	this.array = new Array<>(16); // Default capacity 16
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        
        this.accessCount = 0;
    }

    // constructor: starting Deque size is <cap>
    public Deque(int cap) {        
    	this.array = new Array<>(cap);
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.accessCount = 0;
    }

    // adds a new element to the end of the Deque
    public void addLast(T item) {       
    	if (isFull()) {
            resize(array.length() * 2); // Double the deque size
        }
        rear = (rear + 1) % array.length();
        array.setVal(rear, item);
        size++;
        accessCount++; // accessed once in class
    }

    // adds a new element to the front of the Deque
    public void addFirst(T item) {        
    	if (isFull()) {
            resize(array.length() * 2);
        }
        front = (front - 1 + array.length()) % array.length();
        array.setVal(front, item);
        size++;
        accessCount++; // accessed once in class
    }

    // remove and return the first element in the Deque; throw the
    // exception if the list is empty
    public T removeFirst() throws EmptyDequeException {
    	if (isEmpty()) {
            throw new EmptyDequeException();
        }
        T removedItem = array.getVal(front);
        array.setVal(front, null);
        front = (front + 1) % array.length();
        size--;
        accessCount++; // accessed once in class
        if (size < array.length() / 4 && array.length() > 16) {
            resize(array.length() / 2);
        }
        return removedItem;
    }

    // remove and return the last element in the Deque; throw the
    // exception if the list is empty
    public T removeLast() throws EmptyDequeException { 
    	if (isEmpty()) {
            throw new EmptyDequeException();
        }
        T removedItem = array.getVal(rear);
        array.setVal(rear, null);
        rear = (rear - 1 + array.length()) % array.length();
        size--;
        accessCount++; // accessed once in class
        if (size < array.length() / 4 && array.length() > 16) {
            resize(array.length() / 2);
        }
        return removedItem;
    }

    // return but do not remove the first element in the Deque;
    // throw the exception if the list is empty
    public T peekFirst() throws EmptyDequeException {       
    	if (isEmpty()) {
            throw new EmptyDequeException();
        }
        accessCount++; // One access
        return array.getVal(front);
    }
    
    // return but do not remove the last element in the Deque;
    // throw the exception if the list is empty
    public T peekLast() throws EmptyDequeException {        
    	if (isEmpty()) {
            throw new EmptyDequeException();
        }
        accessCount++; // One access
        return array.getVal(rear);
    }

    // return true if the Deque is empty and false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // returns true if Deque is full, false if not
    private boolean isFull() {
        return size == array.length();
    }

    private void resize(int newCapacity) {    
    	Array<T> newArray = new Array<>(newCapacity);
        int index = 0;
        int i = front;
        while (index < size) {
            newArray.setVal(index, array.getVal(i));
            i = (i + 1) % array.length();
            index++;
        }
        array = newArray;
        front = 0;
        rear = size - 1;
        accessCount += size;
    }
    
    // size method added for testing.
    public int size() {
    	return size;
    }
    
    public int getAccessCount() {
        return accessCount;
    }
    //resets accessCount to 0--used only for testing!!!
    public void resetAccessCount() {
    	this.accessCount = 0;
     }
}
