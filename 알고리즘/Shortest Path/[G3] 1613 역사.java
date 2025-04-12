import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 역사는 사이클이 없음
 * 
 * 1. 만약 a가 b보다 먼저 일어난 경우
 * 	1-1. a->b로 가는 거리를 1로 설정
 * 2. 모든 사건간의 거리를 채움
 * 3-1. 만약 a->b, b->a 가 모두 불가능 한경우
 * 	3-1-1. 어느것이 먼저일어났는지 알수  없음 0 출력
 * 3-2. 만약 b->a의 거리가 있는 경우
 * 	3-2-1. b가 먼저 일어났으므로 1 출력
 * 3-3. 만약 a->b의 거리가 있는 경우
 * 	3-3-1. a가 먼저 일어났으므로 -1 출력
 *
 */
public class BOJ_1613_역사 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int INF = 401;
	
	static int eventCount, queryCount;
	static int[][] distance;
	
	public static void main(String[] args) throws Exception {
		init();

		fillDistance();
		
		checkDistance();
		
		System.out.println(sb);
	}

	static void fillDistance() {
		for (int middle = 1; middle < eventCount + 1; middle++) {
			for (int start = 1; start < eventCount + 1; start++) {
				for (int end = 1; end < eventCount + 1; end++) {
					distance[start][end] = Math.min(distance[start][middle] + distance[middle][end], distance[start][end]);
				}
			}
		}
	}
	
	static void checkDistance() throws Exception {
		queryCount = Integer.parseInt(br.readLine());
		
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (distance[a][b] == INF && distance[b][a] == INF) {
				sb.append("0\n");
			}
			else if (distance[a][b] == INF) {
				sb.append("1\n");
			}
			else if (distance[b][a] == INF){
				sb.append("-1\n");
			}
		}
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		eventCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());
		
		distance = new int[eventCount + 1][eventCount + 1];
		for (int row = 0; row < eventCount + 1; row++) {
			Arrays.fill(distance[row], INF);
		}
		
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			distance[first][second] = 1;
		}
	}
}
