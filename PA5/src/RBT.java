/*
 * Date: 4/24/2024
 * Purpose: Program modeling a right-leaning red-black tree,
 * contains methods for operations all done in O(logN) runtime.
 * 
 * Author: Louis Romeo
 * CSC 345 PA5
 */
public class RBT {
    
	private Node root;
    private int size;

    /* CONSTRUCTOR */
    public RBT() {
	this.root = null;
	this.size = 0;
    }

    /* PUBLIC METHODS */

    /***
     *insert a new key into tree
     *or update the count if the key already exists
     */
    public void put(int key) {
	if(root == null) {
	    root = new Node(key);
	    size++;
	}
	else 
	    root = put(key, root);
	root.setColor(Node.BLACK);
    }
    
    
    
    /***
     *get the count associated with the given key;
     *return 0 if key doesn't exist in the tree
     */
    public int get(int key) {
	Node x = root;
	while(x != null) {
	    if(key == x.key()) 
		return x.count();
	    if(key > x.key())
		x = x.right();
	    else
		x = x.left();
	}
	return 0;
    }

    /***
     *get the color of a node
     ***/
    public String getColor(int key) {
	Node x = get(key, root);
	if(x == null)
	    return null;
	if(x.isRed())
	    return "RED";
	return "BLACK";
    }

    /***
     *return true if the tree
     *is empty and false 
     *otherwise
     */
    public boolean isEmpty() {
	return root == null;
    }

    /***
     *return the number of Nodes
     *in the tree
     */
    public int size() {
	return size;
    }

    /***
     *returns the height of the tree
     */
    public int height() {
	return height(root);
    }

    /***
     *returns the height of node 
     *with given key in the tree;
     *return -1 if the key does
     *not exist in the tree
     */
    public int height(int key) {
	Node x = get(key, root);
	return height(x);
    }

    /***
     *returns true if the key is in the tree
     *and false otherwise
     ***/
    public boolean contains(int key) {
	Node x = get (key, root);
	if(x != null)
	    return true;
	return false;
    }


     /***
     *return the depth of the node
     *with the given key in the tree;
     *return -1 if the key does not exist
     *in the tree
     ***/
    public int depth(int key) {
        return depthHelper(root, key); // Call helper method with root and key
    }

    // Helper method to calculate depth of a node with key
    private int depthHelper(Node node, int key) {
        int depth = 0;
        Node current = node;
        while (current != null) {
            if (key == current.key()) {
                return depth;
            } else if (key < current.key()) {
                current = current.left();
            } else {
                current = current.right();
            }
            depth++;
        }
        return -1; // Key not found
    }

    /***
     *return the size of the subtree 
     *rooted at the given key
     ***/
    public int size(int key) {
        Node x = get(key, root);
        if (x != null) {
            return size(x); // Call recursive method to calculate size of subtree
        }
        return -1; // Key not found
    }

    // Recursive method to calculate size of subtree rooted at a node
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + size(x.left()) + size(x.right());
    }

     /***
     *return the minimum key
     ***/
    public int min() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        Node minNode = min(root);
        return minNode.key();
    }

    // Helper method to find the node with the minimum key in the subtree rooted at node x
    private Node min(Node x) {
        while (x.left() != null) {
            x = x.left();
        }
        return x;
    }

     /***
     *return the maximum key
     ***/
    public int max() throws EmptyTreeException {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        Node maxNode = max(root);
        return maxNode.key();
    }

    // Helper method to find the node with the maximum key in the subtree rooted at node x
    private Node max(Node x) {
        while (x.right() != null) {
            x = x.right();
        }
        return x;
    }

    /***
     *return the largest key
     *that is less than or equal
     *to the parameter
     ***/
    public int floor(int key) throws KeyDoesNotExistException {
        if (isEmpty()) {
            throw new KeyDoesNotExistException();
        }
        Node floorNode = floor(root, key);
        if (floorNode != null) {
            return floorNode.key();
        } else {
            throw new KeyDoesNotExistException();
        }
    }

    // Helper method to find the largest key less than or equal to the parameter in the subtree rooted at node x
    private Node floor(Node x, int key) {
        Node floor = null;
        while (x != null) {
            int cmp = Integer.compare(key, x.key());
            if (cmp == 0) {
                return x;
            }
            if (cmp < 0) {
                x = x.left();
            } else {
                floor = x;
                x = x.right();
            }
        }
        return floor;
    }

     /***
     *return the smallest key
     *that is greater than or equal
     *to the parameter
     ***/
    public int ceil(int key) throws KeyDoesNotExistException {
        if (isEmpty()) {
            throw new KeyDoesNotExistException();
        }
        Node ceilNode = ceil(root, key);
        if (ceilNode != null) {
            return ceilNode.key();
        } else {
            throw new KeyDoesNotExistException();
        }
    }

    // Helper method to find the smallest key greater than or equal to the parameter in the subtree rooted at node x
    private Node ceil(Node x, int key) {
        Node ceil = null;
        while (x != null) {
            int cmp = Integer.compare(key, x.key());
            if (cmp == 0) {
                return x;
            }
            if (cmp > 0) {
                x = x.right();
            } else {
                ceil = x;
                x = x.left();
            }
        }
        return ceil;
    }

    /***
     *return the number of keys
     *that are less than the parameter
     ***/
    public int rank(int key) {
        return rank(root, key);
    }

    // Helper method to find the rank of a key in the subtree rooted at node x
    private int rank(Node x, int key) {
        if (x == null) {
            return 0; // No keys less than the parameter
        }

        int cmp = Integer.compare(key, x.key());
        if (cmp < 0) {
            return rank(x.left(), key);
        } else if (cmp > 0) {
            // Exclude the current node and the keys in the right subtree
            // Account for the number of keys in the left subtree and the current node's count
            return size(x.left()) + x.count() + rank(x.right(), key);
        } else {
            // Count only the keys in the left subtree
            return size(x.left());
        }
    }
    /***
     *return the key at the given rank
     ***/
    public int select(int rank) throws NoSuchRankException {
        if (rank < 0 || rank >= size()) {
            throw new NoSuchRankException();
        }
        return select(root, rank + 1).key(); // Add 1 to the rank to account for 0-based indexing
    }

    // Helper method to find the node with the given rank in the subtree rooted at node x
    private Node select(Node x, int rank) {
        if (x == null) {
            return null; // Base case: no keys in the subtree
        }

        int leftSize = size(x.left());
        if (rank <= leftSize) {
            return select(x.left(), rank); // Search in the left subtree
        } else if (rank == leftSize + x.count()) {
            return x; // Current node has the desired rank
        } else {
            return select(x.right(), rank - leftSize - x.count()); // Search in the right subtree
        }
    }

    /***
     *return the number of keys in [lo...hi]
     ***/
    public int size(int lo, int hi) {
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /***
     *return a String representation of the tree
     *level by level
     ***/
    public String toString() {
        if (isEmpty()) {
            return "Tree is empty";
        }

        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node current = null;
                try {
                    current = queue.dequeue();
                } catch (EmptyQueueException e) {
                    e.printStackTrace();
                }
                sb.append("|").append(current.toString()).append("| ");
                if (current.left() != null) {
                    queue.enqueue(current.left());
                }
                if (current.right() != null) {
                    queue.enqueue(current.right());
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    /* PRIVATE METHODS */

    /***
     *return the height of x
     *or -1 if x is null
     */
    private int height(Node x) {
	if(x == null)
	    return -1;
	return x.height();
    }

    /***
     *recursive helper method for insert
     */
    private Node put(int key, Node x) {
	if(key == x.key()) {
	    x.incCount();
	} else if (key > x.key()) {
	    if(x.right() == null) {
		x.setRight(new Node(key));
		size++;
	    } else {
		x.setRight(put(key, x.right()));
	    }
	} else {
	    if(x.left() == null) {
		x.setLeft(new Node(key));
		size++;
	    } else {
		x.setLeft(put(key, x.left()));
	    }
	}

	// Update subtree size
    x.setN(1 + size(x.left()) + size(x.right()));
	
    x = balance(x);
	x.setHeight(Math.max(height(x.left()), height(x.right())) + 1);
	
	return x;
    }

    /***
     *recursive method for getting Node 
     *with given key
     */
    private Node get(int key, Node x) {
	if(x == null)
	    return null;
	if(key == x.key())
	    return x;
	else if(key > x.key())
	    return get(key, x.right());
	return get(key, x.left());
    }

    /***
     *rotate left
     ***/
    private Node rotateLeft(Node h) {
	Node x = h.right();
	h.setRight(x.left());
	x.setLeft(h);
	x.setColor(h.color());
	h.setColor(Node.RED);
	x.setHeight(h.height());
	h.setHeight(1 + Math.max(height(h.left()), height(h.right())));
	return x;
    }

    /***
     *rotate right
     ***/
    private Node rotateRight(Node h) {
	Node x = h.left();
	h.setLeft(x.right());
	x.setRight(h);
	x.setColor(h.color());
	h.setColor(Node.RED);
	x.setHeight(h.height());
	h.setHeight(1 + Math.max(height(h.left()), height(h.right())));
	return x;
    }

    /***
     *color flip
     ***/
    private void colorFlip(Node h) {
	h.setColor(Node.RED);
	h.left().setColor(Node.BLACK);
	h.right().setColor(Node.BLACK);
	if(h == root)
	    h.setColor(Node.BLACK);
    }

    /***
     *balance
     ***/
    private Node balance(Node h) {
	if(h.right() != null && h.right().isRed() && h.right().right() != null && h.right().right().isRed()) {
	    h = rotateLeft(h);
	    colorFlip(h);
	}
	else if(h.left() != null && h.right() != null && h.left().isRed() && h.right().isRed())
	    colorFlip(h);
	else if(h.left() != null && h.left().isRed())
	    h = rotateRight(h);
	return h;
    }
}    
