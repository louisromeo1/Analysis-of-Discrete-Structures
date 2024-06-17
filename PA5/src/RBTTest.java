public class RBTTest {
    private static int[] keys = new int[]{4,8,12,10,2,0,6,14,16,20};
    private static int[] insertCounts = new int[]{0,11,31,29,29,35,41,68,37,61};
    private static int[] depths = new int[]{2,0,1,2,1,2,3,3,2,3};
    private static int[] depthCounts = new int[]{2,0,1,2,1,2,3,3,2,3};
    private static int[] sizes = new int[]{2,10,5,1,4,1,1,1,3,1};
    private static int[] sizeCounts = new int[]{2,0,1,2,1,2,3,3,2,3};
    private static int[] floorKeys = new int[]{0,1,3,5,7,9,11,15,19,21};
    private static int[] floors = new int[] {0,0,2,4,6,8,10,14,16,20};
    private static int[] floorCounts = new int[]{2,3,3,4,4,3,3,4,4,4};
    private static int[] ceilKeys = new int[]{0,1,3,5,7,9,11,13,15,19};
    private static int[] ceils = new int[]{0,2,4,6,8,10,12,14,16,20};
    private static int[] ceilCounts = new int[]{2,3,3,4,4,3,3,4,4,4};
    private static int[] ranks = new int[]{2,4,6,5,1,0,3,7,8,9};
    private static int[] rankCounts = new int[]{6,1,4,6,3,5,9,9,7,10};
    private static int[] selectArgs = new int[]{0,1,2,3,4,5,6,7,8,9};
    private static int[] selectKeys = new int[]{0,2,4,6,8,10,12,14,16,20};
    private static int[] selectCounts = new int[]{7,4,6,8,1,6,3,8,5,7};

    private static RBT tree;

    public static void main(String[] args) {
	tree = new RBT();
	testPut();
	testDepth();
	testSize();
	testMin();
	testMax();
	testFloor();
	testCeil();
	testRank();
	testSelect();
	testSizeLH();
    }

    private static void testPut() {
	for(int i = 0; i < keys.length; i++) {
	    tree.put(keys[i]);
	    checkCount(insertCounts[i]);
	}
    }

    private static void testDepth() {
	for(int i = 0; i < keys.length; i++) {
	    int d = tree.depth(keys[i]);
	    if(d != depths[i]) {
		System.out.println("The depth of " + keys[i] + " is not correct.");
		System.out.println("Expected: " + depths[i]);
		System.out.println("Actual: " + d);
	    }
	    checkCount(depthCounts[i]);
	}
    }

    private static void testSize() {
	for(int i = 0; i < keys.length; i++) {
	    int act = tree.size(keys[i]);
	    if(act != sizes[i]) {
		System.out.println("The size of the subtree at " + keys[i] + " is not correct.");
		System.out.println("Expected: " + sizes[i]);
		System.out.println("Actual: " + act);
	    }
	    checkCount(sizeCounts[i]);
	}
    }

    private static void testMin() {
	int min = Integer.MIN_VALUE;
	try {
	    min = tree.min();
	} catch (EmptyTreeException e) {
	    e.printStackTrace();
	}
	if(min != 0) {
	    System.out.println("The min key is not correct.");
	    System.out.println("Expected: 0");
	    System.out.println("Actual: " + min);
	}
	checkCount(3);
    }

    private static void testMax() {
	int max = Integer.MAX_VALUE;
	try {
	    max = tree.max();
	} catch (EmptyTreeException e) {
	    e.printStackTrace();
	}
	if(max != 20) {
	    System.out.println("The max key is not correct.");
	    System.out.println("Expected: 20");
	    System.out.println("Actual: " + max);
	}
	checkCount(4);
    }

    private static void testFloor() {
	for(int i = 0; i < keys.length; i++) {
	    try {
		int act = tree.floor(floorKeys[i]);
		if(act != floors[i]) {
		    System.out.println("The floor of " + floorKeys[i] + " is not correct.");
		    System.out.println("Expected: " + floors[i]);
		    System.out.println("Actual: " + act);
		}
		checkCount(floorCounts[i]);
	    } catch (KeyDoesNotExistException e) {
		e.printStackTrace();
	    }
	}
    }

    private static void testCeil() {
	for(int i = 0; i < keys.length; i++) {
	    try {
		int act = tree.ceil(ceilKeys[i]);
		if(act != ceils[i]) {
		    System.out.println("The ceiling of " + ceilKeys[i] + " is not correct.");
		    System.out.println("Expected: " + ceils[i]);
		    System.out.println("Actual: " + act);
		}
		checkCount(ceilCounts[i]);
	    } catch (KeyDoesNotExistException e) {
		e.printStackTrace();
	    }
	}
    }

    private static void testRank() {
	for(int i = 0; i < keys.length; i++) {
	    int act = tree.rank(keys[i]);
	    if(act != ranks[i]) {
		System.out.println("The rank of " + keys[i] + " is not correct.");
		System.out.println("Expected: " + ranks[i]);
		System.out.println("Actual: " + act);
	    }
	    checkCount(rankCounts[i]);
	}
    }

    private static void testSelect() {
	for(int i = 0; i < keys.length; i++) {
	    try {
		int act = tree.select(selectArgs[i]);
		if(act != selectKeys[i]) {
		    System.out.println("The key selected at rank " + selectArgs[i] + " is not correct.");
		    System.out.println("Expected: " + selectKeys[i]);
		    System.out.println("Actual: " + act);
		}
		checkCount(selectCounts[i]);
	    } catch (NoSuchRankException e) {
		e.printStackTrace();
	    }
	}
    }

    private static void testSizeLH() {
	int act = tree.size(2,16);
	if(act != 8) {
	    System.out.println("The number of keys from 2 to 16 is not correct.");
	    System.out.println("Expected: 8");
	    System.out.println("Actual: " + act);
	}
	checkCount(10);
    }

    private static void checkCount(int exp) {
	if(Node.nodeCount() > exp) {
	    System.out.println("Node count looks high.");
	    System.out.println("Expected: " + exp);
	    System.out.println("Actual: " + Node.nodeCount());
	}
	Node.resetNodeCount();
    }
}
