import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다리는 겹치면 안되므로
 * 오른쪽에서 N개 중 R개만 고르면 된다.
 * 
 * nCr == n-1 C r-1 + n-1 C r 을 활용
 * 
 * 1. 미리 30C30까지의 값을 구한다.
 * 1-1. r == 0 이거나 r == n 인 경우
 * 1-1-1. combination[n][r] = 1
 * 1-2. 그 외의 경우
 * 1-2-1. combination[n][r] = combination[n - 1][r] + combination[n - 1][r - 1]
 *
 */
public class BOJ_1010_다리놓기 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int MAX = 30;
	
	static int[][] combination;
	
	public static void main(String[] args) throws Exception {
		init();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			sb.append(combination[n + 1][r]).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		combination = new int[MAX + 1][MAX + 1];
		
		for (int n = 0; n <= MAX; n++) {
			for (int r = 0; r < n; r++) {
				if (r == 0 || r == n) {
					combination[n][r] = 1;
				}
				else {
					combination[n][r] = combination[n - 1][r] + combination[n - 1][r - 1];
				}
			}
		}
	}
}
