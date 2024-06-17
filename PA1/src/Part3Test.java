public class Part3Test {
    private static int[] mVals = new int[] {3, 5, 5, 3, 7, 5, 5, 4, 5, 3};
    private static int[] exp = new int[] {720,28800, 0, 20, 97200, 3888, 259350, 51870, 17010, 576};
    private static int[] accesses = new int[] {46, 50, 30, 34, 64, 60, 110, 120, 520, 506};
    private static double accuracyScore = 0.0;
    private static double efficiencyScore = 0.0;
    public static void main(String[] args) {
	for(int i = 1; i <= 10; i++) {
	    runTest(i);
	}
	System.out.println("Accuracy Score for Part 3: " + accuracyScore);
	System.out.println("Efficiency Score for Part 3: " + efficiencyScore);
    }

    private static void runTest(int testNum) {
	int arrayNum = (testNum + 1)/2;
	Array array = new Array("array" + arrayNum + ".txt");
	int m = mVals[testNum-1];
	int maxProduct = Part3.maxProduct(array, m);

	//check answer
	if(maxProduct == exp[testNum-1])
	    accuracyScore += 1.0;
	else
	    printMsg("value", testNum, exp[testNum-1], maxProduct);
	
	//check arrayAccesses
	int accessCount = array.getAccessCount(); System.out.println(accessCount);
	if(accessCount <= accesses[testNum-1])
	    efficiencyScore+=0.5;
	else {
	    printMsg("accessCount", testNum, accesses[testNum-1], accessCount);
	}
    }

    private static void printMsg(String which, int testNum, int exp, int act) {
	if(which.equals("value")) {
	    System.out.println("The values for test # " + testNum + " do not match.");
	} else {
	    System.out.println("The access count for test # " + testNum + " is too high. Check the code manually.");
	}
	System.out.println("Expected: " + exp);
	System.out.println("Actual: " + act);
    }
}
