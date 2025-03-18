import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1938_통나무옮기기 {

	static BufferedReader br;
	
	static final int INF = 1000000;
	
	static class Position {
		int row, col;
		int status;
		
		public Position(int row, int col, int status) {
			this.row = row;
			this.col = col;
			this.status = status;
		}
	}
	
	static int mapSize;
	static int[][] map, nearTreeCount;
	static int[][][] distance;
	static int startRow, startCol, startStatus; // status: 0 => 가로, 1 => 세로
	static int endRow, endCol, endStatus; 
	
	public static void main(String[] args) throws Exception {
		init();
	
		int minDistance = findMinDistance();
		
		System.out.print(minDistance);
	}
	
	static int findMinDistance() {
		Queue<Position> toVisit = new LinkedList<>();
		toVisit.add(new Position(startRow, startCol, startStatus));
		
		distance[startRow][startCol][startStatus] = 0;
		while (!toVisit.isEmpty()) {
			Position now = toVisit.poll();

			if (now.row == endRow && now.col == endCol && now.status == endStatus) {
				return distance[endRow][endCol][endStatus];
			}
			
			
			// 이동 시켜보자
			// 이동전에는 이동가능성
			// 거리 체크
			
			// 상
			// == 현재 가로
			if (now.status == 0) {
				// 올라갈 수 있고
				if (now.row > 1) {
					if (map[now.row - 1][now.col - 1] == 0
							&& map[now.row - 1][now.col] == 0
							&& map[now.row - 1][now.col + 1] == 0) {
						
						if (distance[now.row - 1][now.col][now.status] == INF) {
							distance[now.row - 1][now.col][now.status] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row - 1, now.col, now.status));
						}
					}
				}		
			}
			// == 현재 세로
			else if (now.status == 1) {
				if (now.row > 2) {
					if (map[now.row - 2][now.col] != 1) {
						if (distance[now.row - 1][now.col][now.status] == INF) {
							distance[now.row - 1][now.col][now.status] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row - 1, now.col, now.status));
						}
					}
				}
			}
			
			// 하
			// == 현재 가로
			if (now.status == 0) {
				// 내려갈 수 있고
				if (now.row < mapSize) {
					if (map[now.row + 1][now.col - 1] == 0
							&& map[now.row + 1][now.col] == 0
							&& map[now.row + 1][now.col + 1] == 0) {
						
						if (distance[now.row + 1][now.col][now.status] == INF) {
							distance[now.row + 1][now.col][now.status] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row + 1, now.col, now.status));	
						}
					}
				}		
			}
			// == 현재 세로
			else if (now.status == 1) {
				if (now.row < mapSize - 1) {
					if (map[now.row + 2][now.col] != 1) {
						if (distance[now.row + 1][now.col][now.status] == INF) {
							distance[now.row + 1][now.col][now.status] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row + 1, now.col, now.status));
						}
					}
				}
			}
			
			// 좌
			// == 현재 가로
			if (now.status == 0) {
				// 왼쪽으로 갈 수 있고
				if (now.col > 2) {
					if (map[now.row][now.col - 2] != 1) {
						if (distance[now.row][now.col - 1][now.status] == INF) {
							distance[now.row][now.col - 1][now.status] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row, now.col - 1, now.status));
						}
					}
				}		
			}
			// == 현재 세로
			else if (now.status == 1) {
				if (now.col > 1) {
					if (map[now.row - 1][now.col - 1] == 0
							&& map[now.row][now.col - 1] == 0
							&& map[now.row + 1][now.col - 1] == 0) {
						if (distance[now.row][now.col - 1][now.status] == INF) {
							distance[now.row][now.col - 1][now.status] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row, now.col - 1, now.status));
						}
					}
				}
			}
			
			// 우
			// == 현재 가로
			if (now.status == 0) {
				// 오른쪽으로 갈 수 있고
				if (now.col < mapSize - 1) {
					if (map[now.row][now.col + 2] != 1) {
						if (distance[now.row][now.col + 1][now.status] == INF) {
							distance[now.row][now.col + 1][now.status] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row, now.col + 1, now.status));
						}
					}
				}		
			}
			// == 현재 세로
			else if (now.status == 1) {
				if (now.col < mapSize) {
					if (map[now.row - 1][now.col + 1] == 0
							&& map[now.row][now.col + 1] == 0
							&& map[now.row + 1][now.col + 1] == 0) {
						if (distance[now.row][now.col + 1][now.status] == INF) {
							distance[now.row][now.col + 1][now.status] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row, now.col + 1, now.status));
						}
					}
				}
			}

			// 회전
			// == 현재 가로			
			if (now.status == 0) {
				// 맵밖으로 나가지 않는 경우
				if (now.row >= 2 && now.row <= mapSize - 1 
						&& now.col >= 2 && now.col <= mapSize - 1) {
					// 주변에 장애물이 없는 경우
					if (nearTreeCount[now.row + 1][now.col + 1]
							- nearTreeCount[now.row + 1][now.col - 2]
							- nearTreeCount[now.row - 2][now.col + 1]
							+ nearTreeCount[now.row - 2][now.col - 2] == 0
						) {
						if (distance[now.row][now.col][1] == INF) {
							distance[now.row][now.col][1] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row, now.col, 1));
						}
					}
				}				
			}
			// == 현재 세로
			else if (now.status == 1) {
				// 맵밖으로 나가지 않는 경우
				if (now.row >= 2 && now.row <= mapSize - 1 
						&& now.col >= 2 && now.col <= mapSize - 1) {
					// 주변에 장애물이 없는 경우
					if (nearTreeCount[now.row + 1][now.col + 1]
							- nearTreeCount[now.row + 1][now.col - 2]
							- nearTreeCount[now.row - 2][now.col + 1]
							+ nearTreeCount[now.row - 2][now.col - 2] == 0
						) {
						if (distance[now.row][now.col][0] == INF) {
							distance[now.row][now.col][0] = distance[now.row][now.col][now.status] + 1;
							toVisit.add(new Position(now.row, now.col, 0));
						}
					}
				}		
			}
		}
		
		// 경우의 수를 다 확인했는데 이동이 불가능 한 경우
		return 0;
	}
	

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize + 1][mapSize + 1];
		nearTreeCount = new int[mapSize + 1][mapSize + 1];
		distance = new int[mapSize + 1][mapSize + 1][2];
		
		for (int row = 0; row <= mapSize; row++) {
			for (int col = 0; col <= mapSize; col++) {
					Arrays.fill(distance[row][col], INF);
			}
		}
		
		startRow = -1;		startCol = -1;
		endRow = -1;		endCol = -1;
		for (int row = 1; row <= mapSize; row++) {
			String rowMap = br.readLine();
			for (int col = 1; col <= mapSize; col++) {
				nearTreeCount[row][col] = 0 
						+ nearTreeCount[row - 1][col]
						+ nearTreeCount[row][col - 1]
						- nearTreeCount[row - 1][col - 1];	
				if (rowMap.charAt(col - 1) == '1') {
					map[row][col] = 1;
					nearTreeCount[row][col]++;
				}
				else if (rowMap.charAt(col - 1) == 'B') {
					if (rowMap.contains("BBB")) {
						if (startRow == -1) {
							startRow = row;
							startCol = col + 1;
							startStatus = 0;
						}
					}
					else {
						// 세로 첫 발견
						if (startRow == -1) {
							startRow = Integer.MAX_VALUE;
							startCol = Integer.MAX_VALUE;
						}
						// 세로 두번째 발견
						else if (startRow == Integer.MAX_VALUE) {
							startRow = row;
							startCol = col;
							startStatus = 1;
						}
					}
				}
				else if (rowMap.charAt(col - 1) == 'E') {
					if (rowMap.contains("EEE")) {
						// 가로 첫발견
						if (endRow == -1) {
							endRow = row;
							endCol = col + 1;
							endStatus = 0;
						}
					}
					else {
						// 세로 첫 발견
						if (endRow == -1) {
							endRow = Integer.MAX_VALUE;
							endCol = Integer.MAX_VALUE;
						}
						// 세로 두번째 발견
						else if (endRow == Integer.MAX_VALUE) {
							endRow = row;
							endCol = col;
							endStatus = 1;
						}
					}
				}
			}
		}
	}
}
