import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Combination 실습 문제
 * 
 * 햄버거 만들기
 * 유의 사항
 * - 같은 재료는 여러번 사용 불가
 * - 아무것도 넣지 않는 경우는 고려할 필요가 없음 (어차피 최소값)
 *
 * 풀이
 * 1. 각 재료의 점수와 칼로리를 입력 받은 후
 * 2. 칼로리를 기준으로 재료를 내림차순으로 정렬
 * 3. 함수의 조기종료를 위해 누적합을 만든 배열을 생성
 * 4. 역순으로 누적합을 계산
 * 5. 조합 알고리즘을 통해 가능한 최대의 경우의 수를 계산
 * 5-1. 모든 햄버거 재료를 다 살펴본 경우 종료
 * 5-2. 만약 아래에 있는 모든 재료의 칼로리를 합한 값이 현재 남은 칼로리보다 적은 경우 모두 더하고 함수 조기 종료
 * 5-3. 아래에 있는 재료들을 모두 넣어도 최대치를 갱신하지 못하는 경우 조기 종료
 * 5-4-1. 현재 재료를 넣지 않고 진행
 * 5-4-2. 남은 칼로리가 모자란 경우 해당 재료를 넣지 않고 진행
 * 
 */
public class SWEA_5215_햄버거다이어트 {

	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	// 햄버거 재료
	static class Component implements Comparable<Component> {
		int score;
		int calorie;
		
		public Component(int score, int calorie) {
			this.score = score;
			this.calorie = calorie;
		}

		// 내림차순으로 정렬하기 위한 조건
		@Override
		public int compareTo(Component o) {
			return o.calorie - this.calorie;
		}
	}
	
	static ArrayList<Component> components; // 재료들을 저장할 배열
	static int component_count, maxCalorie; // component_count: 재료의 개수, maxCalorie: 최대 칼로리
	static int[] sumScore, sumCalorie; // sumScore: 정렬 후 점수들의 누적합, sumCalorie: 정렬 후 칼로리들의 누적합
	static int maxValue = 0; // 최대 값
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			makeBurger(0, maxCalorie, 0);
			
			sb.append("#").append(testCase).append(" ").append(maxValue).append("\n");
		}
		
		System.out.print(sb);

	}
	
	static void makeBurger(int componentIndex, int remainCalories, int currentScore) {		
		// 모든 햄버거 재료를 다 살펴본 경우 종료
		if (componentIndex == component_count) {
			maxValue = Math.max(maxValue, currentScore);
			return;
		}
		
		// 아래에 있는 재료를 모두 넣어도 되는 경우 조기 종료
		if (sumCalorie[componentIndex] <= remainCalories) {
			maxValue = Math.max(maxValue, currentScore + sumScore[componentIndex]);
			return;
		}
		
		// 아래에 있는 재료들을 모두 넣어도 최대치를 갱신하지 못하는 경우 조기 종료
		if (currentScore + sumScore[componentIndex] <= maxValue) {
			return;
		}
		
		// 현재 조합으로 최대값 갱신
		maxValue = Math.max(maxValue, currentScore);
		
		int nowScore = components.get(componentIndex).score;
		int nowCalorie = components.get(componentIndex).calorie;
		
		// 현재 재료를 선택하는 경우
		// 칼로리가 모자라다면 패스
		if (remainCalories - nowCalorie >= 0) {
			makeBurger(componentIndex + 1, remainCalories - nowCalorie, currentScore + nowScore);
		}

		// 현재 재료를 선택하지 않는 경우
		makeBurger(componentIndex + 1, remainCalories, currentScore);
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		component_count = Integer.parseInt(st.nextToken());
		maxCalorie = Integer.parseInt(st.nextToken());
		
		components = new ArrayList<>();
		sumScore = new int[component_count];
		sumCalorie = new int[component_count];
		
		for (int componentIndex = 0; componentIndex < component_count; componentIndex++) {
			st = new StringTokenizer(br.readLine());
			int score = Integer.parseInt(st.nextToken());
			int calorie = Integer.parseInt(st.nextToken());
			components.add(new Component(score, calorie));			
		}
		
		// 내림차순으로 정렬
		Collections.sort(components);
		
		// 역순으로 누적합을 계산
		sumCalorie[component_count - 1] = components.get(component_count - 1).calorie;
		sumScore[component_count - 1] = components.get(component_count - 1).score;
		for (int index = component_count - 2; index >= 0; index--) {
			sumCalorie[index] = sumCalorie[index + 1] + components.get(index).calorie;
			sumScore[index] = sumScore[index + 1] + components.get(index).score;
		}
		
		maxValue = 0;
	}

}
