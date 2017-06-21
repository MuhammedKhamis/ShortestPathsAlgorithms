package classes;

public class BinaryHeap {

	private Vertex[] array;
	private int heapSize;


	public BinaryHeap(Vertex[] a) {
		// TODO Auto-generated constructor stub
		array = a;
		heapSize = a.length;
	}

	private int parent(int i) {
		return i / 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	private void minHeapify(Vertex[] a, int i) {
		setArray(a);
		int l = left(i);
		int r = right(i);
		int smallest;
		if (l < heapSize && array[l].getdistance() < array[i].getdistance()) {
			smallest = l;
		} else {
			smallest = i;
		}

		if (r < heapSize && array[r].getdistance() < array[smallest].getdistance()) {
			smallest = r;
		}
		if (smallest != i) {
			swap(i, smallest);
			minHeapify(array, smallest);
		}
	}

	private void swap(int i, int j) {
		Vertex temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public void buildHeap() {
		//heapSize = array.length;
		for (int i = array.length / 2; i >= 0; i--) {
			minHeapify(array, i);
		}
	}

	/*public int[] heapSort(int[] array) {
		this.buildHeap(array);
		for (int i = array.length - 1; i > 0; i--) {
			swap(i, 0);
			heapSize--;
			maxHeapify(this.array, 0);
		}
		return this.array;
	}*/

	public Vertex peek() {
		return array[0];
	}

	public Vertex extractMin() {
		if (heapSize < 0) {
			throw new RuntimeException();
		}
		Vertex min = array[0];
		array[0] = array[heapSize-1];
		heapSize--;
		minHeapify(this.array, 0);
		return min;
	}

	private void increaseKey(int i, Vertex key) {
		if (array == null) {
			return;
		}
		if (array[i].getdistance() > key.getdistance()) {
			throw new RuntimeException();
		}
		array[i] = key;
		while (i > 0 && array[parent(i)].getdistance() > array[i].getdistance()) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	public void insert(Vertex key) {
		if (heapSize + 1 >= array.length) {
			throw new RuntimeException("Can't insert any more elements.");
		}
		heapSize++;
		array[heapSize].setdistance(Integer.MIN_VALUE);
		increaseKey(heapSize, key);
	}

	private void setArray(Vertex[] a) {
		array = a;
	}
	
	public boolean isEmpty() {
	    return (heapSize == 0)? true : false;
	}

//	private void checkBuild() {
//		for (int i = 0; i < array.length; i++) {
//			int l = left(i);
//			int r = right(i);
//			if (l < array.length) {
//				if (array[i] < array[l]) {
//					printWord("Error in build");
//					return;
//				}
//			}
//			if (r < array.length) {
//				if (array[i] < array[r]) {
//					printWord("Error in build");
//					return;
//				}
//			}
//
//		}
//		printWord("built good");
//	}

//	private void checkSort() {
//		for (int i = 0; i < array.length - 1; i++) {
//			if (array[i] > array[i + 1]) {
//				this.printWord("Not sorted");
//				return;
//			}
//		}
//		this.printWord("good sort");
//	}
//
//	private void printWord(String s) {
//		System.out.println(s);
//	}

}
