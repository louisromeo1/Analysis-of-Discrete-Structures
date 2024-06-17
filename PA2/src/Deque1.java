/*
 * Louis Romeo
 * CSC 345 PA2
 * Purpose: Implementation of a second Deque, where
 * 			it is resized by constant integer size which
 * 			can be modified.
 */
public class Deque1<T> {
    
	private Array<T> array;
    private int front;
    private int rear;
    private int size;
    private int accessCount;

    // Constant for resizing (should be modified for performance testing)
    private static final int RESIZE_CONSTANT = 8;

    public Deque1() {
        this.array = new Array<>(RESIZE_CONSTANT);
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.accessCount = 0;
    }

    // Constructor with capacity parameter.
    public Deque1(int cap) {
        this.array = new Array<>(cap);
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.accessCount = 0;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(array.length() + RESIZE_CONSTANT);
        }
        rear = (rear + 1) % array.length();
        array.setVal(rear, item);
        size++;
        accessCount++; // accessed once in class
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(array.length() + RESIZE_CONSTANT);
        }
        front = (front - 1 + array.length()) % array.length();
        array.setVal(front, item);
        size++;
        accessCount++; // accessed once in class
    }

    public T removeFirst() throws EmptyDequeException {
        if (isEmpty()) {
            throw new EmptyDequeException();
        }
        T removedItem = array.getVal(front);
        array.setVal(front, null);
        front = (front + 1) % array.length();
        size--;
        accessCount++; // accessed once in class
        if (size < array.length() / 4 && array.length() > RESIZE_CONSTANT * 2) {
            resize(array.length() - RESIZE_CONSTANT);
        }
        return removedItem;
    }

    public T removeLast() throws EmptyDequeException {
        if (isEmpty()) {
            throw new EmptyDequeException();
        }
        T removedItem = array.getVal(rear);
        array.setVal(rear, null);
        rear = (rear - 1 + array.length()) % array.length();
        size--;
        accessCount++; // accessed once in class
        if (size < array.length() / 4 && array.length() > RESIZE_CONSTANT * 2) {
            resize(array.length() - RESIZE_CONSTANT);
        }
        return removedItem;
    }

    public T peekFirst() throws EmptyDequeException {
        if (isEmpty()) {
            throw new EmptyDequeException();
        }
        accessCount++; // One access
        return array.getVal(front);
    }

    public T peekLast() throws EmptyDequeException {
        if (isEmpty()) {
            throw new EmptyDequeException();
        }
        accessCount++; // One access
        return array.getVal(rear);
    }

    public boolean isEmpty() {
        return size == 0;
    }

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

    // Resets accessCount to 0--used only for testing!!!
    public void resetAccessCount() {
        this.accessCount = 0;
    }
}
