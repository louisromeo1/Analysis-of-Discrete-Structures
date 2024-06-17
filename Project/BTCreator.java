/*
 * Author: Aarush Parvataneni, Kyle Vega
 * Description: Creates the binary huffman tree that is to be used to encode/ decode the data
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BTCreator {
	// Changed the Node to have a generic type cast - Kyle
	private Node<Character>[] nodeHeap;
	private int cap;
	private int size;
	private Node<Character> treeNode;
	private String encodeData;
	
	// this should take in the filename and create an 
	// array of single nodes (node.left = null & node.right = null)
	public BTCreator(String filename) {
        this.cap = 256; // Assuming ASCII characters
        this.nodeHeap = new Node[cap];
        this.size = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line;
            int nline = 0;
            while ((line = br.readLine()) != null) {
            	// add a newline at the beginning of every line other than before the very first line
            	if (nline > 0) {
            		sb.append('\n');
            	}
                sb.append(line);
                nline++;
            }
            String text = sb.toString();
            encodeData = text;
            
            boolean exists;
            for (char c : text.toCharArray()) {
            	exists = false;
                for (int i = 0; i < size; i++) {
                	// for when the char is already present in the list
                	// only increment the value
                	if (nodeHeap[i].value().equals(c)) {
                		nodeHeap[i].incrementCount();
                		exists = true;
                	}
                }
                // if it doesnt exist, then create the new Node and add to array
                if (! exists) {
            		nodeHeap[size] = new Node<Character>(c, 1);
            		size++;
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public Node returnNode(int index) {
		if (index >= size) {
			return null;
		}
		return nodeHeap[index];
	}
	
	public int size() {
		return size;
	}
	
	public Node<Character> createBT() {
		buildHeap(); // build the min Heap first
		int small;
		Node<Character> combineNode;
		while (size > 2) {
			if (nodeHeap[1].compare(nodeHeap[2]) > 0) {
				small = 2;
			}
			else {
				small = 1;
			}
			// new node with the combined count of its children
			combineNode = new Node<Character>(nodeHeap[0].count() + nodeHeap[small].count());
			// set the lowest two count nodes as its left and right children
			combineNode.setLeft(nodeHeap[0]);
			combineNode.setRight(nodeHeap[small]);
			nodeHeap[0] = combineNode; // replace top node with the new combined one
			swap(small, size - 1); // swap with final Node in heap
			size--;
			// sink the new node (no need to swim since we know that it can't be 
			// the smallest node at the root
			sink(small); 
			// sink the new combined node
			sink(0);
		}
		// combine the final two nodes to create the final binary tree
		// and return the root of the tree
		combineNode = new Node<Character>(nodeHeap[0].count() + nodeHeap[1].count());
		combineNode.setLeft(nodeHeap[0]);
		combineNode.setRight(nodeHeap[1]);
		treeNode = combineNode;
		return combineNode;
	}
	
	private void buildHeap() {
		// based on algorithm from textbook
		for (int i=size/2-1; i>=0; i--) {
			sink(i); 
		}
	}
	
	private void sink(int pos) {
		int curr = pos;
		int smallChild;
		while(curr < size/2) { // so that curr isn't a leaf node
			smallChild = 2*curr + 1;
			if (smallChild + 1 < size && nodeHeap[smallChild].compare(nodeHeap[smallChild + 1]) > 0) {
				smallChild += 1; // picks the smaller of the children
			}
			// if the child is larger or equal to the current Node, our Node is already in the correct spot
			if (nodeHeap[curr].compare(nodeHeap[smallChild]) <= 0) {
				break; 
			}
			swap(curr, smallChild);
			curr = smallChild;
		}
	}
	
	private void swap(int child, int parent) {
		if (child != parent) {
			Node<Character> temp = nodeHeap[child];
			nodeHeap[child] = nodeHeap[parent];
			nodeHeap[parent] = temp;
		}
	}
	
	public String inputData() {
		return encodeData;
	}
	
}
