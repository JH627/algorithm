package problemsss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DFS
 * 
 * 풀이
 * 1. 회사에서 출발해보자
 * 2. 회사에서 갈 수 있는 곳들로 분기
 *    - 만약 최단 경로가 갱신된 상태라면 현재 갈 수 있는 곳이 최단 경로보다 긴 경우에는 분기하지 않는다.
 * 3. 만약 집을 제외한 모든 곳들을 방문한 상태라면 집까지의 거리를 체크후 최단 경로 값 갱신
 *
 */
public class SWEA_1247_최적경로 {
	
	static BufferedReader br;
	
	static int customerCount; // 방문할 곳 (고객 수 + 2 (집, 회사))
	static boolean[] visited; // 방문한 곳
	static int[] x, y; // 집들의 좌표
	static int minLength; // 최단 경로
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			findMinLength(0, 0, 1);
			
			sb.append("#").append(testCase).append(" ").append(minLength).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// now: 현재 방문한 곳의 인덱스, sum: 현재까지의 거리 합, visitedCount: 방문한 곳의 개수
	static void findMinLength(int now, int sum, int visitedCount) {
		// 만약 집 빼고 다 방문한 상태라면
		if (visitedCount + 1 == customerCount) {
			// 집까지의 경로 길이를 계산 후 최단 경로 갱신
			sum += getDistance(x[now], y[now], x[1], y[1]);
			minLength = Math.min(minLength, sum);
			return;
		}
		
		// 아직 방문하지 않은 곳이 남은 경우
		for (int customerIndex = 2; customerIndex < customerCount; customerIndex++) {
			// 방문하지 않은 곳을 찾아서
			if (visited[customerIndex]) {
				continue;
			}

			// 그 곳까지의 거리를 계산 후
			int nextDistance = getDistance(x[now], y[now], x[customerIndex], y[customerIndex]);
			// 만약 현재 최단 경로보다 짧다면 가본다
			if (sum + nextDistance < minLength) {
				visited[customerIndex] = true;
				findMinLength(customerIndex, sum + nextDistance, visitedCount + 1);
				visited[customerIndex] = false;
			}			
		}
	}
	
	// 거리 구하는 함수
	static int getDistance(int startX, int startY, int endX, int endY) {
		return Math.abs(startX - endX) + Math.abs(startY - endY);	
	}
	
	static void init() throws Exception {
		customerCount = Integer.parseInt(br.readLine()) + 2;
		
		visited = new boolean[customerCount];
		x = new int[customerCount];
		y = new int[customerCount];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int customerIndex = 0; customerIndex < customerCount; customerIndex++) {
			x[customerIndex] = Integer.parseInt(st.nextToken());
			y[customerIndex] = Integer.parseInt(st.nextToken());
		}
		
		minLength = Integer.MAX_VALUE;
	}
}
