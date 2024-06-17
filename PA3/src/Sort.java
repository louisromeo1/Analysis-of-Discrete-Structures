/*
 * Louis Romeo
 * CSC 345 PA3
 * Purpose: Implementation of different sorting algorithms 
 * 			using a given Array and LinkedList object. Includes
 * 			mergeSort, insertionSort, selectionSort and bubbleSort.
 */
public class Sort {

    public static void iterativeMerge(Array A) {
        int[] temp = new int[A.length()];
        mergeSort(A, temp, 0, A.length() - 1);
    }

    private static void mergeSort(Array A, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(A, temp, left, mid);
            mergeSort(A, temp, mid + 1, right);
            merge(A, temp, left, mid, right);
        }
    }

    private static void merge(Array A, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = A.getVal(i);
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                A.setVal(k, temp[i]);
                i++;
            } else {
                A.setVal(k, temp[j]);
                j++;
            }
            k++;
        }

        while (i <= mid) {
            A.setVal(k, temp[i]);
            k++;
            i++;
        }
    }

    public static void insertMerge(Array A, int size) {
        int[] temp = new int[A.length()];
        for (int i = 0; i < A.length(); i += size) {
            int left = i;
            int right = Math.min(i + size - 1, A.length() - 1);
            insertionSort(A, left, right);
        }
        mergeSort(A, temp, 0, A.length() - 1);
    }

    private static void insertionSort(Array A, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = A.getVal(i);
            int j = i - 1;
            while (j >= left && A.getVal(j) > key) {
                A.setVal(j + 1, A.getVal(j));
                j--;
            }
            A.setVal(j + 1, key);
        }
    }
    public static void threeWayMerge(Array A) {
        int[] temp = new int[A.length()];
        threeWayMergeSort(A, temp, 0, A.length() - 1);
    }

    private static void threeWayMergeSort(Array A, int[] temp, int left, int right) {
        if (left < right) {
            if (right - left <= 1) {
                if (A.getVal(right) < A.getVal(left)) {
                    A.swap(left, right);
                }
            } else {
                int third = (right - left) / 3;
                int mid1 = left + third;
                int mid2 = left + 2 * third;
                threeWayMergeSort(A, temp, left, mid1);
                threeWayMergeSort(A, temp, mid1 + 1, mid2);
                threeWayMergeSort(A, temp, mid2 + 1, right);
                threeWayMerge(A, temp, left, mid1, mid2, right);
            }
        }
    }

    private static void threeWayMerge(Array A, int[] temp, int left, int mid1, int mid2, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = A.getVal(i);
        }

        int i = left;
        int j = mid1 + 1;
        int k = mid2 + 1;
        int l = left;

        while (i <= mid1 || j <= mid2 || k <= right) {
            int minVal = Integer.MAX_VALUE;
            if (i <= mid1 && temp[i] <= minVal) {
                minVal = temp[i];
            }
            if (j <= mid2 && temp[j] <= minVal) {
                minVal = temp[j];
            }
            if (k <= right && temp[k] <= minVal) {
                minVal = temp[k];
            }
            if (i <= mid1 && temp[i] == minVal) {
                A.setVal(l, temp[i]);
                i++;
            } else if (j <= mid2 && temp[j] == minVal) {
                A.setVal(l, temp[j]);
                j++;
            } else {
                A.setVal(l, temp[k]);
                k++;
            }
            l++;
        }
    }
    // Sort Array A using a five-way Quicksort
    // (done recursively within helper function)
    public static void fiveWayQuick(Array A) {
        fiveWayQuickSort(A, 0, A.length() - 1);
    }

    // Helper method for recursion on fiveWayQuick
    private static void fiveWayQuickSort(Array A, int low, int high) {
        if (low < high) {
            int[] pivots = partition(A, low, high);

            fiveWayQuickSort(A, low, pivots[0] - 1);
            fiveWayQuickSort(A, pivots[0] + 1, pivots[1] - 1);
            fiveWayQuickSort(A, pivots[1] + 1, high);
        }
    }
    
    // Helper method to partition the array with two indexes.
    private static int[] partition(Array A, int low, int high) {
        
    	int pivot1 = A.getVal(low);
        int pivot2 = A.getVal(high);

        if (pivot1 > pivot2) {
            A.swap(low, high); // swap places
            pivot1 = A.getVal(low);
            pivot2 = A.getVal(high);
        }

        int i = low + 1;
        int lt = low + 1;
        int gt = high - 1;

        while (i <= gt) {
            
        	if (A.getVal(i) < pivot1) {
                A.swap(i, lt);
                i++;
                lt++;
            } else if (A.getVal(i) >= pivot1 && A.getVal(i) <= pivot2) {
                i++;
            } else {
                A.swap(i, gt);
                gt--;
            }
        }

        A.swap(low, --lt);
        A.swap(high, ++gt);

        return new int[]{lt, gt};
    }
    public static void locSelect(Array A, int d) {
        for (int i = 0; i < A.length(); i++) {
            int minIndex = i;
            int minVal = A.getVal(i);

            // Find the minimum element within the range of d positions
            for (int j = i + 1; j <= Math.min(i + d, A.length() - 1); j++) {
                if (A.getVal(j) < minVal) {
                    minVal = A.getVal(j);
                    minIndex = j;
                }
            }

            // Swap the minimum element with the current element
            A.swap(i, minIndex);
        }
    }
    
    public static void locHeap(Array A, int d) {
        // Build a max-heap using only the first d + 1 elements
        for (int i = (A.length() - 1) / 2; i >= 0; i--) {
            maxHeapify(A, i, Math.min(A.length(), d + 1));
        }

        // Perform heap sort with locality awareness
        for (int i = A.length() - 1; i > 0; i--) {
            A.swap(0, i);
            maxHeapify(A, 0, Math.min(i, d + 1));
        }
    }

    private static void maxHeapify(Array A, int root, int size) {
        int maxIndex = root;
        int leftChild = 3 * root + 1; // Calculate the left child index
        int rightChild = 3 * root + 2; // Calculate the right child index

        // Find the maximum element among the root and its children within the locality constraint
        for (int i = leftChild; i <= Math.min(leftChild + 2 * (size - 1), A.length() - 1); i++) {
            if (A.getVal(i) > A.getVal(maxIndex)) {
                maxIndex = i;
            }
        }

        // If the maximum is not the root, swap and recursively heapify the affected subtree
        if (maxIndex != root) {
            A.swap(root, maxIndex);
            maxHeapify(A, maxIndex, size);
        }
    }
    
    public static LinkedList mergeSort(LinkedList list) {
        // Base case: if the list is empty or has only one node, it is already sorted
        if (list == null || list.head() == null || list.head().next() == null) {
            return list;
        }
        
        // Split the list into two halves
        LinkedList[] halves = splitList(list);
        LinkedList leftHalf = halves[0];
        LinkedList rightHalf = halves[1];
        
        // Recursively apply merge sort to each half
        leftHalf = mergeSort(leftHalf);
        rightHalf = mergeSort(rightHalf);
        
        // Merge the sorted halves
        return merge(leftHalf, rightHalf);
    }
    
    // Split the linked list into two halves
    private static LinkedList[] splitList(LinkedList list) {
        Node slow = list.head();
        Node fast = list.head();
        Node prev = null;
        
        // Use slow and fast pointers to find the middle of the list
        while (fast != null && fast.next() != null) {
            prev = slow;
            slow = slow.next();
            fast = fast.next().next();
        }
        
        // Disconnect the two halves
        if (prev != null) {
            prev.setNext(null);
        }
        
        // Create new linked lists for the two halves
        LinkedList leftHalf = new LinkedList(list.head());
        LinkedList rightHalf = new LinkedList(slow);
        
        return new LinkedList[]{leftHalf, rightHalf};
    }
    
    // Merge two sorted linked lists into one sorted linked list
    private static LinkedList merge(LinkedList list1, LinkedList list2) {
        Node dummy = new Node(0); // Dummy node to simplify the merge process
        Node current = dummy;
        
        Node p1 = list1.head();
        Node p2 = list2.head();
        
        // Merge the two lists by comparing values of nodes
        while (p1 != null && p2 != null) {
            if (p1.val() < p2.val()) {
                current.setNext(p1);
                p1 = p1.next();
            } else {
                current.setNext(p2);
                p2 = p2.next();
            }
            current = current.next();
        }
        
        // Attach remaining nodes
        if (p1 != null) {
            current.setNext(p1);
        } else {
            current.setNext(p2);
        }
        
        // Skip dummy node and return the merged list
        return new LinkedList(dummy.next());
    }
    
 // Sort the list using a recursive version of 2-way Quicksort
    public static LinkedList quickSort(LinkedList list) {
        // Base case: empty list or single element list is already sorted
        if (list == null || list.isEmpty() || list.head().next() == null) {
            return list;
        }

        // Chooses a pivot (head of list)
        Node pivot = list.head();

        // Partition the list around the pivot
        LinkedList[] partitions = partition(list, pivot);

        // Recursively sort the partitions
        LinkedList lessThanPivot = quickSort(partitions[0]);
        LinkedList greaterThanPivot = quickSort(partitions[1]);

        // Concatenate the sorted partitions with the pivot in between
        pivot.setNext(greaterThanPivot.head());
        lessThanPivot.add(pivot.val());

        return lessThanPivot;
    }

    // Helper method to partition the list around a pivot
    private static LinkedList[] partition(LinkedList list, Node pivot) {
        LinkedList lessThanPivot = new LinkedList();
        LinkedList greaterThanPivot = new LinkedList();
        Node current = list.head().next(); // Skip over the pivot node

        while (current != null) {
            if (current.val() <= pivot.val()) {
                lessThanPivot.add(current.val());
            } else {
                greaterThanPivot.add(current.val());
            }
            current = current.next();
        }

        return new LinkedList[] { lessThanPivot, greaterThanPivot };
    }
    
 // Sort the list using a recursive version of Insertion Sort
    public static LinkedList insertionSort(LinkedList list) {
        
    	if (list == null || list.isEmpty() || list.head().next() == null) {
            return list; // Base case
        }

        Node sorted = null;
        Node current = list.head();

        while (current != null) {
            
        	Node next = current.next();
            sorted = insert(sorted, current);
            current = next;
        }

        list = new LinkedList(sorted);
        return list;
    }

    // Helper function utilized to insert a new node into
    // the LinkedList
    private static Node insert(Node sorted, Node newNode) {
        
    	if (sorted == null || sorted.val() >= newNode.val()) {
            newNode.setNext(sorted);
            return newNode;
        }

        sorted.setNext(insert(sorted.next(), newNode));
        return sorted;
    }
    
 // Sort the list using a recursive version of Bubble Sort
    public static LinkedList bubbleSort(LinkedList list) {
        if (list == null || list.isEmpty()) {
            return list;
        }

        boolean swapped = false; // flag
        Node prev = null;
        Node current = list.head();
        Node nextNode = current.next();

        while (nextNode != null) {
            if (current.val() > nextNode.val()) {
                swap(prev, current, nextNode);
                swapped = true;
            }
            prev = current;
            current = current.next();
            nextNode = nextNode.next();
        }

        // If no swaps occurred, the list is already sorted
        if (!swapped) {
            return list;
        }

        // Recurse after checking for the end of the list
        return bubbleSort(list);
    }

    // Helper method used to swap Nodes in LinkedList
    private static void swap(Node prev, Node node1, Node node2) {
        if (prev != null) {
            prev.setNext(node2);
        } else {
            node1.setNext(node2.next());
        }
        Node temp = node2.next();
        node2.setNext(node1);
        node1.setNext(temp);
    }
}
