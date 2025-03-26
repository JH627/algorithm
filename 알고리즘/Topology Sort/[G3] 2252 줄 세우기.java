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
public class BOJ_2252_줄세우기 {

	static BufferedReader br;
	static StringBuilder sb;
	
	static int elementCount, queryCount;
	static ArrayList<ArrayList<Integer>> front;
	static int[] parentCount;
	
	public static void main(String[] args) throws Exception {
		init();
		
		getSequence();
	
		System.out.print(sb);
	}
	
	static void getSequence() {
		Queue<Integer> toVisit = new LinkedList<>();
		
		// 먼저 나와야하는 요소가 없는 요소들을 담음
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			if (parentCount[elementIndex] == 0) {
				toVisit.add(elementIndex);
			}
		}
		
		while (!toVisit.isEmpty()) {
			int now = toVisit.poll();
			
			sb.append(now + 1).append(" ");
			
			// 연결된 요소 확인
			for (int next : front.get(now)) {
				// 이것보다 먼저 나와야하는 요소가 없는 경우 Queue에 담기
				if (--parentCount[next] == 0) {
					toVisit.add(next);					
				}
			}
		}		
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());
		
		parentCount = new int[elementCount];
		
		front = new ArrayList<>();
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			front.add(new ArrayList<>());
		}
		
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			front.get(a).add(b);
			parentCount[b]++;
		}		
	}

}
