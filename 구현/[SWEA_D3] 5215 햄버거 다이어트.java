import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Permutation 활용
 * 처음 입력을 받고 내 카드와 상대 카드를 배열에 저장
 * 순열 생성 알고리즘을 통하여 상대가 낼 수 있는 모든 경우의 수를 계산
 * 순열 생성과 동시에 점수를 체크하여 만약 상대 패가 다 완성이 되었을 때 승패를 계산한다.
 *
 */
public class SWEA_6808_규영이와인영이의카드게임 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int HAND_SIZE = 9;
	static int[] myCards, enemyCards, enemyHand; // 내 패, 상대가 가지고 있는 카드, 순열 생성을 통해 만들어진 현재 상대의 패
	static boolean[] used; // 순열 생성 알고리즘에서 상대 패가 사용되었는지 체크하는 배열
	static int winCount, loseCount;
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			makeDeck(0, 0, 0);
			
			sb.append("#").append(testCase).append(" ").append(winCount).append(" ").append(loseCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 현재 상대 패에 따른 점수를 파라미터로 넘긴다.
	static void makeDeck(int enemyCardIndex, int myScore, int enemyScore) {
		// 만약 상대 패가 다 완성이 되었다면 승패를 계산
		if (enemyCardIndex == HAND_SIZE) {
			// 내 점수가 더 높은 경우
			if (myScore > enemyScore) {
				winCount++;
			}
			// 상대 점수가 더 높은 경우
			else if (myScore < enemyScore){
				loseCount++;
			}
			return;
		}
		
		// 아직 상대 패가 다 완성이 안되었다면
		for (int index = 0; index < HAND_SIZE; index++) {
			// 아직 사용하지 않은 카드를 패에 추가
			if (!used[index]) {
				enemyHand[enemyCardIndex] = enemyCards[index];
				used[index] = true; // 카드 사용 표시
				// 이긴 사람이 가져갈 점수를 구한다.
				int sum = enemyHand[enemyCardIndex] + myCards[enemyCardIndex];
				// 만약 현재 결정된 상대의 패가 이번라운드에 내가 낼 카드보다 큰 경우
				// 상대에게 점수를 준다.
				if (enemyHand[enemyCardIndex] > myCards[enemyCardIndex]) {
					makeDeck(enemyCardIndex + 1, myScore, enemyScore + sum);
				}
				// 만약 현재 결정된 상대의 패가 이번라운드에 내가 낼 카드보다 작은 경우
				// 나에게 점수를 준다.
				else {
					makeDeck(enemyCardIndex + 1, myScore + sum, enemyScore);
				}
				used[index] = false; // 후처리
			}	
		}	
	}

	static void init() throws IOException {
		myCards = new int[HAND_SIZE];
		enemyCards = new int[HAND_SIZE];
		enemyHand = new int[HAND_SIZE];
		used = new boolean[HAND_SIZE];
		winCount = 0;
		loseCount = 0;
		
		boolean[] count = new boolean[HAND_SIZE * 2 + 1]; // 숫자가 등장했는지 확인할 배열
		
		st = new StringTokenizer(br.readLine());
		for (int cardCount = 0; cardCount < HAND_SIZE; cardCount++) {
			myCards[cardCount] = Integer.parseInt(st.nextToken());
			count[myCards[cardCount]] = true;
		}
		
		// 등장하지 않은 숫자는 상대가 가지고 있는 카드 목록에 추가
		int index = 0;
		for (int num = 1; num < HAND_SIZE * 2 + 1; num++) {
			if (!count[num]) {
				enemyCards[index++] = num;
			}
		}
	}

}
