import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3190_뱀 {

	static BufferedReader br;
	static StringTokenizer st;

	// 상, 우, 하, 좌
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};
	static final int EMPTY = 0;
	static final int BODY = 1;
	static final int APPLE = 2;

	static int mapSize, appleCount, queryCount;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findLifeTime());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize][mapSize];

		appleCount = Integer.parseInt(br.readLine());
		for (int apple = 0; apple < appleCount; apple++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			map[row][col] = APPLE;
		}

		queryCount = Integer.parseInt(br.readLine());
	}

	static int findLifeTime() throws IOException {
		int time = 0;
		int currentDirection = 1;

		Deque<int[]> body = new LinkedList<>();
		map[0][0] = BODY;
		body.addFirst(new int[]{0, 0});
		int nowRow = 0;
		int nowCol = 0;
		for (int query = 0; query < queryCount + 1; query++) {
			int moveTime = 0;
			char operation = '.';
			if (query < queryCount) {
				st = new StringTokenizer(br.readLine());
				moveTime = Integer.parseInt(st.nextToken());
				operation = st.nextToken().charAt(0);
			}
			else {
				moveTime = Integer.MAX_VALUE;
			}

			while (time < moveTime) {
				time++;
				// 몸 이동
				int newRow = nowRow + ADD_ROW[currentDirection];
				int newCol = nowCol + ADD_COL[currentDirection];

				if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
					return time;
				}

				if (map[newRow][newCol] == BODY) {
					return time;
				}

				// 꼬리 반영
				body.addFirst(new int[]{newRow, newCol});
				if (map[newRow][newCol] != APPLE) {
					int[] lastBody = body.pollLast();
					map[lastBody[0]][lastBody[1]] = EMPTY;
				}

				map[newRow][newCol] = BODY;
				nowRow = newRow;
				nowCol = newCol;
			}

			if (query < queryCount) {
				// 방향전환
				if (operation == 'L') {
					currentDirection = (currentDirection + 4 - 1) % 4;
				}
				else {
					currentDirection = (currentDirection + 1) % 4;
				}
			}
		}

		return time;
	}
}
