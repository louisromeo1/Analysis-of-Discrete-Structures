/*
 * Author: Aarush Parvataneni
 * Purpose: tests the initialization and creation of the Huffman Tree via the BTCreator class
 * */

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class BTCreatorTest {
	
	@Test
	public void testBTCreatorInitializer() {
		BTCreator tester = new BTCreator("example1.txt");
		assertEquals(11, tester.size());
		assertEquals(2, tester.returnNode(0).count());
		assertEquals('H', tester.returnNode(0).value());
		assertEquals(2, tester.returnNode(4).count());
		assertEquals(' ', tester.returnNode(4).value());
		tester = new BTCreator("pinkfloyd.txt");
		assertEquals(3, tester.returnNode(0).count());
		assertEquals('A', tester.returnNode(0).value());
		assertEquals(4, tester.returnNode(17).count());
		assertEquals('Y', tester.returnNode(17).value());
		assertEquals(27, tester.returnNode(16).count());
		assertEquals('\n', tester.returnNode(16).value());
	}
	
	@Test
	public void testCreateBT() {
		BTCreator tester = new BTCreator("example1.txt");
		Node tree = tester.createBT();
		assertEquals(18, tree.count());
		assertEquals(true, testValidTree(tree));
		ArrayList<Integer> heights = new ArrayList<Integer>();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		testEfficiency(tree, heights, counts, 0);
		assertEquals(heights.size(), counts.size());
		// this algorithm to test efficiency has an O(n^2) score but since we know that
		// Ns value is capped to 256, it will not be much of an issue
		for (int i = 0; i < heights.size(); i++) {
			for (int j = 0; j < heights.size(); j++) {
				// test case fails if a higher count is present at a bigger height (lower level)
				if (heights.get(i) > heights.get(j) && counts.get(i) > counts.get(j)) {
					assertEquals(true, false);
					return;
				}
			}
		}
		
		tester = new BTCreator("pinkfloyd.txt");
		tree = tester.createBT();
		assertEquals(true, testValidTree(tree));
		heights = new ArrayList<Integer>();
		counts = new ArrayList<Integer>();
		testEfficiency(tree, heights, counts, 0);
		assertEquals(heights.size(), counts.size());
		
		for (int i = 0; i < heights.size(); i++) {
			for (int j = 0; j < heights.size(); j++) {
				// test case fails if 
				if (heights.get(i) > heights.get(j) && counts.get(i) > counts.get(j)) {
					assertEquals(true, false);
					return;
				}
			}
		}
	}
	
	private boolean testValidTree(Node curr) {
		if (curr == null) { 
			return true;
		}
		// checks that the leaf nodes contain the values of the characters in the file
		if (curr.getLeft() == null && curr.getRight() == null) { 
			if (curr.value().equals(null)) {
				return false;
			}
			else {
				return true;
			}
		}
		// if there is no left node, check that the count is the same as the right child's count 
		// and that the the curr node's value is null (since it is an interior node)
		// and call this function recursively to the right child
		else if (curr.getLeft() == null) {
			return (curr.count() == curr.getRight().count()) && (curr.value() == null) && testValidTree(curr.getRight());
		}
		// if there is no left node, check that the count is the same as the left child's count 
		// and that the the curr node's value is null (since it is an interior node)
		// and call this function recursively to the left child
		else if (curr.getRight() == null) {
			return (curr.count() == curr.getLeft().count()) && (curr.value() == null) && testValidTree(curr.getLeft());
		}
		// do the same as above but check to see the count equals the sum of its children
		else {
			return (curr.count() == curr.getLeft().count() + curr.getRight().count()) && (curr.value() == null) && testValidTree(curr.getLeft()) && testValidTree(curr.getRight());
		}
	}
	
	private void testEfficiency(Node curr, ArrayList<Integer> heights, ArrayList<Integer> c, int h) {
		if (curr == null) {return;}
		// adds the leaf nodes' counts and heights to their respective arraylists at the same time
		if (curr.getLeft() == null && curr.getRight() == null) {
			heights.add(h);
			c.add(curr.count());
			return;
		}
		// recursively calls the left and right children
		testEfficiency(curr.getLeft(), heights, c, h+1);
		testEfficiency(curr.getRight(), heights, c, h+1);
	}
	
}
