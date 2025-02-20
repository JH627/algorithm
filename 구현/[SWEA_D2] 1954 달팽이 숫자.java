import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 달팽이 배열 그리기
 * 
 * 1. (0, 0)에서 달팽이가 출발한다.
 * 2. 움직이기 전에 기존에 있던 자리에 숫자를 남긴다.
 * 3. 만약 기존에 향하던 방향(directionStatus)으로 가려는데 배열 범위를 벗어나지 않고 기존에 방문했던 곳이 아니라면
 * 3-1. 기존에 가던 방향대로 앞으로 한칸 이동한다.
 * 3-1. 만약 조건에 맞지 않는 다면 방향을 돌린다 (예. 우 -> 하, 상 -> 우)
 * 		방향을 돌린 후 한칸 이동한다
 * 4. 배열의 크기만큼 이동을 반복하면 종료한다.
 *
 */
public class SWEA_1954_달팽이숫자 {
	
	// 달팽이 방향 좌표 배열 (우, 하, 좌, 상)
	static final int[] ROW_DIRECTION = {0, 1, 0, -1};
	static final int[] COL_DIRECTION = {1, 0, -1, 0};
	
	// directionStatus: 현재 달팽이의 방향, size: 배열의 크기
	static int directionStatus, size;
	static int[][] map; // 숫자를 저장할 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			// 처음은 우 방향으로 출발
			directionStatus = 0;	
			size = Integer.parseInt(br.readLine());
			
			map = new int[size][size];
			
			fillNumber(0, 0);
			
			sb.append("#").append(testCase).append(" ").append("\n");
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					sb.append(map[row][col]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
	static void fillNumber(int row, int col) {		
		for (int clock = 1; clock <= size * size; clock++) {
			// 기존에 있던 자리에 숫자를 남긴다.
			map[row][col] = clock;
			// 세로로 가는 경우
			if (directionStatus == 1 || directionStatus == 3) {
				// 만약 새로운 위치가
				int nextRow = row + ROW_DIRECTION[directionStatus];
				// 배열 범위를 벗어나지 않고 기존에 방문했던 곳이 아니라면 
				if (nextRow >= 0 && nextRow < size && map[nextRow][col] == 0) {
					// 새로운 위치로 이동
					row = nextRow;
				}
				// 만약 배열의 범위를 벗어나거나 기존에 방문했던 곳이라면
				else {
					// 바라보는 방향을 바꾼다.
					directionStatus = (directionStatus + 1) % 4;
					// 방향을 바꾼후 한칸 전진한다.
					col = col + COL_DIRECTION[directionStatus];
				}
			}
			// 가로로 가는 경우
			else {
				// 만약 새로운 위치가
				int newCol = col + COL_DIRECTION[directionStatus];
				// 배열 범위를 벗어나지 않고 기존에 방문했던 곳이 아니라면 
				if (newCol >= 0 && newCol < size && map[row][newCol] == 0) {
					// 새로운 위치로 이동
					col = newCol;
				}
				// 만약 배열의 범위를 벗어나거나 기존에 방문했던 곳이라면
				else {
					// 바라보는 방향을 바꾼다.
					directionStatus = (directionStatus + 1) % 4;
					// 방향을 바꾼후 한칸 전진한다.
					row = row + ROW_DIRECTION[directionStatus];
				}
			}
		}
		
	}
}
