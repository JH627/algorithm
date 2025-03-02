import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 *
 * 1. 입력을 받으며 각 번호가 등장하는 위치를 저장해둔다.
 * 2. 1부터 하나씩 접근하며 DFS를 통해 가장 멀리갈 수 있는 길이를 구한다.
 *    (반드시 1이 큰 곳으로 가야하기 때문에 낮은 숫자부터 먼저 처리한다)
 * 2-1. 접근 시 방문처리를 한다.
 * 2-2. 1부터 하나씩 접근할 때 이미 방문한 숫자인 경우에는 DFS를 시작하지 않는다.
 * 3. 모든 숫자가 방문처리가 되면 최대 길이와 숫자를 출력한다. 
 *
 */
public class SWEA_1861_정사각형방 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static final int[] ADD_ROW = {-1, 0, 1, 0}; // 이동 방향 
	static final int[] ADD_COL = {0, -1, 0, 1};
	
	static int mapSize; // 맵의 크기
	static int[][] map; // 맵 숫자를 저장할 배열
	static int[][] position; // 숫자가 어느 위치에 등장하는 지 저장할 배열
	static boolean[] visited; // 숫자를 확인했는지 저장할 배열
	static int maxLength, maxRoomIndex; // maxLength: 최대 길이, max: 최대길이가 시작된 숫자

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			// 1부터 하나씩 접근
			for (int index = 0; index < mapSize * mapSize; index++) {
				// 만약 전 숫자들에서 방문되지 않은 경우
				if (!visited[index]) {
					visited[index] = true;
					// DFS 진행
					int length = findConnectedArrayLength(position[index][0], position[index][1], 1);
					
					// 만약 최대 길이가 갱신 된경우
					if (length > maxLength) {
						// 길이와 등장 숫자 최신화
						maxLength = length;
						maxRoomIndex = index + 1;
					}
				}
			}

			sb.append("#").append(testCase).append(" ");
			sb.append(maxRoomIndex).append(" ").append(maxLength).append("\n");
		}

		System.out.print(sb);
	}
	
	static int findConnectedArrayLength(int row, int col, int length) {
		// 주변 확인
		for (int addIndex = 0; addIndex < 4; addIndex++) {
			int newRow = row + ADD_ROW[addIndex];
			int newCol = col + ADD_COL[addIndex];
			
			if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
				continue;
			}
			
			// 숫자가 1차이 나야하는 조건에 안 맞는 경우 조기 종료
			if (map[newRow][newCol] - map[row][col] != 1) {
				continue;
			}
			
			// 만약 1이 큰 곳이 있다면 길이를 1 늘리고 계속 탐색
			length = findConnectedArrayLength(newRow, newCol, length + 1);
			
			break;
		}
		
		return length;
	}

	static void init() throws IOException {
		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize][mapSize];
		visited = new boolean[mapSize * mapSize];
		position = new int[mapSize * mapSize][2];
		
		StringTokenizer st;
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				int number = Integer.parseInt(st.nextToken()) - 1;
				// 각 숫자의 등장 위치 저장
				map[row][col] = number;
				position[number][0] = row;
				position[number][1] = col;
			}
		}
		
		maxLength = 0;
		maxRoomIndex = 0;
	}
	
}
