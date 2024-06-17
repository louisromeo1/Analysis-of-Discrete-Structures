import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/*
 * Louis Romeo
 * CSC 345 PA1
 * 2/1/2024
 * Purpose: This program reads integer input from a text file,
 * 			and calculates the remainder over an Array of size M.
 */
public class Part1 {
    public static void main(String[] args) {
	int m = 96;
	int p = 97;
	int testNum = 1;
	String input = null;
	double score = 0.0;

	while(testNum <= 3) {
	    input = "test_input" + testNum + ".txt";
	    score += runTest(input, m, p, testNum);
	    testNum++;
	}
	System.out.println("Expected Score: " + score + "/9.0");
    }

    /****
     * TO BE IMPLEMENTED BY STUDENT
     ***/
    public static int[] getCounts(String fn, int m) {
    	Map<Integer, Integer> countsMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fn))) {
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line.trim()); // Parse from file.
                int remainder = number % m; // Calculates the remainder

                countsMap.put(remainder, countsMap.getOrDefault(remainder, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] counts = new int[m];
        for (Map.Entry<Integer, Integer> entry : countsMap.entrySet()) {
            counts[entry.getKey()] = entry.getValue(); // Sets values equal.
        }

        return counts;
    }

    /****
     *The following methods are used in the main method for testing purposes,
     *so I recommend you do not change them. However, the grading is done 
     *with a different class (with similar methods), so if you do change them, it shouldn't 
     *be a problem unless they do not compile.
     ****/

    //This is the method for running each test.
    private static double runTest(String input, int m, int p, int testNum) {
	double score = 0.0;
	System.out.println("\nRunning on " + input + " with modulus " + m + "...");
	System.out.println("***********************************************\n");
	int[] counts = Part1.getCounts(input, m);
	//Part1.printCounts(counts);
	String output = "test_output" + testNum + "a.txt";
	score += checkCounts(counts, output);

	System.out.println("\nRunning on " + input + " with modulus " + p + "...");
	System.out.println("***********************************************\n");
	counts = Part1.getCounts(input, p);
	//Part1.printCounts(counts);
	output = "test_output" + testNum + "b.txt";
	score += checkCounts(counts, output);
	return score;
    }

    //This method compares your results to the expected results, which are read from a text file.
    private static double checkCounts(int[] counts, String output) {
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(output));
	    String line = br.readLine();
	    int i = 0;
	    while(line != null) {
		String[] split = line.split(":\t");
		if(Integer.parseInt(split[1]) != counts[i]) {
		    System.out.println("\nCounts for " + i + " do not match.");
		    return 0.0;
		}
		line = br.readLine();
		i++;
	    }
	    br.close();
	} catch (Exception e) {
	    e.printStackTrace();
	    return 0.0;
	}
	System.out.println("\nAll the counts match!");
	return 1.5;
    }

    private static void printCounts(int[] counts) {
	for(int i = 0; i < counts.length; i++) 
	    System.out.println(i + ":\t" + counts[i]);
    }
}
	

