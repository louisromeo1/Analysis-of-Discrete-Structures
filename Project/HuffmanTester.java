/*
 * Author: Aarush Parvataneni
 * Purpose: Tests the initialization and encode/decode methods of the 
 * 			HuffmanEncoderDecoder class
 * */

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HuffmanTester {

	@Test
	// tests the encode and decode functions
	public void testEncodeDecode() {
		HuffmanEncoderDecoder huffman = new HuffmanEncoderDecoder("example1.txt");
		assertEquals(huffman.decode(huffman.encode(huffman.inputData())), huffman.inputData());
		huffman = new HuffmanEncoderDecoder("pinkfloyd.txt");
		assertEquals(huffman.decode(huffman.encode(huffman.inputData())), huffman.inputData());
	}
	
	@Test
	// tests the writing and reading of encoded and decoded text from files
	public void writeEncode() {
		HuffmanEncoderDecoder huffman = new HuffmanEncoderDecoder("example1.txt");
		// tests writing encoded data to file
		huffman.writeEncodedDataToFile("encoded.txt", huffman.encode(huffman.inputData()));
		assertEquals(huffman.encode(huffman.inputData()), huffman.readFromFile("encoded.txt"));
		// tests writing decoded data to file
		huffman.writeDecodedDataToFile("decoded.txt", huffman.decode(huffman.encode(huffman.inputData())));
		assertEquals(huffman.inputData(), huffman.readFromFile("decoded.txt"));
		
		huffman = new HuffmanEncoderDecoder("pinkfloyd.txt");
		huffman.writeEncodedDataToFile("encoded.txt", huffman.encode(huffman.inputData()));
		assertEquals(huffman.encode(huffman.inputData()), huffman.readFromFile("encoded.txt"));
		huffman.writeDecodedDataToFile("decoded.txt", huffman.decode(huffman.encode(huffman.inputData())));
		assertEquals(huffman.inputData(), huffman.readFromFile("decoded.txt"));
	}

}
