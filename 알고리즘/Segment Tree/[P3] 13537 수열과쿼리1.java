import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13537_수열과쿼리1 {

	static BufferedReader br;
	
	static int elementCount, queryCount;
	static long[] elements;
	static List<Long>[] segTree;
	
	public static void main(String[] args) throws Exception {
		init();
		
		makeSegTree(1, elementCount, 1);
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			long limit = Long.parseLong(st.nextToken());
			
			sb.append(getQuery(1, elementCount, 1, left, right, limit)).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void makeSegTree(int start, int end, int node) {
		if (start == end) {
			segTree[node].add(elements[start]);
			return;
		}
		
		int mid = (start + end) / 2;
		makeSegTree(start, mid, node * 2);
		makeSegTree(mid + 1, end, node * 2 + 1);
		merge(node, node * 2, node * 2 + 1);
	}
	
	static void merge(int node, int left, int right) {
		int leftIndex = 0; int leftSize = segTree[left].size();
		int rightIndex = 0; int rightSize = segTree[right].size();
		
		while (leftIndex != leftSize || rightIndex != rightSize) {
			if (leftIndex == leftSize) {
				segTree[node].add(segTree[right].get(rightIndex++));
				continue;
			} 
			if (rightIndex == rightSize) {
				segTree[node].add(segTree[left].get(leftIndex++));
				continue;
			}
			
			if (segTree[left].get(leftIndex) < segTree[right].get(rightIndex)) {
				segTree[node].add(segTree[left].get(leftIndex++));
			}
			else {
				segTree[node].add(segTree[right].get(rightIndex++));
			}
		}
	}
	
	static void update(int start, int end, int node, int target) {
		if (start > target || end < target) {
			return;
		}
		
		segTree[node].add(elements[target]);
		if (start == end) {
			return;
		}
		
		int mid = (start + end) / 2;
		update(start, mid, node * 2, target);
		update(mid + 1, end, node * 2 + 1, target);
	}
	
	static int getQuery(int start, int end, int node, int left, int right, long limit) {
		if (left > end || start > right) {
			return 0;
		}
		
		if (left <= start && end <= right) {
			return segTree[node].size() - upperBound(node, limit);
		}
		
		int mid = (start + end) / 2;
		return getQuery(start, mid, node * 2, left, right, limit)
				+ getQuery(mid + 1, end, node * 2 + 1, left, right, limit);
	}
	
	static int upperBound(int node, long limit) {
		int size = segTree[node].size();
		
		int l = 0;
		int r = size;
		while (l < r) {
			int mid = (l + r) / 2;
			if (segTree[node].get(mid) <= limit) {
				l = mid + 1;
			}
			else {
				r = mid;
			}			
		}
		
		return r;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		elementCount = Integer.parseInt(br.readLine());
		elements = new long[elementCount + 1];
		segTree = new ArrayList[4 * elementCount];
		for (int index = 0; index < 4 * elementCount; index++) {
			segTree[index] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int elementIndex = 1; elementIndex < elementCount + 1; elementIndex++) {
			elements[elementIndex] = Long.parseLong(st.nextToken());
		}
		
		queryCount = Integer.parseInt(br.readLine());
	}
	
}
