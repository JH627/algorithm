import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 풀이
 *
 * 문제 오류
 * 테스트 케이스 반례있음
 * 하단에 첨부
 *
 * 조합을 이용해 약품을 투입할 공간을 찾아보자
 *
 * ===
 * 생각해볼 수 있는 조건
 * - 조합에 포함된 약품의 개수는 K개 이하
 *   => 어차피 K개면 일렬로 넣으면 됨
 * - A약품을 넣는 경우 이어지는 곳에는 A약물만
 *   => B약품을 등장하기 위해서는 A가 등장 한 후 최소 K개 이후
 *   => K개 이전에 등장하면 A가 K개 연속 등장하지 못함이 보장
 *
 * - 충격조건에 맞는지 확인 위해서는 O(depth * width) 탐색 필요
 *   => 조건에 안 맞는 경우가 하나라도 발견되면 탐색 중단
 *
 * ===
 *
 * 1. 위 조건을 이용해서 약품을 0개 넣는 경우부터 합격 기준 - 1개 까지 탐색
 * 2. 조합을 사용해서 약품을 넣을 수 있는 경우의 수 탐색
 * 3. 1번에서 설정한 약품 개수 제한에 도달하면 시뮬레이션 -> 합격 조건을 만족하는지 확인
 * 3-1. 만약 합격 조건을 만족한다면 현재 약품 제한 개수 출력
 * 3-2. 합격 조건을 만족하지 않는다면 다른 조합 확인
 *
 */
public class SWEA_2112_보호필름 {

	static BufferedReader br;

	static int depth, width, criteria; // 깊이, 너비, 합격기준
	static int[][] films; // 보호필름 값
	static int[] selected; // default: -1, A: 0, B: 1

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			// 약품을 0개 넣는 경우부터 합격 기준 - 1개 까지 탐색
			// 합격 기준인 경우 어차피 한줄로 쭉 넣으면 통과이기때문에 탐색 필요 없음
			int count = criteria;
			for (int selectLimit = 0; selectLimit < criteria; selectLimit++) {
				// 만약 해당 약품 개수 제한에서 성공한 케이스가 있다면
				// 현재 약품 개수 제한 출력
				if (test(0, 0, selectLimit, -criteria)) {
					count = selectLimit;
					break;
				}
			}

			sb.append("#").append(testCase).append(" ").append(count).append("\n");
		}

		System.out.print(sb);
	}

	static boolean test(int elementIndex, int selectCount, int selectLimit, int lastA) {
		if (selectCount == selectLimit) {
			// 약품 A와 B를 넣는 경우를 뒤집어서 테스트
			// 만약 조건을 만족하는 경우가 있는 경우 true 반환
			return checkIsPossible(true) || checkIsPossible(false);
		}

		// 요소를 끝까지 탐색했거나, 뒷 요소를 모두 골라도 선택 제한 개수를 채울 수 없는 경우 분기 종료
		if (elementIndex == depth || selectLimit - selectCount > depth - elementIndex) {
			return false;
		}

		// A 투입
		selected[elementIndex] = 0;
		if (test(elementIndex + 1, selectCount + 1, selectLimit, elementIndex)) {
			return true;
		}

		// B 투입 (최소 K개 이후 가능)
		if (elementIndex > lastA + (criteria - 1)) {
			selected[elementIndex] = 1;
			if (test(elementIndex + 1, selectCount + 1, selectLimit, lastA)) {
				return true;
			}
		}

		// 변경 없이 다음 진행
		selected[elementIndex] = -1;
		return test(elementIndex + 1, selectCount, selectLimit, lastA);
	}


	// 보호필름이 합격 조건인지 체크
	static boolean checkIsPossible(boolean reverse) {
		int clock = 0; // 연속된 개수
		for (int widthIndex = 0; widthIndex < width; widthIndex++) {
			boolean possible = false; // 현재 열이 가능한가
			int prev = -1; // 이전에 등장한 수
			for (int depthIndex = 0; depthIndex < depth; depthIndex++) {
				// 만약 A나 B 약물이 투입된 곳이면 해당 값으로 대체, 아닌 경우 기존 값으로 현재 값 설정
				// reverse: A약물과 B약물 위치 뒤집기
				int now = (selected[depthIndex] != -1) ? ((reverse) ? selected[depthIndex] : (selected[depthIndex] + 1) % 2) : films[depthIndex][widthIndex];
				
				// 이전에 등장한 값과 다르다면
				if (prev != now) {
					// 길이는 다시 1
					clock = 1;
					prev = now;
				}
				// 이전에 등장한 값과 같다면
				else {
					// 길이 1 증가 
					clock++;
				}

				// 길이 제한에 도달하면 열 탐색 조기 종료
				if (clock == criteria) {
					possible = true;
					break;
				}
			}
			
			// 만약 하나의 열이라도 조건을 만족 못하면 더이상 탐색할 필요 없음
			if (!possible) {
				return false;
			}
		}
	
		// 모두 만족
		return true;
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		depth = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		criteria = Integer.parseInt(st.nextToken());

		films = new int[depth][width];
		selected = new int[depth];
		// 0과 1로 A, B를 나타내기 때문에 -1로 설정
		Arrays.fill(selected, -1);

		for (int depthIndex = 0; depthIndex < depth; depthIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int widthIndex = 0; widthIndex < width; widthIndex++) {
				films[depthIndex][widthIndex] = Integer.parseInt(st.nextToken());
			}
		}
	}
}

// 무작정 A 필름을 먼저 넣는 케이스가 통과됨
// B를 먼저 넣는 경우에 최소값이 나오는 경우가 있음
// 테스트 케이스 반례
/*
입력
2
12 2 6
1 1
1 1
1 1
1 0
1 0
0 1
0 1
0 0
0 0
1 0
1 0
1 0
12 2 6
0 0
0 0
0 0
0 1
0 1
1 0
1 0
1 1
1 1
0 1
0 1
0 1

정답 출력
#1 2
#2 2
*/
