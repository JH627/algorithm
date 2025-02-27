import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 *
 * 1. 확인할 점수를 입력받는다.
 * 2. 경기를 진행시켜본다.
 * 2-1. 만약 경기수가 15번에 도달한 경우 가능한 경우의 수이므로 1 출력
 * 2-2. 15번 아래인 경우 아직 게임을 모두 진행하지 않은 팀을 찾아서 다른 팀과 경기를 진행
 *      진행 시에는 승, 무, 패로 분기하여 진행
 * 2-3. 분기 시에는 처음 입력받은 값을 초과하는 상태로는 분기하지 않음
 *      (예> A팀이 3승해야하는데 4승으로 분기하는 경우는 없다)
 *
 */
public class BOJ_6987_월드컵 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static final int TEAM_COUNT = 6; // 팀의 개수
	static final int GAME_COUNT = 15; // 게임 수 (5 + 4 + 3 + 2 + 1)

	static int[][] currentResult; // 현재 각 팀의 승, 무, 패
	static int[] played, needCheckResults; // played: 어떤 팀과 경기를 했는지 비트에 기록, needCheckResults: 확인해야하는 결과
	static int allPlayed; // 모든 팀들과 경기한 상태를 나타냄 (1 << TEAM_COUNT) - 1
	static boolean possible, inputError; // possible: 주어진 결과가 가능한 결과인지, inputError: 입력값이 잘못된 경우 ex> A팀 6승

	public static void main(String[] args) throws Exception {
		for (int checkIndex = 0; checkIndex < 4; checkIndex++) {
			init();

			// 입력에서 나올 수 없는 경우의 수가 나온 경우
			if (inputError) {
				System.out.print("0 ");
				continue;
			}

			// 경기 진행
			makeWorldCupResults(0);
			System.out.printf(possible ? "1 " : "0 ");
		}
	}

	static void makeWorldCupResults(int gameCount) {
		// 15번 다 경기를 진행한 경우는 가능한 경우가 주어진 것임
		if (gameCount == GAME_COUNT) {
			possible = true;
		}

		// 다른 분기에서 경기 결과가 확인된 경우 더이상 분기 X
		if (possible) {
			return;
		}

		// 아직 모든 팀과 경기를 하지 않은 팀을 찾음
		int teamA = 0;
		for (int teamIndex = 0; teamIndex < TEAM_COUNT; teamIndex++) {
			if (played[teamIndex] != allPlayed) {
				teamA = teamIndex;
				break;
			}
		}

		// 그 팀과 경기하지 않은 한 팀을 찾아 경기
		for (int teamIndex = 0; teamIndex < TEAM_COUNT; teamIndex++) {
			// 경기 안한 팀 찾기
			if ((played[teamA] & (1 << teamIndex)) != 0) {
				continue;
			}

			int teamB = teamIndex;

			// 승 (input으로 주어진 결과 이상으로는 분기하지 않는다)
			if (currentResult[teamA][0] + 1 <= needCheckResults[teamA * 3 + 0] && currentResult[teamB][2] + 1 <= needCheckResults[teamB * 3 + 2]) {
				// 경기 결과 반영
				updateResults(teamA, 0, teamB, 2, false);
				makeWorldCupResults(gameCount + 1);
				// 경기 결과 복구 (후처리)
				updateResults(teamA, 0, teamB, 2, true);
			}

			// 무 (input으로 주어진 결과 이상으로는 분기하지 않는다)
			if (currentResult[teamA][1] + 1 <= needCheckResults[teamA * 3 + 1] && currentResult[teamB][1] + 1 <= needCheckResults[teamB * 3 + 1]) {
				// 경기 결과 반영
				updateResults(teamA, 1, teamB, 1, false);
				makeWorldCupResults(gameCount + 1);
				// 경기 결과 복구 (후처리)
				updateResults(teamA, 1, teamB, 1, true);
			}

			// 패 (input으로 주어진 결과 이상으로는 분기하지 않는다)
			if (currentResult[teamA][2] + 1 <= needCheckResults[teamA * 3 + 2] && currentResult[teamB][0] + 1 <= needCheckResults[teamB * 3 + 0]) {
				// 경기 결과 반영
				updateResults(teamA, 2, teamB, 0, false);
				makeWorldCupResults(gameCount + 1);
				// 경기 결과 복구 (후처리)
				updateResults(teamA, 2, teamB, 0, true);
			}

			break;
		}
	}

	// 경기 결과 update (restore: 경기 결과 적용, 복구)
	static void updateResults(int teamA, int aResult, int teamB, int bResult, boolean restore) {
		int delta = restore ? -1 : 1;

		currentResult[teamA][aResult] += delta;
		currentResult[teamB][bResult] += delta;

		if (restore) {
			played[teamA] &= ~(1 << teamB);
			played[teamB] &= ~(1 << teamA);
		} else {
			played[teamA] |= (1 << teamB);
			played[teamB] |= (1 << teamA);
		}
	}


	static void init() throws Exception {
		currentResult = new int[TEAM_COUNT][3];
		played = new int[TEAM_COUNT];
		needCheckResults = new int[TEAM_COUNT * 3];

		// 자기 자신과는 경기할 필요가 없으므로 자기 자신은 경기했다고 표시
		for (int teamIndex = 0; teamIndex < TEAM_COUNT; teamIndex++) {
			played[teamIndex] = (1 << teamIndex);
		}

		possible = false;
		inputError = false;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int index = 0; index < TEAM_COUNT * 3; index++) {
			int value = Integer.parseInt(st.nextToken());
			needCheckResults[index] = value;
			// 나올 수 없는 경우의 수
			if (value > 5) {
				inputError = true;
			}
		}
		
		allPlayed = (1 << TEAM_COUNT) - 1;
	}
}
