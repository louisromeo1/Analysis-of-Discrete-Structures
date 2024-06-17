/*
 * Louis Romeo
 * CSC 345 PA1
 * 2/1/2024
 * Purpose: Solution to the given problem "Given an 
 * 			array of integers A and an integer m, determine 
 * 			the maximum product of m consecutive integers in the array".
 */
public class Part3 {
    
	public static int maxProduct(Array a, int m) {
        int n = a.length();

        if (n < m || m <= 0) {
            throw new IllegalArgumentException("Invalid input"); // Check for invalid input.
        }

        int maxProduct = 1;
        int currentProduct = 1;

        // Calculate product of first m elements.
        for (int i = 0; i < m; i++) {
            currentProduct *= a.getVal(i);
        }

        maxProduct = currentProduct; // Set the maximum.

        // Move the window and update product.
        for (int i = m; i < n; i++) {
            int outgoing = a.getVal(i - m);
            int incoming = a.getVal(i);

            if (outgoing != 0) {
                currentProduct = (currentProduct / outgoing) * incoming;
            } else {
                currentProduct = 1;
                // Calculate the product for the next m elements.
                for (int j = i - m + 1; j <= i; j++) {
                    currentProduct *= a.getVal(j);
                }
            }

            maxProduct = Math.max(maxProduct, currentProduct);
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        // Example for testing
        Array arr = new Array(new int[]{3, 1, 6, 2, 8, 9, 3, 4});
        int m = 3;

        int result = maxProduct(arr, m);
        System.out.println("Maximum product: " + result);
    }
}
