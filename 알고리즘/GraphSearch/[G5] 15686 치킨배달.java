import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};
	
	static class Position {
		int row, col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int mapSize, limit, storeCount;
	static ArrayList<Position> startingPoints;
	static int[][] map;
	static int[][] dist;
	static int minDistance = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		init();
		
		getDistance();
		findMinDistance();
		
		System.out.print(minDistance);
	}
	
	static void getDistance() {
		dist = new int[startingPoints.size()][storeCount];
		
		for (int startPoint = 0; startPoint < startingPoints.size(); startPoint++) {
			setDistance(startPoint);
		}
	}
	
	static void setDistance(int start) {
		Position startPoint = startingPoints.get(start);
		Queue<Position> toVisit = new LinkedList<>();
		int[][] distance = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			Arrays.fill(distance[row], -1);
		}
		distance[startPoint.row][startPoint.col] = 0;
		toVisit.add(new Position(startPoint.row, startPoint.col));
		
		while (!toVisit.isEmpty()) {
			Position now = toVisit.poll();
			
			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				int newRow = now.row + ADD_ROW[deltaIndex];
				int newCol = now.col + ADD_COL[deltaIndex];
				
				if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
					continue;
				}
				
				if (distance[newRow][newCol] != -1) {
					continue;
				}
				
				distance[newRow][newCol] = distance[now.row][now.col] + 1;
				if (map[newRow][newCol] >= 0) {
					dist[start][map[newRow][newCol]] = distance[newRow][newCol];
				}
				toVisit.add(new Position(newRow, newCol));
			}
		}
	}
	
	static void findMinDistance() {
		int max = 1 << storeCount;
		for (int cond = 0; cond < max; cond++) {
			if (Integer.bitCount(cond) == limit) {
				minDistance = Math.min(minDistance, getDistanceBy(cond));
			}
		}
	}
	
	static int getDistanceBy(int cond) {
		int sum = 0;
		for (int startIndex = 0; startIndex < startingPoints.size(); startIndex++) {
			int tempDistance = Integer.MAX_VALUE;
			for (int endHouse = 0; endHouse < storeCount; endHouse++) {
				if (((1 << endHouse) & cond) >= 1) {
					if (tempDistance > dist[startIndex][endHouse]) {
						tempDistance = dist[startIndex][endHouse];
					}
				}
			}
			sum += tempDistance;
			if (sum > minDistance) {
				return Integer.MAX_VALUE;
			}
		}
		
		return sum;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		
		startingPoints = new ArrayList<>();
		map = new int[mapSize][mapSize];
		storeCount = 0;
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(map[row], -1);
			for (int col = 0; col < mapSize; col++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 1) {
					startingPoints.add(new Position(row, col));
				}
				else if (value == 2) {
					map[row][col] = storeCount++;
				}
			}
		}
	}
		
}
