import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 초기화
 * 1-1. 각 노드의 부모는 자기 자신으로 초기화하고, rank는 0으로 초기화
 * 2. 0 입력이 들어온경우
 * 2-1. 두 노드를 union
 * 2-1-1. 만약 find를 통해 찾은 두 노드의 부모가 같다면 합집합 연산을 수행하지 않음
 * 2-1-2. 두 노드의 부모가 다르다면 rank가 낮은 쪽이 rank가 큰 쪽에 합해짐
 * 3. 1 입력이 들어온 경우
 * 3-1. find연산
 * 3-1-1. parent가 자기자신일 때까지 재귀
 * 3-1-2. 자기자신일 경우 값을 리턴 (루트노드)
 *
 */
public class SWEA_3289_서로소집합 {
	
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
			
			sb.append("#").append(testCase).append(" ");
			
			useQuery();
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void useQuery() throws IOException {
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int operation = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			switch (operation) {
				case 0:
					union(a, b);
					break;
				case 1:
					sb.append(checkIfSameSet(a, b));
					break;
			}
		}
	}
	
	static int checkIfSameSet(int elementA, int elementB) {
		if (elementA == elementB) {
			return 1;
		}
		return (find(elementA) == find(elementB)) ? 1 : 0;
	}
	
	static int find(int element) {
		if (element == parent[element]) {
			return element;
		}
		
		return parent[element] = find(parent[element]);
	}
	
	static void union(int elementA, int elementB) {
		int aParent = find(elementA);
		int bParent = find(elementB);
		
		if (aParent == bParent) {
			return;
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
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());
		
		parent = new int[elementCount];
		rank = new int[elementCount];
		
		for (int index = 0; index < elementCount; index++) {
			parent[index] = index;
		}
	}
}
