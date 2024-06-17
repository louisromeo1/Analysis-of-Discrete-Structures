import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part4Test {
    private static int[] accesses = new int[] {20,34,101,126,1162};
    private static double accuracyScore = 0.0;
    private static double efficiencyScore = 0.0;
    public static void main(String[] args) {
	for(int i = 1; i <= 5; i++) {
	    runTest(i);
	}
	System.out.println("Accuracy Score for Part 4: " + accuracyScore);
	System.out.println("Efficiency Score for Part 4: " + efficiencyScore);
    }

    private static void runTest(int testNum) {
	int arrayNum = testNum;
	Array array = new Array("array" + arrayNum + ".txt");
	int[] copy = getCopy("array" + arrayNum + ".txt");

	Part4.pushZeroes(array);

	int accessCount = array.getAccessCount();
	
	//check that all the zeroes are at the end
	int firstZero = array.length();
	for(int i = 0; i < array.length(); i++) {
	    if(array.getVal(i) == 0) {
		firstZero = i;
		break;
	    }
	}
	boolean zeroesLast = true;
	int i = firstZero;
	while(i < array.length()) {
	    if(array.getVal(i) != 0) {
		System.out.println("The array does not appear to be split correctly.");
		zeroesLast = false;
		break;
	    }
	    i++;
	}

	//check that the array still has all the same elements
	boolean arraysMatch = array.compare(copy);
	if(!arraysMatch) 
	    System.out.println("The array does not have all the same elements.");

	if(zeroesLast && arraysMatch)
	    accuracyScore += 2.0;
	
	//check arrayAccesses
	if(accessCount <= accesses[testNum-1])
	    efficiencyScore+=1.0;
	else if(accessCount <= 2*accesses[testNum-1]) {
	    printMsg("accessCount", testNum, accesses[testNum-1], accessCount);
	    efficiencyScore += 0.5;
	} else {
	    printMsg("accessCount", testNum, accesses[testNum-1], accessCount);
	}
    }

    private static void printMsg(String which, int testNum, int exp, int act) {
	if(which.equals("value")) {
	    System.out.println("The values for test # " + testNum + " do not match.");
	} else {
	    System.out.println("The access count for test # " + testNum + " is high.");
	}
	System.out.println("Expected: " + exp);
	System.out.println("Actual: " + act);
    }

    private static int[] getCopy(String fn) {
	int[] array = null;
	int n = 0;
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    String line = br.readLine();
	    if(line != null) {
		n = Integer.parseInt(line);
	    }
	    line = br.readLine();
	    int i = 0;
	    array = new int[n];
	    while(line != null && i < n) {
		int num = Integer.parseInt(line);
		array[i] = num;
		line = br.readLine();
		i++;
	    }
	    br.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return array;
    }
}

