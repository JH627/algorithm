import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 연속된 5개를 찾자 
 * 
 * 1. 모든 노드에서 DFS탐색을 한번씩 시도한다.
 * 1-1. 만약 다음 노드가 방문하지 않은 노드라면
 * 1-1-1. 방문처리를 하고 Depth + 1한 상태로 다음 노드를 탐색한다.
 * 1-1-2. 방문후에는 방문처리를 제거한다.
 * 2. 만약 depth가 5인 탐색이 한번이라도 발견된다면 탐색을 종료한다.
 *
 */
public class BOJ_13023_ABCDE {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int elementCount, edgeCount;
	static boolean[] visited;
	static boolean possible;
	
	static ArrayList<ArrayList<Integer>> friend;
	
	public static void main(String[] args) throws Exception {
		init();
		
		possible = false;
		for (int elementIndex = 0; elementIndex < elementCount && !possible; elementIndex++) {
			visited[elementIndex] = true;
			findMaxLength(elementIndex, 1);
			visited[elementIndex] = false;
		}
		
		System.out.print(possible ? 1 : 0);
	}
	
	static void findMaxLength(int now, int depth) {
		for (int next : friend.get(now)) {
			if (possible) {
				return;
			}
			
			if (!visited[next]) {
				// 5개 연속인 경우를 발견
				if (depth == 4) {
					possible = true;
					continue;
				}
				// 방문처리
				visited[next] = true;
				findMaxLength(next, depth + 1);
				// 후처리
				visited[next] = false;
			}
		}
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		
		friend = new ArrayList<>();
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			friend.add(new ArrayList<>());
		}
		
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			friend.get(a).add(b);
			friend.get(b).add(a);
		}
		
		visited = new boolean[elementCount];
	}
}
