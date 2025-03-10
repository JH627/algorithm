import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7469_K번째수 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int elementCount, queryCount;
	static int[] elements;
	static int[][] segTree;
	
	public static void main(String[] args) throws Exception {
		init();
		
		makeSegTree(1, elementCount, 1);
		
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int targetIndex = Integer.parseInt(st.nextToken());
			
			int number = getKthNumber(left, right, targetIndex);
			
			sb.append(number).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void makeSegTree(int start, int end, int node) {
		if (start == end) {
			segTree[node] = new int[]{elements[start]};
			return;
		}
		
		int mid = (start + end) / 2;
		makeSegTree(start, mid, node * 2);
		makeSegTree(mid + 1, end, node * 2 + 1);
		segTree[node] = mergeSort(segTree[node * 2], segTree[node * 2 + 1]);
	}
	
	static int getKthNumber(int leftIndex, int rightIndex, int targetIndex) {
		int l = (int) -1e9, r = (int) 1e9;
		while (l <= r) {
			int mid = (l + r) / 2;
			int count = getQuery(1, elementCount, 1, leftIndex, rightIndex, mid);
			
			if (count < targetIndex) {
				l = mid + 1;
			}
			else {
				r = mid - 1;
			}
		}
		
		return r;
	}
	
	// 범위에 맞는 것들 중에 K보다 작은 것들의 개수
	static int getQuery(int start, int end, int node, int left, int right, int targetNumber) {
		if (start > right || left > end) {
			return 0;
		}
		if (left <= start && end <= right) {
			return lowerBound(node, targetNumber);
		}
		
		
		int mid = (start + end) / 2;
		return getQuery(start, mid, node * 2, left, right, targetNumber) + 
				getQuery(mid + 1, end, node * 2 + 1, left, right, targetNumber);
	}
	
	static int lowerBound(int node, int target) {
		int l = 0, r = segTree[node].length;
		while (l < r) {
			int mid = (l + r) / 2;
			if (segTree[node][mid] < target) {
				l = mid + 1;
			}
			else {
				r = mid;
			}
		}
		
		return r;
	}
	
	static int[] mergeSort(int[] left, int[] right) {
		int leftSize = left.length;
		int rightSize = right.length;
		int sumLen = leftSize + rightSize;
		int[] mergedArray = new int[sumLen];
		
		int mergedIndex = 0;
		int leftIndex = 0;	int rightIndex = 0;
		while (leftIndex != leftSize || rightIndex != rightSize) {
			if (leftIndex == leftSize) {
				mergedArray[mergedIndex++] = right[rightIndex++];
				continue;
			}
			
			if (rightIndex == rightSize) {
				mergedArray[mergedIndex++] = left[leftIndex++];
				continue;
			}
			
			if (left[leftIndex] > right[rightIndex]) {
				mergedArray[mergedIndex++] = right[rightIndex++];
			}
			else {
				mergedArray[mergedIndex++] = left[leftIndex++];
			}	
		}
		
		return mergedArray;
	}
	
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		elementCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());
		
		elements = new int[elementCount + 1];
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 1; elementIndex < elementCount + 1; elementIndex++) {
			elements[elementIndex] = Integer.parseInt(st.nextToken());
		}
		
		int height = 32 - Integer.numberOfLeadingZeros(elementCount - 1);
		int size = 2 << height;
		segTree = new int[size][];
	}
}
