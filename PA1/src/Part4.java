/*
 * Louis Romeo
 * CSC 345 PA1
 * 2/1/2024
 * Purpose: Solution to the given problem "Given an array of integers A, 
 * 			rearrange A so that all the 0’s are pushed to the end of the array. 
 * 			All other values should stay in the same relative order".
 */
public class Part4 {
    
	public static void pushZeroes(Array a) {
        int n = a.length();
        int nonZeroIndex = 0;

        // Traverse the array and find non-zero elements
        for (int i = 0; i < n; i++) {
            int current = a.getVal(i);

            if (current != 0) {
                // Check if a swap is needed
                if (nonZeroIndex != i) {
                    a.swap(nonZeroIndex, i);
                }
                nonZeroIndex++;
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        Array arr1 = new Array(new int[]{2, 6, 1, 9, 0, 2});
        pushZeroes(arr1);
        System.out.println("Result: " + arr1);

        Array arr2 = new Array(new int[]{0, 0, 6, 1, 0, 9, 8});
        pushZeroes(arr2);
        System.out.println("Result: " + arr2);

        Array arr3 = new Array(new int[]{3, 1, 4, 1, 5, 9, 2});
        pushZeroes(arr3);
        System.out.println("Result: " + arr3);
    }
}
