import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Permutation 실습
 * 순열(Permutation) : 서로 다른 것들 중에 몇 개를 뽑아서 한 줄로 나열하는 것 (nPr)
 * 
 * Permutation 알고리즘
 * 1. N(elementCount)개의 요소 중에서 R(selectedCount)개를 선택하여 나열하는 모든 경우를 생성한다.
 * 2. 요소를 선택할 때, 중복을 허용하지 않도록 체크 배열(isSelected)을 사용
 * 3. 선택된 요소의 개수가 R(selectedCount)개가 되면 결과를 출력하고 재귀를 종료
 * 4. 선택된 요소를 원래 상태로 되돌려(후처리, 체크 배열 원복) 다음 경우의 수를 탐색한다.
 */
public class BOJ_15649_N과M1 {

	static StringBuilder sb = new StringBuilder();
	
	static int elementCount, selectedCount; // elementCount: 활용할 요소의 개수, selectedCount: 선택할 요소의 개수
	static int[] selectedNumbers; // 선택된 요소를 저장할 배열
	static boolean[] isSelected; // 요소가 선택되었는지 확인할 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		elementCount = Integer.parseInt(st.nextToken());
		selectedCount = Integer.parseInt(st.nextToken());
		
		selectedNumbers = new int[selectedCount];
		isSelected = new boolean[elementCount];
		
		permutation(0);
		
		System.out.print(sb);
	}
	
	static void permutation(int selectedIndex) {
		// 만약 R(selectedCount)개 만큼의 요소를 골랐다면 (기저조건)
		if (selectedIndex == selectedCount) {
			// 저장된 요소들을 출력
			for (int number : selectedNumbers) {
				sb.append(number).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 만약 아직 모든 요소를 다 고르지 못한 상태라면
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			// 중복이 생기지 않도록 선택하지 않은 원소들 중에서 선택하여
			if (!isSelected[elementIndex]) {
				// 배열에 저장하고
				selectedNumbers[selectedIndex] = elementIndex + 1;
				isSelected[elementIndex] = true;
				// 재귀적으로 함수 호출
				permutation(selectedIndex + 1);
				isSelected[elementIndex] = false;
			}
		}
		
	}

}
