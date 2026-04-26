import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16975_수열과쿼리21 {

	static BufferedReader br;
	
	static long[] elements;
	static long[] segTree, lazy;
	static int elementCount;
	static int queryCount;
	
	public static void main(String[] args) throws Exception  {
		init();
		
		makeSegTree(1, elementCount, 1);
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int operation = Integer.parseInt(st.nextToken());
			
			switch (operation) {
				case 1:
					int left = Integer.parseInt(st.nextToken());
					int right = Integer.parseInt(st.nextToken());
					long delta = Long.parseLong(st.nextToken());
					
					updateQuery(1, elementCount, 1, left, right, delta);
					break;
				case 2:
					int target = Integer.parseInt(st.nextToken());
					sb.append(getQuery(1, elementCount, 1, target)).append("\n");
					break;
			}	
		}

		System.out.print(sb);
	}
	
	static void makeSegTree(int start, int end, int node) {
		if (start == end) {
			segTree[node] = elements[start];
			return;
		}
		
		int mid = (start + end) / 2;
		makeSegTree(start, mid, node * 2);
		makeSegTree(mid + 1, end, node * 2 + 1);
		
		segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
	}
	
	static void updateQuery(int start, int end, int node, int left, int right, long delta) {
		lazyUpdate(start, end, node);
		
		if (start > right || left > end) {
			return;
		}
		
		if (left <= start && end <= right) {
			segTree[node] += (end - start + 1) * delta;
			if (start != end) {
				lazy[node * 2] += delta;
				lazy[node * 2 + 1] += delta;
			}
			return;
		}
		
		int mid = (start + end) / 2;
		updateQuery(start, mid, node * 2, left, right, delta);
		updateQuery(mid + 1, end, node * 2 + 1, left, right, delta);
	}
	
	static long getQuery(int start, int end, int node, int target) {
		lazyUpdate(start, end, node);
		
		if (start == end) {
			return segTree[node];
		}
		
		int mid = (start + end) / 2;
		if (mid >= target) {
			return getQuery(start, mid, node * 2, target);
		}
		if (mid < target) {
			return getQuery(mid + 1, end, node * 2 + 1, target); 
		}
		
		return 0;
	}

	static void lazyUpdate(int start, int end, int node) {
		if (lazy[node] == 0) {
			return;
		}
		
		segTree[node] += (end - start + 1) * lazy[node];
		
		if (start == end) {
			lazy[node] = 0;
			return;
		}
		
		lazy[node * 2] += lazy[node];
		lazy[node * 2 + 1] += lazy[node];

		lazy[node] = 0;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		elementCount = Integer.parseInt(br.readLine());
		elements = new long[elementCount + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int elementIndex = 1; elementIndex < elementCount + 1; elementIndex++) {
			elements[elementIndex] = Long.parseLong(st.nextToken());
		}
		
		segTree = new long[4 * elementCount];
		lazy = new long[4 * elementCount];
		
		queryCount = Integer.parseInt(br.readLine());
	}
}
