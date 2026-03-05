import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1730_판화 {

	static BufferedReader br;
	static StringBuilder sb;

	static int mapSize;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		init();
		useQuery();
		printMap();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		mapSize = Integer.parseInt(br.readLine());
		map = new char[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = '.';
			}
		}
	}

	static void useQuery() throws IOException {
		char[] query = br.readLine().toCharArray();

		int row = 0, col = 0;
		for (char operation : query) {
			int newRow = row;
			int newCol = col;

			switch (operation) {
				case 'U':
					newRow = row - 1;
					break;
				case 'D':
					newRow = row + 1;
					break;
				case 'L':
					newCol = col - 1;
					break;
				case 'R':
					newCol = col + 1;
					break;
			}

			if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
				continue;
			}

			if (operation == 'U' || operation == 'D') {
				map[row][col] = (map[row][col] == '-') ? '+' : (map[row][col] == '.') ? '|' : map[row][col];
				map[newRow][newCol] = (map[newRow][newCol] == '-') ? '+' : (map[newRow][newCol] == '.') ? '|' : map[newRow][newCol];
			}
			else {
				map[row][col] = (map[row][col] == '|') ? '+' : (map[row][col] == '.') ? '-' : map[row][col];
				map[newRow][newCol] = (map[newRow][newCol] == '|') ? '+' : (map[newRow][newCol] == '.') ? '-' : map[newRow][newCol];
			}

			row = newRow;
			col = newCol;
		}
	}

	static void printMap() {
		sb = new StringBuilder();
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				sb.append(map[row][col]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
