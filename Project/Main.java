/*
 * @author Dwij Ravikumar
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the textfile to build the Huffman Tree with:");
        String filename = scanner.nextLine();

        // Create an instance of HuffmanEncoderDecoder with the input filename
        HuffmanEncoderDecoder encoderDecoder = new HuffmanEncoderDecoder(filename);

        while (true) {
            System.out.println("Enter 'encode' to encode a string, 'decode' to decode a string, or 'close' to exit:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("encode")) {
                // Call the encode method to encode a string
                encode(encoderDecoder, scanner, filename);
            } else if (input.equalsIgnoreCase("decode")) {
                // Call the decode method to decode a string
                decode(encoderDecoder, scanner);
            } else if (input.equalsIgnoreCase("close")) {
                System.out.println("Closing the program...");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'encode', 'decode', or 'close'.");
            }
        }

        scanner.close();
    }

    private static void encode(HuffmanEncoderDecoder encoderDecoder, Scanner scanner, String filename) {
    	System.out.println("Would you like to encode the same file or a different string (y for file/ n for different string):");
    	String sameFile = scanner.nextLine();
    	String encodedData;
    	String saveOption;
    	if (sameFile.equalsIgnoreCase("y")) {
    		encodedData = encoderDecoder.encode(encoderDecoder.readFromFile(filename));
    		System.out.println("Encoded data: " + encodedData);
    	}
    	else if (sameFile.equalsIgnoreCase("n")) {
    		System.out.println("Enter the string to encode (if a char in the string doesn't "
    				+ "exist in our Huffman Tree, it will be erased):");
            String data = scanner.nextLine();
            // Encode the input string using HuffmanEncoderDecoder
            encodedData = encoderDecoder.encode(data);
            System.out.println("Encoded data: " + encodedData);
    	}
    	else {
    		System.out.println("Invalid Input");
    		encode(encoderDecoder, scanner, filename);
    		return;
    	}
    	System.out.println("Do you want to save the encoded data to a file? (yes/no)");
        saveOption = scanner.nextLine();
        if (saveOption.equalsIgnoreCase("yes")) {
        	System.out.println("What would you like to name the file?");
        	String encodeFileName = scanner.nextLine();
            // Save the decoded data to a file
            encoderDecoder.writeEncodedDataToFile(encodeFileName, encodedData);
            System.out.print("Encoded data saved to ");
            System.out.println(encodeFileName);
        }
    }

    private static void decode(HuffmanEncoderDecoder encoderDecoder, Scanner scanner) {
    	System.out.println();
    	System.out.println("Faults in the encoded binary string will lead to faulty results,"
    			+ "so please be wary and ensure the encoded string is\n trained from the same"
    			+ "tree as the one in use currently.");
    	System.out.println();
    	System.out.println("Do you want to decode a string or a file(enter string / file)?");
    	String option = scanner.nextLine();
    	String decodedData;
    	if (option.equalsIgnoreCase("file")) {
    		System.out.println("Enter file name to decode:");
    		String decodeFile = scanner.nextLine();
    		decodedData = encoderDecoder.decode(encoderDecoder.readFromFile(decodeFile));
    		System.out.println("Decoded data: " + decodedData);
    	}
    	else if (option.equalsIgnoreCase("string")) {
    		System.out.println("Enter the encoded string:");
            String encodedData = scanner.nextLine();
            // Decode the input string using HuffmanEncoderDecoder
            decodedData = encoderDecoder.decode(encodedData);
            System.out.println("Decoded data: " + decodedData);
    	}
    	else {
    		System.out.println("Invalid Input");
    		decode(encoderDecoder, scanner);
    		return;
    	}
        

        System.out.println("Do you want to save the decoded data to a file? (yes/no)");
        String saveOption = scanner.nextLine();
        if (saveOption.equalsIgnoreCase("yes")) {
        	System.out.println("What would you like to name the file?");
        	String decodeFileName = scanner.nextLine();
            // Save the decoded data to a file
            encoderDecoder.writeEncodedDataToFile(decodeFileName, decodedData);
            System.out.print("Decoded data saved to ");
            System.out.println(decodeFileName);
        }
    }
}
