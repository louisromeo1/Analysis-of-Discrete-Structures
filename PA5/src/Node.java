public class Node {
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    private static int nodeCount;
    private int key;
    private int count;
    private Node left, right;
    private int height;//the height of this node
    private int N;//the size of the subtree rooted at this node
    private boolean color;

    public Node(int key) {
	this.key = key;
	this.N = 1;
	this.count = 1;
	this.height = 0;
	this.color = RED;
    }

    //these are used only for efficiency testing
    public static int nodeCount() {
	return nodeCount;
    }

    //these are used only for efficiency testing
    public static void resetNodeCount() {
	nodeCount = 0;
    }

    public int key() {
	return this.key;
    }

    public int count() {
	return this.count;
    }

    public Node left() {
	return this.left;
    }

    public Node right() {
	return this.right;
    }

    public void setLeft(Node left) {
	this.left = left;
    }

    public void setRight(Node right) {
	this.right = right;
    }

    public int height() {
	return this.height;
    }

    public void setHeight(int height) {
	this.height = height;
    }

    public int N() {
	return this.N;
    }

    public void setN(int N) {
	this.N = N;
    }

    public boolean isRed() {
	return this.color == Node.RED;
    }

    public void setColor(boolean color) {
	this.color = color;
    }

    public boolean color() {
	return this.color;
    }

    public void incCount() {
	this.count++;
    }

    public String toString() {
	return "(" + key + ", " + count + "): " + (this.color ? "RED" : "BLACK");
    }
}
