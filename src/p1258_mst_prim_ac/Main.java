package p1258_mst_prim_ac;import java.util.ArrayList;import java.util.Scanner;public class Main {	/**	 * @param args	 */	public static void main(String[] args) {		Scanner scanner = new Scanner(System.in);		while (scanner.hasNext()) {			int N = scanner.nextInt();			int[][] edges = new int[N][N];			for (int i = 0; i < edges.length; i++) {				for (int j = 0; j < edges.length; j++) {					edges[i][j] = scanner.nextInt();				}			}//			print(edges);			mst(edges, N, 0);		}	}	static void print(int[][]edges){		for (int i = 0; i < edges.length; i++) {			for (int j = 0; j < edges.length; j++) {				System.out.println(edges[i][j] +" ");			}			System.out.println();		}	}		private static void mst(int[][] edges, int n, int u) {		MSTEdgeNode edgeNode = null;		MinHeap minHeap = new MinHeap(n * n);		boolean[] vmst = new boolean[n];		vmst[u] = true;		int count = 1;		int sum = 0;		do {			int v = getFirstNeighbor(edges, u, n);			while (v != -1) {				if (vmst[v] == false) {					edgeNode = new MSTEdgeNode(u, v, edges[u][v]);					minHeap.insert(edgeNode);				}				v = getNextNeighbor(edges, u, v, n);			}			while (minHeap.isEmpty() == false && count < n) {				edgeNode = minHeap.removeMin();				if (vmst[edgeNode.head] == false) {					sum += edgeNode.key;//					System.out.println(edgeNode.key);					u = edgeNode.head;					vmst[u] = true;					count++;					break;				}			}		} while (count < n);		System.out.println(sum);	}	static int getFirstNeighbor(int[][] edges, int v, int n) {		if (v != -1) {			for (int col = 0; col < n; col++) {				if (edges[v][col] > 0 && edges[v][col] < Integer.MAX_VALUE) {					return col;				}			}		}		return -1;	}	static int getNextNeighbor(int[][] edges, int v, int w, int n) {		if (v != -1 && w != -1) {			for (int col = w + 1; col < n; col++) {				if (edges[v][col] > 0 && edges[v][col] < Integer.MAX_VALUE) {					return col;				}			}		}		return -1;	}	static class MinHeap {				public MinHeap(int sz) {			heap = new ArrayList<MSTEdgeNode>();			currentSize = 0;		}		public int getAll() {			int all = 0;			for (MSTEdgeNode edgeNode : heap) {				all += edgeNode.key;			}//			System.out.println(heap);			return all;		}		boolean insert(MSTEdgeNode x) {			if (heap.contains(x) == false) {				heap.add(x);				siftUp(currentSize);				currentSize++;				return true;			} else {				return false;			}		}		MSTEdgeNode removeMin() {			MSTEdgeNode x = heap.get(0);			heap.set(0, heap.get(currentSize - 1));			heap.remove(currentSize-1);			currentSize--;			siftDown(0, currentSize - 1);			return x;		}		boolean isEmpty() {			return (currentSize == 0) ? true : false;		}		boolean isFull() {			return (currentSize == maxHeapSize);		}		void MakeEmpty() {			currentSize = 0;			heap = null;		}		ArrayList<MSTEdgeNode> heap;		int currentSize;		int maxHeapSize;		void siftDown(int start, int end) {			int i = start;			int j = 2 * i + 1;			MSTEdgeNode temp = heap.get(i);			while (j <= end) {				if (j < end && heap.get(j).key > heap.get(j + 1).key) {					j++;				}				if (temp.key <= heap.get(j).key)					break;				else {					heap.set(i, heap.get(j));					i = j;					j = 2 * j + 1;				}			}			heap.set(i, temp);		}		void siftUp(int start) {			int j = start;			int i = (j - 1) / 2;			MSTEdgeNode temp = heap.get(j);			while (j > 0) {				if (heap.get(i).key <= temp.key) {					break;				} else {					heap.set(j, heap.get(i));					j = i;					i = (i - 1) / 2;				}			}			heap.set(j, temp);		}	}	static class MSTEdgeNode {		int tail = -1;		int head = -1;		int key = 0;		public MSTEdgeNode(int t, int h, int k) {			tail = t;			head = h;			key = k;		}		public String toString() {			return "<" + head + "," + tail + "," + key + ">";		}		@Override		public boolean equals(Object arg0) {			if (arg0 != null && arg0 instanceof MSTEdgeNode) {				MSTEdgeNode edgeNode = (MSTEdgeNode) arg0;				return this.tail == edgeNode.tail && this.head == edgeNode.head						&& this.key == edgeNode.key;			}			return false;		}	}}