/*
 * Author: Aarush Parvataneni
 * Purpose: Tests  the initialization and methods of the Node class
 * */	

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NodeTest {
	
	@Test
	public void testCreateType1() {
		Node<Character> tester = new Node<Character>('A' ,25);
		assertEquals(25, tester.count());
		assertEquals(65, (int)tester.value());
		tester = new Node<Character>('B' ,5);
		assertEquals(5, tester.count());
		assertEquals(66, (int)tester.value());
		tester = new Node<Character>('\n' ,13);
		assertEquals(13, tester.count());
		assertEquals(10, (int)tester.value());
	}
	
	@Test
	public void testCreateType2() {
		Node<Character> tester = new Node<Character>(2);
		assertEquals(2, tester.count());
		assertEquals(null, tester.value());
		tester = new Node<Character>(1500);
		assertEquals(1500, tester.count());
		assertEquals(null, tester.value());
	}
	
	@Test
	public void testSetLeftRight() {
		Node<Character> tester = new Node<Character>(0);
		Node<Character> left = new Node<Character>(1);
		Node<Character> right = new Node<Character>(2);
		Node<Character> leftLeft = new Node<Character>(3);
		Node<Character> rightRight = new Node<Character>(4);
		tester.setLeft(left);
		tester.setRight(right);
		tester.getLeft().setLeft(leftLeft);
		tester.getRight().setRight(rightRight);
		assertEquals(0, tester.count());
		assertEquals(1, tester.getLeft().count());
		assertEquals(2, tester.getRight().count());
		assertEquals(3, tester.getLeft().getLeft().count());
		assertEquals(4, tester.getRight().getRight().count());
	}
	
	@Test
	public void testCompare() {
		Node<Character> tester = new Node<Character>(0);
		Node<Character> other = new Node<Character>(4);
		assertEquals(-1, tester.compare(other));
		tester = new Node<Character>(4);
		assertEquals(0, tester.compare(other));
		tester.incrementCount();
		assertEquals(1, tester.compare(other));
		assertEquals(-1, other.compare(tester));
	}
}
