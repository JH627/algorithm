import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 0인곳에서 그래프 탐색을 시작하자
 * 2. 0인곳으로만 뻗어나간다.
 * 2. 그래프 탐색을 하던 중 1을 만나면 0으로 바꾼다
 * 3. 그래프 탐색을 모두 마치면(1시간이 지나면) 이번 시간에 사라진 치즈의 개수를 반환한다.
 * 4. 치즈 개수을 갱신 (마지막 치즈의 개수 - 이번 시간에 사라진 치즈의 개수)
 * 4-1. 만약 치즈개수가 0개라면 마지막 시간에 지워진 치즈의 개수와 시간을 출력한다.
 *
 */
public class BOJ_2636_치즈 {

	static BufferedReader br;
	
    static final int[] ADD_ROW = {1, -1, 0, 0};
    static final int[] ADD_COL = {0, 0, 1, -1};
    
    static int[][] map; // 현재 치즈의 상태
    static boolean[][] visited; // 방문한 곳
    static int rowSize, colSize;
    static int emptyRow, emptyCol; // 외부 공기의 위치 (시작위치)
    static int cheezeSize; // 남은 치즈의 개수
	
	public static void main(String[] args) throws Exception {
		init();
		
		// 만약 주어진 치즈가 없다면 0 0 출력
		if (cheezeSize == 0) {
			System.out.printf("%d\n%d", 0, 0);
			return;
		}
		
		int removedNow = cheezeSize;
		int time = 0;
		while (++time > 0) {
			// (마지막 치즈의 개수 - 이번 시간에 사라진 치즈의 개수)
			removedNow = getRemovedCheezeCount();
			cheezeSize -= removedNow;
			
			// 다 사라졌다면 결과 출력
			if (cheezeSize == 0) {
				break;
			}
		}
		
		System.out.println(time);
		System.out.print(removedNow);
	}
	
	static int getRemovedCheezeCount() {
		visited = new boolean[rowSize][colSize];
		return removeCheese(emptyRow, emptyCol);
	}
	
	static int removeCheese(int row, int col) {
		Queue<int[]> toVisit = new LinkedList<>();
		
		// 0인곳 (외부공기)에서 시작
		visited[row][col] = true;
		toVisit.add(new int[] {row, col});
		
		int removedAreaCount = 0;
		int nowRow, nowCol, newRow, newCol;
		while (!toVisit.isEmpty()) {			
			int[] now = toVisit.poll();
			nowRow = now[0];
			nowCol = now[1];
			
			for (int index = 0; index < 4; index++) {
				newRow = nowRow + ADD_ROW[index];
				newCol = nowCol + ADD_COL[index];
				
				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}
				
				// 이미 방문한 곳을 패스
				if (visited[newRow][newCol]) {
					continue;
				}
				
				visited[newRow][newCol] = true;
				
				// 만약 치즈가 있는곳을 발견했다면
				if (map[newRow][newCol] == 1) {
					// 0으로 바꾸고
					map[newRow][newCol] = 0;
					// 이번 시간에 사라진 치즈개수에 추가
					// 만약 치즈가 모두 사라졌다면 조기 종료
					if (++removedAreaCount == cheezeSize) {
						return removedAreaCount;
					}
				}
				// 0이라면 계속 방문할 곳에 추가
				else {
					toVisit.add(new int[] {newRow, newCol});
				}
			}		
		}
		
		return removedAreaCount;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		cheezeSize = 0;
		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				if (st.nextToken().charAt(0) == '1') {
					cheezeSize++;
					map[row][col] = 1;
				}
				// 외부공기 시작 위치
				else {
					emptyRow = row;
					emptyCol = col;
				}
			}	
		}
	}

}
