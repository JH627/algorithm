import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 입력을 받고 union 연산을 실행한다.
 * 2. 최초 그룹의 개수는 elementCount
 * 2-1. 만약 union 연산에 실패한 경우 -> 그룹이 합쳐지지 않음
 *      => 그룹개수 유지
 * 2-2. 만약 union 연산에 성공한 경우 -> 그룹이 합쳐짐
 *      => 그룹개수--
 *
 */
public class SWEA_7465_창용마을무리의개수 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int elementCount, queryCount;
	static int[] parent, rank;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			int groupCount = findGroupCount();
			
			sb.append("#").append(testCase).append(" ").append(groupCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int findGroupCount() throws IOException {
		int currentGroupCount = elementCount;
		
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			// 합쳐진 경우
			if (union(a, b)) {
				currentGroupCount--;
			}
		}
		
		return currentGroupCount;
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());
		
		parent = new int[elementCount];
		rank = new int[elementCount];
		
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			parent[elementIndex] = elementIndex;
		}
	}
	
	static int find(int element) {
		if (element == parent[element]) {
			return element;
		}
		
		return parent[element] = find(parent[element]);
	}
	
	static boolean union(int elementA, int elementB) {
		int aParent = find(elementA);
		int bParent = find(elementB);
		
		if (aParent == bParent) {
			return false;
		}

		if (rank[aParent] > rank[bParent]) {
			parent[bParent] = aParent;
		}
		else if (rank[aParent] < rank[bParent]) {
			parent[aParent] = bParent;
		}
		else {
			parent[aParent] = bParent;
			rank[bParent]++;
		}
		return true;
	}
}
