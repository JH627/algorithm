import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 주어진 키들로 만들 수 있는 경우의 수들을 조합 알고리즘으로 구함
 * 2. 점원을 탑에 넣거나 탑에 넣지 않는 경우로 분기
 * 2-1. 만약 현재 점원들의 키 합이 선반 높이와 같거나 크다면 선반 높이 이상인 탑중에 가장 낮은 탑의 값을 갱신하고 분기 종료
 * 2-2. 끝까지 다 탐색한 경우 분기 종료
 * 3. 선반 높이 이상인 값 중 가장 작은 탑의 값 - 선반 높이를 출력
 *
 */
public class SWEA_1486_장훈이의높은선반 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int memberCount, thingHeight; // memberCount: 점원 수, thingHeight: 선반 높이
	static int[] heights; // 점원들의 키
	static int nearestHeight; // 선반 높이 이상인 값 중 가장 작은 탑의 값
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			getNearestHeight(0, 0);
			
			// 선반 높이 이상인 값 중 가장 작은 탑의 값 - 선반 높이
			sb.append("#").append(testCase).append(" ").append(nearestHeight - thingHeight).append("\n");
		}

		System.out.print(sb);
	}
	
	static void getNearestHeight(int memberIndex, int sum) {
		// 만약 현재 점원들의 키 합이 선반 높이와 같거나 크다면
		// 선반 높이 이상인 탑중에 가장 낮은 탑의 값을 갱신
		if (sum >= thingHeight) {
			nearestHeight = Math.min(nearestHeight, sum);
			return;
		}
		
		// 끝까지 다 탐색한 경우 그냥 종료
		if (memberIndex == memberCount) {
			return;
		}
		
		// 점원을 탑에 쌓는다
		getNearestHeight(memberIndex + 1, sum + heights[memberIndex]);
		// 점원을 탑에 쌓지 않는다
		getNearestHeight(memberIndex + 1, sum);
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		memberCount = Integer.parseInt(st.nextToken());
		thingHeight = Integer.parseInt(st.nextToken());
		heights = new int[memberCount];
		
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < memberCount; index++) {
			heights[index] = Integer.parseInt(st.nextToken());
		}
		
		nearestHeight = Integer.MAX_VALUE;
	}
}
