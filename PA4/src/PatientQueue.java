/*
 * Louis Romeo
 * CSC 345 PA4
 * Purpose: A program representing a Queue of Patients
 * 			in a hospital implemented using only 1 array and
 * 			1 PHashtable object.
 */
public class PatientQueue {
    
	private PHashtable patientTable; // PHashtable to store patients
    private Patient[] patientArray; // Array to store patients
    private int size; // Current size of the queue

    // Constructor with default capacity
    public PatientQueue() {
        this(11);
    }

    // Constructor with specified capacity
    public PatientQueue(int cap) {
        this.patientTable = new PHashtable(cap);
        this.patientArray = new Patient[cap];
        this.size = 0;
    }

    // Method to insert a patient into the queue
    public void insert(Patient patient) {
        if (size == patientArray.length) {
            expandArray(); // Double the array size if it's full
        }
        patientTable.put(patient);
        patientArray[size] = patient;
        size++;
        bubbleUp(size - 1); // Maintain heap property
    }

    // Method to remove and return the next patient in the queue (with highest priority)
    public Patient removeNext() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        Patient patient = patientArray[0];
        patientTable.remove(patient.name());
        patientArray[0] = patientArray[size - 1];
        patientArray[size - 1] = null;
        size--;
        if (size > 1) {
            bubbleDown(0);
        }
        return patient;
    }

    // Method to return but not remove the next patient in the queue (with highest priority)
    public Patient getNext() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return patientArray[0];
    }

    // Method to get the size of the queue
    public int size() {
        return size;
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to remove a patient from the queue by name
    public Patient remove(String name) {
    	if (!patientTable.containsPatient(name)) {
    	    return null; // Patient not found
    	}
        Patient patient = patientTable.get(name);
        int index = findIndex(patient);
        patientTable.remove(name);
        patientArray[index] = patientArray[size - 1];
        patientArray[size - 1] = null;
        size--;
        if (size > 1) {
            bubbleDown(index);
        }
        return patient;
    }

 // Method to update the urgency level of a patient by name
    public Patient update(String name, int urgency) {
        if (!patientTable.containsPatient(name)) {
            return null; // Patient not found
        }
        Patient patient = patientTable.get(name);
        int index = findIndex(patient);

        int oldUrgency = patient.urgency(); // Store the old urgency

        // Update the urgency
        patient.setUrgency(urgency);

        // Determine whether to bubble up or down based on the change in urgency
        if (urgency > oldUrgency) {
            bubbleUp(index); // Bubble up if the urgency has increased
        } else if (urgency < oldUrgency) {
            bubbleDown(index); // Bubble down if the urgency has decreased
        }

        return patient;
    }

    // Helper method to maintain max-heap property when adding a patient
    private void bubbleUp(int index) {
        while (index > 0 && patientArray[parent(index)].compareTo(patientArray[index]) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    // Helper method to maintain max-heap property when removing a patient
    private void bubbleDown(int index) {
        int maxIndex = index;
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        if (leftChild < size && patientArray[leftChild].compareTo(patientArray[maxIndex]) > 0) {
            maxIndex = leftChild;
        }
        if (rightChild < size && patientArray[rightChild].compareTo(patientArray[maxIndex]) > 0) {
            maxIndex = rightChild;
        }
        if (maxIndex != index) {
            swap(index, maxIndex);
            bubbleDown(maxIndex);
        }
    }


    // Helper method to expand the array when it's full
    private void expandArray() {
        Patient[] newArray = new Patient[patientArray.length * 2];
        System.arraycopy(patientArray, 0, newArray, 0, patientArray.length);
        patientArray = newArray;
    }

    // Helper method to swap two elements in the array
    private void swap(int i, int j) {
        Patient temp = patientArray[i];
        patientArray[i] = patientArray[j];
        patientArray[j] = temp;
    }

    // Helper method to get the index of the parent of a node
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Helper method to get the index of the left child of a node
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // Helper method to get the index of the right child of a node
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Helper method to find the index of a patient in the array
    private int findIndex(Patient patient) {
        for (int i = 0; i < size; i++) {
            if (patientArray[i].equals(patient)) {
                return i;
            }
        }
        return -1; // Patient not found
    }
}
