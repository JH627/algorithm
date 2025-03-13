import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1517_버블소트 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int elementCount;
	static int[] elements;
	static long[] segTree;
	
	public static void main(String[] args) throws Exception {
		init();
		
		int maxRank = compressPosition(elements);
		segTree = new long[maxRank * 4];
		
		long swapCount = 0;
		for (int index = 1; index < elementCount + 1; index++) {
			long targetValue = elements[index];
			updateQuery(1, maxRank, 1, targetValue);
			swapCount += getQuery(1, maxRank, 1, targetValue + 1, maxRank);
		}
		
		System.out.print(swapCount);
	}
	
	static long getQuery(int start, int end, int node, long left, long right) {
		if (left > end || start > right) {
			return 0;
		}
		
		if (left <= start && end <= right) {
			return segTree[node];
		}
		
		int mid = (start + end) / 2;
		return getQuery(start, mid, node * 2, left, right) + 
				getQuery(mid + 1, end, node * 2 + 1, left, right);
	}
	
	static void updateQuery(int start, int end, int node, long target) {
		if (start == end) {
			segTree[node]++;
			return;
		}
		
		int mid = (start + end) / 2;
		segTree[node]++;
		if (target <= mid) {
			updateQuery(start, mid, node * 2, target);
		}
		else {
			updateQuery(mid + 1, end, node * 2 + 1, target);			
		}
	}
	
	// 좌표 압축
	static int compressPosition(int[] originArray) {
		Map<Integer, Integer> orderedPosition = new HashMap<>();
		int[] sortedArray = originArray.clone();
		Arrays.sort(sortedArray);
		
		int rank = 1;
		for (int index = 1; index < elementCount + 1; index++) {
			if (!orderedPosition.containsKey(sortedArray[index])) {
				orderedPosition.put(sortedArray[index], rank++);
			}
		}
		
		for (int index = 1; index < elementCount + 1; index++) {
			originArray[index] = orderedPosition.get(originArray[index]);
		}
		
		return rank;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount + 1];
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 1; elementIndex < elementCount + 1; elementIndex++) {
			elements[elementIndex] = Integer.parseInt(st.nextToken());
		}
	}
}
