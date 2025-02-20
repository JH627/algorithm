import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 도착해야하는 사다리 맨 아래에서 역으로 출발
 * 현재 위치에서 한 칸씩 올라가며 양 옆을 검사한다.
 * 만약 사다리가 가로로 있는 경우를 발견하면 옆 사다리로 이동
 * 맨 위까지 왔을 때의 col값을 반환
 * 
 * 
 * 옆 사다리로 이동할 때 미리 사다리의 col값을 저장해놓아 빠르게 이동한다.
 * (한 막대에서 출발한 가로선이 다른 막대를 가로질러서 연속하여 이어지는 경우는 없기 때문)
 * 
 * 도착 지점이나 출발 지점이 두 개인 경우는 없으므로
 * 양 옆 검사 대상에서
 * 맨 아래 맨 위는 제외 
 *
 */
public class SWEA_1210_Ladder1 {
	
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int TC = 10; // 테스트 케이스 수
	static final int MAP_SIZE = 100; // 맵 사이즈
	static final int[] ADD_COL = {-1, 1}; // 양 옆을 확인할 때 사용할 배열
	
	static boolean map[][]; // 맵
	static ArrayList<Integer> ladderColPoints; // 사다리의 세로 인덱스를 저장할 배열
	static int endLadderColPointIndex; // 현재 사다리 번호를 저장할 변수

	public static void main(String[] args) throws Exception {		
		StringBuilder sb = new StringBuilder();
				
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			sb.append("#").append(testCase).append(" ").append(solve()).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int solve() {
		// X표시에서 스타트
		int nowLadderIndex = endLadderColPointIndex;
		// 현재의 col 값은 현재 위치한 사다리의 col값
		int nowCol = ladderColPoints.get(nowLadderIndex);
		
		// 한칸씩 위로 올라가며 탐색 맨 아래 맨 위는 제외
		for (int row = MAP_SIZE - 2; row > 0; row--) {
			
			// 양옆을 검사
			for (int addColIndex = 0; addColIndex < 2; addColIndex++) {
				// 양 옆의 col값
				int newCol = nowCol + ADD_COL[addColIndex];
				
				// 만약 새로운 col 값이 map 범위 밖인 경우 무시하고 다음 경우의 수 진행
				if (newCol < 0 || newCol >= MAP_SIZE) {
					continue;
				}
				
				// 양 옆에 사다리가 있는 경우
				if (map[row][newCol]) {
					// 왼쪽에  사다리가 있는 경우
					if (addColIndex == 0) {
						// 현재  col값을 왼쪽 사다리의 col값으로 변경
						nowCol = ladderColPoints.get(--nowLadderIndex);
					}
					// 오른쪽에  사다리가 있는 경우
					else {
						// 현재  col값을 오른쪽 사다리의 col값으로 변경
						nowCol = ladderColPoints.get(++nowLadderIndex);
					}
					break;
				}
			}
		}
		
		// 마지막 col값을 반환
		return nowCol;
	}

	static void init() throws IOException {
		map = new boolean[MAP_SIZE][MAP_SIZE];
		ladderColPoints = new ArrayList<>();
		
		int caseNum = Integer.parseInt(br.readLine());
		for (int row = 0; row < MAP_SIZE; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < MAP_SIZE; col++) {				
				// 사다리의 상태 (0, 1, 2)
				int status = Integer.parseInt(st.nextToken());
				// 끝나는 지점 기록
				if (row == MAP_SIZE -1 && status != 0) {
					ladderColPoints.add(col);
					if (status == 2) {
						endLadderColPointIndex = ladderColPoints.size() - 1;
					}
				}
				// 사다리나 끝나는 지점이 있는 경우
				if (status != 0) {
					map[row][col] = true;
				}
			}
		}
	}

}
