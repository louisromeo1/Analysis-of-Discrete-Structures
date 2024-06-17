/*
 * Louis Romeo
 * CSC 345 PA4
 * Purpose: This class models a Patient object, with fields for
 * 			name, urgency, time, and queue position.
 */
public class Patient implements Comparable<Patient> {
    
	private String name;
    private int urgency;
    private long timeIn;
    private int positionInQueue;

    // constructor
    public Patient(String name, int urgency, long timeIn) {
        
    	this.name = name;
        this.urgency = urgency;
        this.timeIn = timeIn;
        this.positionInQueue = -1;
    }

    // duplicate constructor
    public Patient(String name2, int urgency2) {
		this.name = name2;
		this.urgency = urgency2;
	}

	// get for patient name
    public String name() {
        return name;
    }

    // get for patient urgency
    public int urgency() {
        
    	return urgency;
    }

    // setters
    public void setUrgency(int urgency) {
        
    	this.urgency = urgency;
    }

    // comparator method
    @Override
    public int compareTo(Patient other) {
        
    	if (this.urgency == other.urgency()) {
            
        	return Long.compare(this.timeIn, other.timeIn);
        }
        return Integer.compare(this.urgency, other.urgency());
    }

    // toString method
    @Override
    public String toString() {
        
    	return String.format("%s, %d, %d, %d", name, urgency, 
    			timeIn, positionInQueue);
    }

    // set position in queue
    public void setPositionInQueue(int position) {
        
    	this.positionInQueue = position;
    }

    // get position in queue
    public int posInQueue() {
        
    	return positionInQueue;
    }
}