import java.util.LinkedList;

public class PHashtable {
    
	private static final int DEFAULT_CAPACITY = 11; // Default initial capacity
    private int capacity; // Current capacity
    private LinkedList<Patient>[] table; // Array of LinkedLists for separate chaining

    // Constructor with default capacity
    public PHashtable() {
        this(DEFAULT_CAPACITY);
    }

    // Constructor with specified capacity
    public PHashtable(int cap) {
        this.capacity = cap;
        this.table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Hash function to determine the index for a given patient
    private int hash(String name) {
        return Math.abs(name.hashCode() % capacity);
    }

    // Method to get a patient from the hashtable by name
    public Patient get(String name) {
        int index = hash(name);
        for (Patient patient : table[index]) {
            if (patient.name().equals(name)) {
                return patient;
            }
        }
        return null; // Patient not found
    }

    // Method to add a patient to the hashtable
    public void put(Patient patient) {
        int index = hash(patient.name());
        table[index].add(patient);
    }

    // Method to remove a patient from the hashtable by name
    public Patient remove(String name) {
        int index = hash(name);
        for (Patient patient : table[index]) {
            if (patient.name().equals(name)) {
                table[index].remove(patient);
                return patient;
            }
        }
        return null; // Patient not found
    }

    // Method to get the size of the hashtable
    public int size() {
        int count = 0;
        for (LinkedList<Patient> list : table) {
            count += list.size();
        }
        return count;
    }
    
    public boolean containsPatient(String name) {
        for (LinkedList<Patient> list : table) {
            for (Patient patient : list) {
                if (patient.name().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}
