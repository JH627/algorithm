import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2842_집배원한상덕 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] ADD_COL = {-1, 0, 1, -1, 1, -1, 0, 1};
	static final char OFFICE = 'P';
	static final char HOUSE = 'K';
	static final char EMPTY = '.';

	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static int mapSize, houseCount;
	static int[][] height;
	static char[][] map;
	static Point startPoint;

	static ArrayList<Integer> sortedHeight;
	static int minHouseHeight, maxHouseHeight;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinPower());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		mapSize = Integer.parseInt(br.readLine());
		map = new char[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = line.charAt(col);
				switch (map[row][col]) {
					case HOUSE:
						houseCount++;
						break;
					case OFFICE:
						startPoint = new Point(row, col);
						break;
				}
			}
		}

		minHouseHeight = Integer.MAX_VALUE;
		maxHouseHeight = Integer.MIN_VALUE;
		Set<Integer> set = new HashSet<>();
		height = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				height[row][col] = Integer.parseInt(st.nextToken());
				set.add(height[row][col]);

				if (map[row][col] == HOUSE || map[row][col] == OFFICE) {
					minHouseHeight = Math.min(minHouseHeight, height[row][col]);
					maxHouseHeight = Math.max(maxHouseHeight, height[row][col]);
				}
			}
		}

		sortedHeight = new ArrayList<>(set);
		Collections.sort(sortedHeight);
	}

	static int findMinPower() {
		int minPower = Integer.MAX_VALUE;

		int leftIndex = 0;
		int rightIndex = sortedHeight.indexOf(maxHouseHeight);
		int leftLimit = sortedHeight.indexOf(minHouseHeight);
		int rightLimit = sortedHeight.size();
		while (leftIndex <= leftLimit && rightIndex < rightLimit) {
			// 지금 불가능
			if (!isPossible(sortedHeight.get(leftIndex), sortedHeight.get(rightIndex))) {
				rightIndex++;
			}
			// 지금 가능하면 l범위 줄이기
			else {
				minPower = Math.min(minPower, sortedHeight.get(rightIndex) - sortedHeight.get(leftIndex));
				leftIndex++;
			}
		}

		return minPower;
	}

	static boolean isPossible(int minHeight, int maxHeight) {
		Queue<Point> toVisit = new LinkedList<>();

		boolean[][] visited = new boolean[mapSize][mapSize];
		toVisit.add(startPoint);
		visited[startPoint.row][startPoint.col] = true;
		int findHouseCount = 0;
		while (!toVisit.isEmpty()) {
			Point now = toVisit.poll();

			for (int deltaIndex = 0; deltaIndex < 8; deltaIndex++) {
				int newRow = now.row + ADD_ROW[deltaIndex];
				int newCol = now.col + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
					continue;
				}
				if (height[newRow][newCol] < minHeight || height[newRow][newCol] > maxHeight) {
					continue;
				}

				if (visited[newRow][newCol]) {
					continue;
				}
				visited[newRow][newCol] = true;

				if (map[newRow][newCol] == HOUSE) {
					if (++findHouseCount == houseCount) {
						return true;
					}
				}

				toVisit.add(new Point(newRow, newCol));
			}
		}

		return false;
	}
}
