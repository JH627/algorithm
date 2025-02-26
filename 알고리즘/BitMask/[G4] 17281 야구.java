import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 나올 수 있는 타순의 경우의 수를 계산한다.
 * 2. 만약 타순이 다 결정되었다면 경기를 시뮬레이션한다.
 * 3. 각 이닝을 시작하면 필드의 상태를 초기화한다.
 * 4. 선수의 결과의 따라 경기를 진행한다.
 * 4-1. 만약 아웃이 3개이상 쌓인 경우 다음 이닝 진행
 * 4-2. 홈런인 경우 현재 필드에 있는 선수 + 1 을 점수에 더하고 필드를 비운다.
 * 4-3. 그 외(안타, 2루타, 3루타)의 경우는 결과에 따라 필드에서 나가게 되는 선수의 수를 확인 후 점수에 더하고
 *      선수들을 결과에 따라 이동시킨다.
 * 
 */
public class BOJ_17281_야구 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static final int MEMBER_COUNT = 9; // 한 팀의 선수의 수
	
	static int gameCount; // 이닝 수
	static int maxScore; // 최고 점수
	static int[][] power; // 각 선수의 이닝 결과 
	static int[] sequence; // 타순
	static boolean[] isSelected; // 타순 생성시 선수가 현재 타순에 들어가 있는지 확인할 배열
	
	public static void main(String[] args) throws Exception {
		init();
		
		makeSequence(0);
		
		System.out.print(maxScore);
	}
	
	static void makeSequence(int selectCount) {
		// 4번타자는 이미 골라져있으므로 패스
		if (selectCount == 3) {
			makeSequence(selectCount + 1);
			return;
		}
		
		// 타순을 다 설정했다면 게임 시뮬레이션
		if (selectCount == MEMBER_COUNT) {
			playGame();
			return;
		}
		
		// 선수를 하나씩 확인하며
		for (int index = 0; index < MEMBER_COUNT; index++) {
			// 만약 타순에 포함이 안되었다면
			if (!isSelected[index]) {
				// 타순에 넣고 다음 선수 고르기
				isSelected[index] = true;
				sequence[selectCount] = index;
				makeSequence(selectCount + 1);
				isSelected[index] = false;
			}
		}
	}
	
	// 경기 시뮬레이션 함수
	static void playGame() {
		int sequenceIndex = 0; // 지금 타자
		int boardStatus = 0; // 필드 상태
		
		int score = 0; // 점수
		
		for (int round = 0; round < gameCount; round++) {
			int outCount = 0; // 아웃 카운트
			boardStatus = 0; // 라운드 시작때마다 보드 초기화
			
			while (true) {
				// 현재 선수 기량
				int nowResult = power[round][sequence[sequenceIndex]];
				
				// 다음에 올 선수 세팅
				sequenceIndex = (sequenceIndex + 1) % MEMBER_COUNT;
				
				// 아웃인 경우
				if (nowResult == 0) {
					// 아웃 세번시 경기 종료
					if (++outCount == 3) {
						break;
					}
					continue;
				}
				
				// 홈런인 경우
				if (nowResult == 4) {
					// 필드에 있던 선수 점수
					score += Integer.bitCount(((1 << 3) - 1) & boardStatus);
					// 홈런친사람 점수
					score++;
					// 필드에는 이제 아무도 없다 
					boardStatus = 0;
					continue;
				}
				
				// 안타, 2루타, 3루타
				// ex> 2루타의 경우 2, 3루에 있는 선수가 나가게 되므로 '110' 상태의 비트를 마스킹하여 1의 개수 확인 
				int escapeMask = ((1 << 3) - 1) - ((1 << (3 - nowResult)) - 1);
				// 나간 선수만큼 점수 올리기
				score += Integer.bitCount(boardStatus & escapeMask);
				
				// 선수를 이동시키고
				boardStatus = (boardStatus << nowResult);
				// 방금 친 타자를 결과에 따라 이동
				boardStatus += (1 << (nowResult - 1));
			}			
		}
		
		maxScore = Math.max(maxScore, score);
	}
	
	static void init() throws Exception {
		gameCount = Integer.parseInt(br.readLine());
		power = new int[gameCount][MEMBER_COUNT];
		sequence = new int[MEMBER_COUNT];
		isSelected = new boolean[MEMBER_COUNT];
		
		StringTokenizer st;
		for (int game = 0; game < gameCount; game++) {
			st = new StringTokenizer(br.readLine());
			for (int memberIndex = 0; memberIndex < MEMBER_COUNT; memberIndex++) {
				power[game][memberIndex] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1번 선수는 4번 타자로 미리 결정
		sequence[3] = 0;
		isSelected[0] = true;
		
		maxScore = Integer.MIN_VALUE;
	}
}
