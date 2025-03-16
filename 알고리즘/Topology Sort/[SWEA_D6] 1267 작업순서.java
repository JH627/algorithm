import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 위상정렬
 * 
 * 풀이
 * 
 * 1. 먼저 나와야하는 관계를 그래프로 나타낸다.
 * 2. A가 B보다 먼저나와야하면 
 *    => graph.get(a).add(b)
 *    => parentCount[B]++
 * 3. 모든 비교를 확인한 후 parentCount가 0인 요소들을 Queue에 담는다.
 *    => 이것보다 먼저나와야하는 요소가 없음
 * 4. Queue에서 하나씩 꺼내며 현재 요소와 연결된 요소들을 확인한다. 
 *    => 요소를 꺼내면 요소를 출력
 *    => 확인한다는건 먼저 나와야하는 요소가 하나가 나온 것
 *    => parentCount[연결된 요소]--
 * 4-1. 만약 parentCount == 0이라면 이제 이것보다 먼저 나와야하는 요소가 없으므로 Queue에  담는다.
 *
 */
public class SWEA_1267_작업순서 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static final int TC = 10;
	
	static int vertexCount, edgeCount; // vertexCount: 노드 개수, edgeCount: 간선 개수
	static ArrayList<ArrayList<Integer>> graph; // 주어진 그래프
	static int[] parentCount; // 먼저 나와야하는 요소의 개수
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			sb.append("#").append(testCase).append(" ");
			
			getSequence();
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void getSequence() {
		Queue<Integer> toVisit = new LinkedList<>();
		
		// 먼저 나와야하는 요소가 없는 요소들을 담음
		for (int vetexIndex = 0; vetexIndex < vertexCount; vetexIndex++) {
			if (parentCount[vetexIndex] == 0) {
				toVisit.add(vetexIndex);
			}
		}
		
		while (!toVisit.isEmpty()) {
			int now = toVisit.poll();
			
			sb.append(now + 1).append(" ");
			
			// 연결된 요소 확인
			for (int next : graph.get(now)) {
				// 이것보다 먼저 나와야하는 요소가 없는 경우 Queue에 담기
				if (--parentCount[next] == 0) {
					toVisit.add(next);					
				}
			}
		}
		
		
		
	}
	
	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		
		parentCount = new int[vertexCount];
		
		graph = new ArrayList<>();
		for (int vetexIndex = 0; vetexIndex < vertexCount; vetexIndex++) {
			graph.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			
			// 선후 관계를 표현
			graph.get(first).add(second);
			// second 보다 first가 먼저 나와야 함
			parentCount[second]++;
		}
	}

}
