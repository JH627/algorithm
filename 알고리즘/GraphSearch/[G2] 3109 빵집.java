import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 그리디 + DFS
 *
 * 풀이
 *
 * 1. 최대한 위쪽으로 딱 붙여서 파이프를 연결해보자
 *    (위쪽으로 딱 붙이는 건 위부터 파이프를 연결하기 때문 => 아래쪽에 공간을 최대한 확보하자)
 * 2. 만약 한번이라도 지나간 곳은 다시 방문할 필요가 없다
 *    (방문한 곳 => 이미 파이프가 연결되었거나 or 이미 전에 이 노드로 지나가 봤는데 어차피 연결이 안되는 경우)
 * 3. 만약 끝까지 도달했다면 길을 하나 늘린다.
 *
 */
public class BOJ_3109_빵집 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static final int[] ADD_ROW = {-1, 0, 1};

	static int rowSize, colSize;
	static boolean[][] isImpossible; // 방문한 곳
	static int wayCount; // 연결한 파이프 라인의 개수

	public static void main(String[] args) throws Exception {
		init();

		for (int startRow = 0; startRow < rowSize; startRow++) {
			if (findWay(startRow, 0)) {
				wayCount++;
			}
		}

		System.out.print(wayCount);
	}

	static boolean findWay(int row, int col) {
		// 끝까지 도달했다면 길이 완성되었음을 반환
		if (col == colSize - 1) {
			return true;
		}

		// 한번이라도 지나간 곳 표시
		isImpossible[row][col] = true;

		int nextCol = col + 1;
		// 위쪽에 최대한 붙여보자
		for (int addIndex = 0; addIndex < 3; addIndex++) {
			int nextRow = row + ADD_ROW[addIndex];

			if (nextRow < 0 || nextRow >= rowSize) {
				continue;
			}

			// 만약 지나간 곳이라면 패스
			if (isImpossible[nextRow][nextCol]) {
				continue;
			}

			// 파이프가 연결된 경우 더 이상의 분기는 없다
			if (findWay(nextRow, nextCol)) {
				return true;
			}
		}

		// 해당 지점에서 모든 분기를 했지만 파이프를 연결하지 못한 경우
		return false;
	}

	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		isImpossible = new boolean[rowSize][colSize];

		String shape;
		for (int row = 0; row < rowSize; row++) {
			shape = br.readLine();
			for (int col = 0; col < colSize; col++) {
				if (shape.charAt(col) == 'x') { 
					isImpossible[row][col] = true; // 건물이 있는 곳은 못간다
				}
			}
		}

		wayCount = 0;
	}
}
