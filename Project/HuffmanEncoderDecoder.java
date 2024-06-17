/*
 * Louis Romeo
 * CSC 345 Group Project
 * Purpose: To encode and decode text using the Huffman Algorithm and write it to files 
 */
import java.io.*;

public class HuffmanEncoderDecoder {
    
	private BTCreator btCreator;
    private Node<Character> root;
    private String inputData;

    // Constructor creates a new BinaryTree for
    // encoded values - Louis
    public HuffmanEncoderDecoder(String filename) {
        btCreator = new BTCreator(filename);
        root = btCreator.createBT();
        inputData = btCreator.inputData();
    }

    // Method to encode data - Louis
    public String encode(String data) {
        StringBuilder encodedData = new StringBuilder();
        for (char c : data.toCharArray()) {
            String code = getCode(c);
            encodedData.append(code);
        }
        return encodedData.toString();
    }

    // Method to decode data - Louis
    public String decode(String encodedData) {
        StringBuilder decodedData = new StringBuilder();
        Node<Character> currentNode = root;
        for (char bit : encodedData.toCharArray()) {
        	// goto left or right child based on bit value
            if (bit == '0') {
                currentNode = currentNode.getLeft();
            } else if (bit == '1') {
                currentNode = currentNode.getRight();
            }
            // if this is a leaf node, append leaf value to string and
            // return node to the root
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                decodedData.append(currentNode.value());
                currentNode = root;
            }
        }
        return decodedData.toString();
    }

    // Method to get Huffman code for a character
    private String getCode(char c) {
        StringBuilder code = new StringBuilder();
        getCodeHelper(root, c, "", code);
        return code.toString();
    }

    // Helper method to traverse the tree and find 
    // Huffman code for a character - Louis
    private void getCodeHelper(Node<Character> node, char c, String currentCode, StringBuilder code) {
        if (node == null) return;
        if (node.getLeft() == null && node.getRight() == null) { // only check for leaf nodes
        	if (node.value().equals(c)) {
                code.append(currentCode);
                return;
            }
        }
        getCodeHelper(node.getLeft(), c, currentCode + "0", code); // add 0 if we take the left path
        getCodeHelper(node.getRight(), c, currentCode + "1", code);// add 1 if we take the right path
    }

    // Method to write encoded data to a file
    public void writeEncodedDataToFile(String filename, String encodedData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(encodedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to write decoded data to a file
    public void writeDecodedDataToFile(String filename, String decodedData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(decodedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read data from a file
    public static String readFromFile(String filename) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int nline = 0;
            // read every line in the file and append it to string
            while ((line = reader.readLine()) != null) {
            	// add a newline at the beginning of every line other than before the very first line
            	if (nline > 0) { 
            		data.append('\n');
            	}
                data.append(line);
                nline++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
    
    public String inputData() {
    	return inputData;
    }
    
}
