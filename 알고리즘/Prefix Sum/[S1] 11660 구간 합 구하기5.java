import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 누적합 문제
 *
 * 풀이
 * 1. 배열을 입력받으며 각 칸의 누적합을 구한다.
 * 1-1. 입력받으며 오른쪽 위, 왼쪽 옆 값을 내 원래 값과 더한다.
 * 2. 값을 구할 구간의 쿼리 값을 받아 아래 기술한 누적합 계산 로직을 사용하여 직사각형 내 요소들의 합을 출력한다.
 * 
 * 배열의 누적합 계산
 * valueSum[endRow][endCol] 
 *    - valueSum[startRow - 1][endCol] - valueSum[endRow][startCol - 1]
 * + valueSum[startRow - 1][startCol - 1]
 * 
 * ㅁㅁㅁㅁ    ㅇㅇㅇㅇ     ㅇㅇㅇㅇ     ㅇㅇㅁㅁ      ㅇㅇㅁㅁ
 * ㅁㅁㅁㅁ >  ㅇㅇㅇㅇ   - ㅇㅇㅇㅇ   - ㅇㅇㅁㅁ      ㅇㅇㅁㅁ
 * ㅁㅁ◼◼     ㅇㅇㅇㅇ     ㅁㅁ◼◼     ㅇㅇ◼◼       ㅁㅁ◼◼
 * ㅁㅁ◼◼     ㅇㅇㅇㅇ     ㅁㅁ◼◼     ㅇㅇ◼◼       ㅁㅁ◼◼
 *          (o값을 계산)                  (겹치는 공간은 두번 빠지므로 다시 더해줌) 
 */
public class BOJ_11660_구간합구하기5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int boardSize, queryCount; // boardSize: 보드의 길이, queryCount: 합을 찾을 구간의 개수
	static int[][] valueSum; // 누적합을 저장할 배열
	
	public static void main(String[] args) throws Exception {
		init();
		
		findQuery();
		
		System.out.print(sb);
	}
	
	static void findQuery() throws Exception {
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int startRow = Integer.parseInt(st.nextToken()); // 시작 x1 좌표
			int startCol = Integer.parseInt(st.nextToken()); // 시작 y1 좌표
			int endRow = Integer.parseInt(st.nextToken()); // 종료 x2 좌표
			int endCol = Integer.parseInt(st.nextToken()); // 종료 y2 좌표
			
			int sum = valueSum[endRow][endCol] // 전체 누적합
					- valueSum[startRow - 1][endCol] // 범위에 포함되지 않는 위쪽 값을 감소
					- valueSum[endRow][startCol - 1] // 범위에 포함되지 않는 왼쪽 값을 감소
					+ valueSum[startRow - 1][startCol - 1]; // 중복으로 감소된 왼쪽 위 값을 다시 증가하여 복구
			
			sb.append(sum).append("\n");
		}
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		boardSize = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());
		
		// 누적합을 계산하기위해 (원래 사이즈 + 1)로 길이를 설정
		// 누적합 => 이전에 참고할 노드가 필요함 => 0번 인덱스는 참고할 노드가 없으므로 0번 인덱스를
		// 1번 인덱스처럼 활용하여 0번 인덱스를 참고할 수 있도록 함
		valueSum = new int[boardSize + 1][boardSize + 1];
		int value = 0;
		for (int row = 1; row < boardSize + 1; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col < boardSize + 1; col++) {
				value = Integer.parseInt(st.nextToken());
				
				// 누적합을 계산하여 저장
				valueSum[row][col] = value 
						+ valueSum[row - 1][col]
						+ valueSum[row][col - 1] 
						- valueSum[row - 1][col - 1];
			}
		}
		
	}
}
