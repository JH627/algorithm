import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 분할 정복
 * 
 * 풀이
 * 
 * 1. 현재 확인해야하는 공간이 0또는 1로 가득차 있는지 확인해보자
 * 1-1. 누적합을 통해 확인 
 *      => 해당 공간의 합이 0이라면 0출력, 해당 공간의 합이 size * size라면 1 출력 
 * 2. (1-1)에서 해당공간이 모두 0이거나 1인지 확인했기 때문에 아닌 경우 4등분 해야함
 * 2-1. 만약 길이가 2인 경우 
 *      => 나누게 되면 어차피 0또는 1이므로 괄호로 둘러싸인 4개의 값(2, 1, 3, 4분면) 출력
 * 2-2. 만약 길이가 2가 아닌경우
 *      => 괄호를 씌우고 2, 1, 3, 4 분면으로 분할 (사이즈 /= 2)
 *
 */
public class BOJ_1992_쿼드트리 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static int videoSize; // 길이
	static int[][] origin, videoSum; // 원본 값, 누적합 배열
	
	public static void main(String[] args) throws Exception {
		init();
		
		getQuadTree(1, 1, videoSize);
		
		System.out.print(sb);
	}
	
	static void getQuadTree(int row, int col, int size) {
		// 현재 공간의 누적합을 확인
		int sum = videoSum[row + size - 1][col + size - 1] - videoSum[row + size - 1][col - 1] - videoSum[row - 1][col + size - 1] + videoSum[row - 1][col - 1];
		
		// 0인 경우 모든 공간이 0이므로 0 출력
		if (sum == 0) {
			sb.append(0);
			return;
		}
		
		// 합이 size * size인 경우 모든 공간이 1이므로 1 출력
		if (sum == size * size) {
			sb.append(1);
			return;
		}
		
		// 더이상 쪼개는 의미가 없는 경우
		// 하나씩 출력
		if (size == 2) {
			sb.append('(');
			sb.append(origin[row][col]);
			sb.append(origin[row][col + 1]);
			sb.append(origin[row + 1][col]);
			sb.append(origin[row + 1][col + 1]);
			sb.append(')');
			return;
		}
		
		// 공간이 하나의 값으로 통일이 안 된 경우
		// 2, 1, 3, 4 분면으로 분할
		sb.append('(');
		// 2사분면
		getQuadTree(row, col, size / 2);
		// 1사분면
		getQuadTree(row, col + size / 2, size / 2);
		// 3사분면
		getQuadTree(row + size / 2, col, size / 2);
		// 4사분면
		getQuadTree(row + size / 2, col + size / 2, size / 2);
		sb.append(')');
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		videoSize = Integer.parseInt(br.readLine());
		origin = new int[videoSize + 1][videoSize + 1];
		videoSum = new int[videoSize + 1][videoSize + 1];
		
		for (int row = 1; row < videoSize + 1; row++) {
			String videoLine = br.readLine();
			for (int col = 1; col < videoSize + 1; col++) {
				origin[row][col] = videoLine.charAt(col - 1) - '0';
				
				// 누적합
				videoSum[row][col] = 
						origin[row][col] 
					    + videoSum[row - 1][col] + videoSum[row][col - 1]
					    - videoSum[row - 1][col - 1];
			}
		}
	}
}
