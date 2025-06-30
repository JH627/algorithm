import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 얻을 수 있는 가치를 계산한다.
 * 2-1. 만약 현재 계산중인 부피가 넣으려는 물건의 부피보다 작은 경우
 * 	2-1-1. 물건을 하나 덜 넣었을 때, 현재 부피의 최대 가치 값으로 초기화한다.
 * 2-2. 만약 현재 계산중인 부피가 넣으려는 물건의 부피보다 크거나 같은 경우
 * 	2-2-1. 물건을 하나 덜 넣었을 때, 현재 물건을 넣는 경우와 현재 물건을 넣지 않을 때의 값을 비교한다.
 *
 */
public class SWEA_3282_01Knapsack {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int elementCount, limit;
	static int[] weight, cost;
	static int[][] maxCost;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			getMaxCost();
			
			sb.append("#").append(testCase).append(" ").append(maxCost[elementCount % 2][limit]).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void getMaxCost() {
		maxCost = new int[2][limit + 1];
		
		
		for (int elementIndex = 1; elementIndex < elementCount + 1; elementIndex++) {
			int prev = (elementIndex + 1) % 2;
			int now = elementIndex % 2;
			// 1. 얻을 수 있는 가치를 계산한다.
			for (int w = 1; w <= limit; w++) {
				// 2-1. 만약 현재 계산중인 부피가 넣으려는 물건의 부피보다 작은 경우
				if (weight[elementIndex] > w) {
					// 2-1-1. 물건을 하나 덜 넣었을 때, 현재 부피의 최대 가치 값으로 초기화한다.
					maxCost[now][w] = maxCost[prev][w];
				}
				// 2-2. 만약 현재 계산중인 부피가 넣으려는 물건의 부피보다 크거나 같은 경우
				else {
					// 2-2-1. 물건을 하나 덜 넣었을 때, 현재 물건을 넣는 경우와 현재 물건을 넣지 않을 때의 값을 비교한다.
					maxCost[now][w] = Math.max(
							maxCost[prev][w - weight[elementIndex]] + cost[elementIndex],
							maxCost[prev][w]);
				}
			}
		}
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		
		weight = new int[elementCount + 1];
		cost = new int[elementCount + 1];
		
		for (int elementIndex = 1; elementIndex < elementCount + 1; elementIndex++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			weight[elementIndex] = w;
			cost[elementIndex] = c;
		}
	}
}
