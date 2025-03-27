import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이
 * 1. 시작지점으로 부터 그래프 탐색을 시작한다.
 * 1-1. 시작지점의 거리를 0으로 초기화한다.
 * 2. 만약 방문하지 않은 지점을 찾은 경우 현재 노드까지의 거리 + 1로 갱신
 * 3. 만약 최대 거리가 갱신되는 경우
 * 3-1. 최대 거리 노드를 저장, 최대 거리 갱신
 * 4. 만약 현재 최대거리와 같은 경우
 * 4-1. 현재 최대거리인 노드와 숫자를 비교 후 다음 노드 숫자가 더 크면 노드 번호 갱신
 *
 */
public class SWEA_1238_Contact {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static ArrayList<ArrayList<Integer>> connected;
	static int vertexCount, edgeCount, startPoint;
	static int maxVertex, maxDistance;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = 10;
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			findMaxDistance();
			
			sb.append("#").append(testCase).append(" ").append(maxVertex).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void findMaxDistance() {
		int[] distance = new int[vertexCount + 1];
		Arrays.fill(distance, -1);
		
		Queue<Integer> toVisit = new LinkedList<>();
		distance[startPoint] = 0;
		toVisit.add(startPoint);
		while (!toVisit.isEmpty()) {
			int now = toVisit.poll();
			
			if (maxDistance < distance[now]) {
				maxDistance = distance[now];
				maxVertex = now;
			}
			else if (maxDistance == distance[now]) {
				if (maxVertex < now) {
					maxDistance = distance[now];
					maxVertex = now;
				}
			}
			
			for (int next : connected.get(now)) {
				if (distance[next] != -1) {
					continue;
				}
				distance[next] = distance[now] + 1;
				toVisit.add(next);
			}
		}
	}
	
	static void init() throws Exception {
		connected = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		
		vertexCount = 100;
		edgeCount = Integer.parseInt(st.nextToken()) / 2;
		startPoint = Integer.parseInt(st.nextToken());
		
		for (int vertexIndex = 0; vertexIndex <= vertexCount; vertexIndex++) {
			connected.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			connected.get(from).add(to);
		}
		
		maxVertex = -1;
		maxDistance = -1;
	}
}
