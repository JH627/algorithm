import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 0.5단위로 만날 수 있으므로 좌표를 2배로 늘려서 생각하고 1씩 이동
 * 
 * 1. 점들의 위치와 방향, 에너지를 입력받고 리스트에 저장한다.
 * 2. 리스트 요소가 두개 이상인 경우 리스트 요소들을 확인한다 (하나인 경우 충돌이 불가능)
 * 	2-1. 점의 좌표를 키, 해당 좌표에 있는 점들의 정보를 값으로 가지는 맵을 생성한다
 * 	2-2. 리스트를 하나씩 확인하며 점의 방향에 따라 점을 이동 시킨다
 * 		2-2-1. 해당 점의 좌표를 1차원 좌표로 변환 (row * MAP_SIZE + col)하여 map에 저장
 * 	2-3. map에 있는 리스트들을 하나씩 확인
 * 		2-3-1. 만약 리스트의 사이즈가 1인 경우 충돌을 안한것이므로 다음에 확인할 리스트에 추가
 * 		2-3-2. 만약 리스트 사이즈가 2 이상인 경우
 * 			2-3-2-1. 리스트 내에 있는 점들의 에너지를 정답에 더하고
 * 			2-3-2-2. 다음에 확인할 리스트에는 추가하지 않음
 * 
 */
public class SWEA_5648_원자소멸시뮬레이션 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] ADD_ROW = {-1, 1, 0, 0};
	static final int[] ADD_COL = {0, 0, -1, 1};
	
	static final int MAP_SIZE = 4001;
	
	static class Point {
		int row, col;
		int direction;
		int energy;
		
		public Point(int row, int col, int direction, int energy) {
			this.row = row;
			this.col = col;
			this.direction = direction;
			this.energy = energy;
		}
	}
	
	static int pointCount;
	static List<Point> points;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			int score = movePoints();
			
			sb.append("#").append(testCase).append(" ").append(score).append("\n");	
		}
		
		System.out.print(sb);
	}
	
	static int movePoints() {
		HashMap<Integer, ArrayList<Point>> position;
		int score = 0;
		
		while (points.size() > 1) {
			position = new HashMap<>();
			
			for (Point now : points) {
				int newRow = now.row + ADD_ROW[now.direction];
				int newCol = now.col + ADD_COL[now.direction];
				
				if (newRow < 0 || newRow >= MAP_SIZE || newCol < 0 || newCol >= MAP_SIZE) {
					continue;
				}
				
				now.row = newRow;
				now.col = newCol;
				
				int pos = newRow * MAP_SIZE + newCol;
				
				position.computeIfAbsent(pos, p -> new ArrayList<>()).add(now);
			}
			
			List<Point> nextList = new LinkedList<>();
			// 점수 계산
			for (ArrayList<Point> bucket : position.values()) {
				if (bucket.size() == 1) {
					nextList.add(bucket.get(0));
				}
				else {
					for (Point point : bucket) {
						score += point.energy;
					}
				}
			}
			
			points = nextList;
		}

		return score;
	}
	
	static void init() throws Exception {
		pointCount = Integer.parseInt(br.readLine());
		
		points = new LinkedList<>();
		for (int pointIndex = 0; pointIndex < pointCount; pointIndex++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			int energy = Integer.parseInt(st.nextToken());
			
			int row = (1000 - y) * 2;
			int col = (x + 1000) * 2;
			
			points.add(new Point(row, col, direction, energy));
		}
	}
}
