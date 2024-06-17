/*
 * Louis Romeo
 * CSC 345 PA4
 * Purpose: Class to represent a hospital clinic using
 * 			the PatientQueue and PHashtable files.
 */
public class Clinic {
    private int er_threshold; // The highest level of urgency the clinic can handle
    private PatientQueue patientQueue; // Patient queue for non-ER patients
    private PHashtable erPatients; // Hashtable for ER patients
    private int processedCount; // Number of patients processed
    private int sentToERCount; // Number of patients sent to ER
    private int seenByDoctorCount; // Number of patients seen by doctor
    private int walkedOutCount; // Number of patients who walked out

    // Constructor
    public Clinic(int er_threshold) {
        this.er_threshold = er_threshold;
        this.patientQueue = new PatientQueue();
        this.erPatients = new PHashtable();
        this.processedCount = 0;
        this.sentToERCount = 0;
        this.seenByDoctorCount = 0;
        this.walkedOutCount = 0;
    }

    // Return the ER threshold
    public int er_threshold() {
        return er_threshold;
    }

    // Process the new patient with the given name and urgency
    public String process(String name, int urgency) {
        if (urgency > er_threshold) {
            erPatients.put(new Patient(name, urgency));
            sentToERCount++;
            return name;
        } else {
            patientQueue.insert(new Patient(name, urgency));
            processedCount++;
            return name;
        }
    }

    // Send the next patient in the queue to be seen by a doctor
    public String seeNext() throws EmptyQueueException {
    	if (isEmpty()) {
            throw new EmptyQueueException();
        }
        Patient nextPatient = patientQueue.removeNext();
        seenByDoctorCount++;
        return nextPatient.name();
    }

    public boolean isEmpty() {
        return patientQueue.size() == 0;
    }

	// Handle an emergency for a patient
    public boolean handle_emergency(String name, int urgency) {
        if (erPatients.containsPatient(name)) {
            patientQueue.update(name, urgency);
            return true;
        } else {
            Patient patient = patientQueue.remove(name);
            if (patient != null) {
                patient.setUrgency(urgency);
                if (urgency > er_threshold) {
                    erPatients.put(patient);
                    sentToERCount++;
                    return true;
                } else {
                    patientQueue.insert(patient);
                    return false;
                }
            }
            return false;
        }
    }

    // A patient walks out before being seen by a doctor
    public void walk_out(String name) {
        if (erPatients.containsPatient(name)) {
            erPatients.remove(name);
            walkedOutCount++;
        } else {
            patientQueue.remove(name);
            walkedOutCount++;
        }
    }

    // Return the number of patients that have been processed
    public int processed() {
        return processedCount;
    }

    // Return the number of patients that have been sent to the ER
    public int sentToER() {
        return sentToERCount;
    }

    // Return the number of patients that have been seen by a doctor
    public int seenByDoctor() {
        return seenByDoctorCount;
    }

    // Return the number of patients that walked out before being seen by a doctor
    public int walkedOut() {
        return walkedOutCount;
    }
}
