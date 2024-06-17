import java.util.*;

public class DequeTest {
    
	private static Random gen = new Random(System.currentTimeMillis());
    private static ArrayList<Integer> expI = new ArrayList<Integer>();
    private static Deque<Integer> actI = new Deque<Integer>();
    private static ArrayList<String> expS = new ArrayList<String>();
    private static Deque<String> actS = new Deque<String>();
    private static HashMap<String, Integer> map = new HashMap<String, Integer>();
    
    public static void main(String[] args) {
	setUpMap();
	double score = 0.0;
	//tests for Integers
	System.out.println("\n*****Running tests on Deque with integers*****\n");
	score += testAddLast();
	score += testAddFirst();
	score += testRemoveFirst();
	score += testRemoveLast();
	score += testPeekFirst();
	score += testPeekLast();
	score += finalTest();
	//tests for Strings
	System.out.println("\n*****Running tests on Deque with Strings*****\n");
	score += testAddLastS();
	score += testAddFirstS();
	score += testRemoveFirstS();
	score += testRemoveLastS();
	score += testPeekFirstS();
	score += testPeekLastS();
	System.out.println("\nExpected Score: " + score);
    }

    private static void setUpMap() {
	map.put("addLast", 500);
	map.put("addFirst", 620);
	map.put("removeFirst", 327);
	map.put("removeLast", 277);
	map.put("peekFirst", 2);
	map.put("peekLast", 2);
	map.put("final", 5000);
	map.put("addLastS", 150);
	map.put("addFirstS", 300);
	map.put("removeFirstS", 375);
	map.put("removeLastS", 50);
	map.put("peekFirstS", 2);
	map.put("peekLastS", 2);
    }

    //testing the addLast method
    private static double testAddLast() {
	double score = 0.0;
	System.out.println("\nTesting addLast method...");
	int count = 0;
	while(count < 100) {
	    Integer num = gen.nextInt(100);
	    expI.add(num);
	    actI.addLast(num);
	    count++;
	}
	if(checkLists()) {
	    score += 1.5;
	}
	if(checkCounts("addLast",true)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the addFirst method
    private static double testAddFirst() {
	double score = 0.0;
	System.out.println("\nTesting addFirst method...");
	int count = 0;
	while(count < 100) {
	    Integer num = gen.nextInt(100);
	    expI.add(0, num);
	    actI.addFirst(num);
	    count++;
	}
	if(checkLists()) {
	    score += 1.5;
	}
	if(checkCounts("addFirst",true)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the removeFirst method
    private static double testRemoveFirst() {
	double score = 0;
	System.out.println("\nTesting removeFirst method...");
	int count = 1;
	Integer e = -1;
	Integer a = -1;
	while(count <= 50) {
	    try {
		e = expI.remove(0);
		a = actI.removeFirst();
	    } catch (Exception ex) {
		ex.printStackTrace();
		return 0;
	    }	
	    
	    if(!e.equals(a)) {
		System.out.println("Removed items do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
		return 0;
	    }
	    count++;
	}
	if(checkLists()) {
	    score += 1.5;
	}
	if(checkCounts("removeFirst",true)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the removeLast method
    private static double testRemoveLast() {
	double score = 0;
	System.out.println("\nTesting removeLast method...");
	int count = 1;
	Integer e = -1;
	Integer a = -1;
	while(count <= 50) {
	    try {
		e = expI.remove(expI.size()-1);
		a = actI.removeLast();
	    } catch (Exception ex) {
		ex.printStackTrace();
		return 0;
	    }
	    
	    if(!e.equals(a)) {
		System.out.println("Removed items do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
		return 0;
	    }
	    count++;
	}
	if(checkLists()) {
	    score += 1.5;
	}
	if(checkCounts("removeLast",true)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the peekFirst method
    private static double testPeekFirst() {
	double score = 0;
	System.out.println("\nTesting peekFirst method...");
	int count = 1;
	Integer e = -1;
	Integer a = -1;
	try {
	    e = expI.get(0);
	    a = actI.peekFirst();
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return 0;
	}
	if(!e.equals(a)) {
	    System.out.println("Peeked items do not match.");
	    System.out.println("Expected: " + e);
	    System.out.println("Actual: " + a);
	    return 0;
	}
	if(checkCounts("peekFirst",true)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }
    //testing the peekLast method
    private static double testPeekLast() {
	double score = 0;
	System.out.println("\nTesting peekLast method...");
	int count = 1;
	Integer e = -1;
	Integer a = -1;
	try {
	    e = expI.get(expI.size()-1);
	    a = actI.peekLast();
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return 0;
	}
	if(!e.equals(a)) {
	    System.out.println("Peeked items do not match.");
	    System.out.println("Expected: " + e);
	    System.out.println("Actual: " + a);
	    return 0;
	}
	if(checkCounts("peekLast",true)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //runs various methods many times and tests the results
    private static double finalTest() {
	actI = new Deque<Integer>(10);
	expI = new ArrayList<Integer>();
	double score = 0;
	System.out.println("\nTesting for robustness...");
	int count = 0;
	//add a bunch of elements to the lists
	System.out.println("Adding elements...");
	while(count < 500) {
	    Integer num = gen.nextInt(500);//System.out.println("adding " + num);
	    expI.add(num);
	    actI.addLast(num);
	    count++;
	}
	if(checkLists())
	    score += 1.5;
	while(count > 0) {
	    Integer num = gen.nextInt(500);//System.out.println("adding " + num);
	    expI.add(0, num);
	    actI.addFirst(num);
	    count--;
	}
	if(checkLists())
	    score += 1.5;
	Integer e = null;
	Integer a = null;
	while(!expI.isEmpty()) {
	    try {
		e = expI.remove(0);
		a = actI.removeFirst();
		if(e.equals(a))
		    score += 0.01;
		if(!expI.isEmpty()) {
		   e = expI.remove(expI.size()-1);
		   a = actI.removeLast();
		   if(e.equals(a))
		       score += 0.01;
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
		return score;
	    }
	}
	if(actI.isEmpty())
	    score += 1.5;
	if(checkCounts("final",true)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + (int)(score+1));
	return (int)(score+1);
    }

      //testing the addLast method
    private static double testAddLastS() {
	double score = 0.0;
	System.out.println("\nTesting addLast method...");
	int count = 0;
	while(count < 150) {
	    String num = "" + gen.nextInt(100);
	    expS.add(num);
	    actS.addLast(num);
	    count++;
	}
	if(checkListsS()) {
	    score += 1.5;
	}
	if(checkCounts("addLastS",false)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the addFirst method
    private static double testAddFirstS() {
	double score = 0.0;
	System.out.println("\nTesting addFirst method...");
	int count = 0;
	while(count < 150) {
	    String num = "" + gen.nextInt(100);
	    expS.add(0, num);
	    actS.addFirst(num);
	    count++;
	}
	if(checkListsS()) {
	    score += 1.5;
	}
	if(checkCounts("addFirstS",false)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the removeFirst method
    private static double testRemoveFirstS() {
	double score = 0;
	System.out.println("\nTesting removeFirst method...");
	int count = 1;
	String e = null;
	String a = null;
	while(count <= 75) {
	    try {
		e = expS.remove(0);
		a = actS.removeFirst();
	    } catch (Exception ex) {
		ex.printStackTrace();
		return 0;
	    }	
	    
	    if(!e.equals(a)) {
		System.out.println("Removed items do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
		return 0;
	    }
	    count++;
	}
	if(checkListsS()) {
	    score += 1.5;
	}
	if(checkCounts("removeFirstS",false)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the removeLast method
    private static double testRemoveLastS() {
	double score = 0;
	System.out.println("\nTesting removeLast method...");
	int count = 1;
	String e = null;
	String a = null;
	while(count <= 50) {
	    try {
		e = expS.remove(expS.size()-1);
		a = actS.removeLast();
	    } catch (Exception ex) {
		ex.printStackTrace();
		return 0;
	    }
	    
	    if(!e.equals(a)) {
		System.out.println("Removed items do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
		return 0;
	    }
	    count++;
	}
	if(checkListsS()) {
	    score += 1.5;
	}
	if(checkCounts("removeLastS",false)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the peekFirst method
    private static double testPeekFirstS() {
	double score = 0;
	System.out.println("\nTesting peekFirst method...");
    	String e = "";
	String a = "";
	try {
	    e = expS.get(0);
	    a = actS.peekFirst();
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return 0;
	}
	if(!e.equals(a)) {
	    System.out.println("Peeked items do not match.");
	    System.out.println("Expected: " + e);
	    System.out.println("Actual: " + a);
	    return 0;
	}
	if(checkCounts("peekFirstS",false)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }
    //testing the peekLast method
    private static double testPeekLastS() {
	double score = 0;
	System.out.println("\nTesting peekLast method...");
	String e = null;
	String a = null;
	try {
	    e = expS.get(expS.size()-1);
	    a = actS.peekLast();
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return 0;
	}
	if(!e.equals(a)) {
	    System.out.println("Peeked items do not match.");
	    System.out.println("Expected: " + e);
	    System.out.println("Actual: " + a);
	    return 0;
	}
	if(checkCounts("peekLastS",false)) {
	    score += 1.5;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    private static boolean checkLists() {
	//compare list sizes
	if(expI.size() != actI.size()) {
	    System.out.println("Sizes of lists do not match.");
	    return false;
	}
	return true;
    }

    private static boolean checkListsS() {
	//compare list sizes
	if(expS.size() != actS.size()) {
	    System.out.println("Sizes of lists do not match.");
	    return false;
	}
	return true;
    }

    private static boolean checkCounts(String method, boolean integers) {
	int e = map.get(method);
	int a = -1;
	if(integers)
	    a = actI.getAccessCount();
	else
	    a = actS.getAccessCount();
	if(a > 1.5*e) {
	    System.out.println("The access count for " + method + " is too high.");
	    System.out.println("Expected: " + e);
	    System.out.println("Actual: " + a);
	    actI.resetAccessCount();
	    actS.resetAccessCount();
	    return false;
	}
	actI.resetAccessCount();
	actS.resetAccessCount();
	return true;
    }
}
