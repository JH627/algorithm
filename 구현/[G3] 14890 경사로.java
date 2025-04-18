import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 현재의 높이를 검사한다.
 * 	1-1. 만약 전과 높이가 2 이상 차이난다면 활주로 건설이 불가
 * 2. 만약 전과 높이가 같다면
 * 	2-1. 활주로 건설에 사용가능한 범위를 1늘린다.
 * 3. 만약 내려가는 경우
 * 	3-1. 이전에 설치한 블럭이 남아있는 경우 (사용가능한 범위가 음수인경우)
 * 		3-1-1. 활주로 건설이 불가
 * 	3-2. 이전 구역에 설치가 완료된 경우
 * 		3-2-1앞으로 같은 높이의 숫자가 (최소 블럭길이 - 1)만큼 등장해야한다고 표시 
 * 4. 만약 올라가는 경우
 * 	4-1. 이전 구역에 올라가는 블럭을 설치할 수 없는 경우 (활주로 건설에 사용가능한 범위가 blockLength보다 적은 경우)
 * 		4-1-1. 활주로 건설 불가
 * 	4-2. 이전 구역에 설치 가능한 경우
 * 		4-2-1. 활주로 건설에 사용가능한 범위를 1로 설정.
 * 5. 만약 끝까지 검사했는데 언덕을 설치해야하는 구간이 남아있는 경우
 * 	5-1. 활주로 건설 불가
 *
 */
public class BOJ_14890_경사로 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int mapSize, blockLength;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
        init();
			
		int count = 0;
			
		for (int row = 0; row < mapSize; row++) {
			if (checkRow(row)) {
				count++;
			}
		}
		
		for (int col = 0; col < mapSize; col++) {
			if (checkCol(col)) {
				count++;
			}
		}
		
		System.out.print(count);
	}
	
	static boolean checkRow(int row) {
		int prev = 0;
		int remainArea = 0;
		for (int col = 0; col < mapSize; col++) {
			if (col == 0) {
				prev = map[row][col];
				remainArea = 1;
				continue;
			}
			
			// 높이가 두칸이상 차이나면 불가
			if (Math.abs(prev - map[row][col]) >= 2) {
				return false;
			}
			
			// 전과 같다면 사용가능 범위 + 1
			if (prev == map[row][col]) {
				remainArea++;
			}
			// 내려가는 경우
			else if (prev > map[row][col]) {
				// 전에 설치한 블럭이 남아 있는 경우
				if (remainArea < 0) {
					return false;
				}
				remainArea = -blockLength + 1;
				prev = map[row][col];
			}
			// 올라가는 경우
			else if (prev < map[row][col]) {
				// 올라가는 발판을 설치할 공간이 모자란 경우
				if (remainArea < blockLength) {
					return false;
				}
				remainArea = 1;
				prev = map[row][col];
			}
		}
		
		if (remainArea < 0) {
			return false;
		}
		
		return true;
	}
	
	static boolean checkCol(int col) {
		int prev = 0;
		int remainArea = 0;
		for (int row = 0; row < mapSize; row++) {
			if (row == 0) {
				prev = map[row][col];
				remainArea = 1;
				continue;
			}
			
			if (Math.abs(prev - map[row][col]) >= 2) {
				return false;
			}
			
			if (prev == map[row][col]) {
				remainArea++;
			}
			// 내려가는 경우
			else if (prev > map[row][col]) {
				if (remainArea < 0) {
					return false;
				}
				remainArea = -blockLength + 1;
				prev = map[row][col];
			}
			// 올라가는 경우
			else if (prev < map[row][col]) {
				if (remainArea < blockLength) {
					return false;
				}
				remainArea = 1;
				prev = map[row][col];
			}
		}
		
		if (remainArea < 0) {
			return false;
		}
		
		return true;
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		mapSize = Integer.parseInt(st.nextToken());
		blockLength = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
	}
}
