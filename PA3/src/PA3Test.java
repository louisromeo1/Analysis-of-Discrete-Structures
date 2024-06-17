public class PA3Test {
    private static Array sorted;
    private static Array shuffled;
    private static LinkedList sortedList;
    private static LinkedList shuffledList;
    
    public static void main(String[] args) {
	int score = 0;
	score += testIterativeMerge();
	score += testInsertMerge();
	score += test3WayMerge();
	score += test5WayQuick();
	score += testLocSelect();
	score += testLocHeap();
	score += testLLMerge();
	score += testLLQuick();
	score += testLLInsertion();
	score += testLLBubble();
	System.out.println("Expected Score: " + score);
    }

    private static int testIterativeMerge() {
	print("testing iterative mergesort");
	int score = 0;
	setUpArrays(50, 50);
	Sort.iterativeMerge(shuffled);
	if(areEqual(shuffled, sorted))
	    score += 3;
	setUpArrays(250, 250);
        Sort.iterativeMerge(shuffled);
	if(areEqual(shuffled, sorted))
	    score += 3;
	return score;
    }

    private static int testInsertMerge() {
	print("testing insert-mergesort");
	int score = 0;
	setUpArrays(50, 50);
	Sort.insertMerge(shuffled, 9);
	if(areEqual(shuffled, sorted))
	    score += 3;
	setUpArrays(250, 250);
        Sort.insertMerge(shuffled, 16);
	if(areEqual(shuffled, sorted))
	score += 3;
	return score;
    }

    private static int test3WayMerge() {
	print("testing three-way mergesort");
	int score = 0;
	setUpArrays(50, 50);
	Sort.threeWayMerge(shuffled);
	if(areEqual(shuffled, sorted))
	    score += 3;
	setUpArrays(250, 250);
        Sort.threeWayMerge(shuffled);
	if(areEqual(shuffled, sorted))
	    score += 3;
	return score;
    }

    private static int test5WayQuick() {
	print("testing five-way quicksort");
	int score = 0;
	setUpArrays(50, 50);
	Sort.fiveWayQuick(shuffled);
	if(areEqual(shuffled, sorted))
	    score += 3;
	setUpArrays(250, 250);
        Sort.fiveWayQuick(shuffled);
	if(areEqual(shuffled, sorted))
	    score += 3;
	return score;
    }

    private static int testLocSelect() {
	print("testing locality-aware selection sort");
	int score = 0;
	setUpArrays(50, 5);
	Sort.locSelect(shuffled, 5);
	if(areEqual(shuffled, sorted))
	    score += 3;
	setUpArrays(250, 10);
        Sort.locSelect(shuffled, 10);
	if(areEqual(shuffled, sorted))
	    score += 3;
	return score;
    }

    private static int testLocHeap() {
	print("testing locality-aware heapsort");
	int score = 0;
	setUpArrays(50, 7);
	Sort.locHeap(shuffled, 7);
	if(areEqual(shuffled, sorted))
	    score += 3;
	setUpArrays(250, 9);
        Sort.locHeap(shuffled, 9);
	if(areEqual(shuffled, sorted))
	    score += 3;
	return score;
    }

    private static int testLLMerge() {
	print("testing mergesort for linked lists");
	int score = 0;
	setUpLists(50);
	shuffledList = Sort.mergeSort(shuffledList);
	if(areEqual(shuffledList, sortedList))
	    score += 3;
	setUpLists(250);
	shuffledList = Sort.mergeSort(shuffledList);
	if(areEqual(shuffledList, sortedList))
	score += 3;
	return score;
    }

    private static int testLLQuick() {
	print("testing quicksort for linked lists");
	int score = 0;
	setUpLists(50);
	shuffledList = Sort.quickSort(shuffledList);
	if(areEqual(shuffledList, sortedList))
	    score += 3;
	setUpLists(250);
	shuffledList = Sort.quickSort(shuffledList);
	if(areEqual(shuffledList, sortedList))
	score += 3;
	return score;
    }

    private static int testLLInsertion() {
	print("testing insertion sort for linked lists");
	int score = 0;
	setUpLists(50);
	shuffledList = Sort.insertionSort(shuffledList);
	if(areEqual(shuffledList, sortedList))
	    score += 3;
	setUpLists(250);
	shuffledList = Sort.insertionSort(shuffledList);
	if(areEqual(shuffledList, sortedList))
	    score += 3;
	return score;
    }

    private static int testLLBubble() {
	print("testing bubblesort for linked lists");
	int score = 0;
	setUpLists(50);
	shuffledList = Sort.bubbleSort(shuffledList);
	if(areEqual(shuffledList, sortedList))
	    score += 3;
	setUpLists(250);
	shuffledList = Sort.bubbleSort(shuffledList);
	if(areEqual(shuffledList, sortedList))
	    score += 3;
	return score;
    }

    private static void setUpArrays(int size, int d) {
	int[] s = ArrayGen.genSortAsc(size);
	int[] u = new int[size];
	for(int i = 0; i < size; i++) {
	    u[i] = s[i];
	}
	ArrayGen.shuffle(u, d);
	sorted = new Array(s);
	shuffled = new Array(u);
    }

    private static boolean areEqual(Array act, Array exp) {
	if(act.length() != exp.length()) {
	    print("failed");
	    return false;
	}
	    
	for(int i = 0; i < act.length(); i++) {
	    if(act.getVal(i) != exp.getVal(i)) {
		print("failed");
		return false;
	    }
	}
	print("passed");
	return true;
    }

    private static void setUpLists(int size) {
	sortedList = new LinkedList();
	shuffledList = new LinkedList();
	int[] s = ArrayGen.genSortDesc(size);
	for(int i = 0; i < s.length; i++) {
	    sortedList.add(s[i]);
	}
	int[] u = new int[size];
	for(int i = 0; i < size; i++) {
	    u[i] = s[i];
	}
	ArrayGen.shuffle(u, size);
	for(int i = 0; i < u.length; i++) {
	    shuffledList.add(u[i]);
	}
    }

    private static boolean areEqual(LinkedList act, LinkedList exp) {
	Node cur1 = act.head();
	Node cur2 = exp.head();
	if(cur1 == null && cur2 != null) {
	    print("failed");
	    return false;
	}
	if(cur1 != null && cur2 == null) {
	    print("failed");
	    return false;
	}
	while(cur1 != null && cur2 != null) {
	    if(cur1.val() != cur2.val()) {
		print("failed");
		return false;
	    }
	    cur1 = cur1.next();
	    cur2 = cur2.next();
	}
	print("passed");
	return true;
    }

    private static void print(String msg) {
	System.out.println(msg);
    }
}
