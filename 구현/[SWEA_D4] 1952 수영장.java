import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백트래킹 활용
 * 
 * 풀이
 * 
 * 1. 달을 하나씩 살펴보며
 * 1-1. 만약 0일 운동할 계획이라면 현재 가격을 그대로 가지고 다음 달 확인
 * 1-2. 1일 이상 운동할 계획이라면 3가지 경우로 분기
 * 1-2-1. 일일권으로 이번달을 다니고 다음 달 확인
 * 1-2-2. 한달권으로 이번달을 다니고 다음 달 확인
 * 1-2-3. 3달권으로 이번달, 다음달, 다다음달을 다니고 다다다음달 확인
 * 1-2-4. 1년권으로 이번년을 다니고 결과 확인
 *
 */
public class SWEA_1952_수영장 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int dailyFee, monthFee, threeMonthFee, yearFee; // 일일권, 한달권, 3달권, 1년권 가격
	static int minFee; // 가격 싼 방법
	static int[] usePlan; // 운동 계획
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			getMinFee(0, 0);

			sb.append("#").append(testCase).append(" ").append(minFee).append("\n");
		}

		System.out.print(sb);
	}
	
	static void getMinFee(int month, int currentFee) {
		// 작년계획을 모두 세운 경우 최소 가격 최신화
		if (month >= 12) {
			minFee = Math.min(minFee, currentFee);
			return;
		}
		
		// 이번 달은 운동을 할 계획이 없으니 패스
		if (usePlan[month] == 0) {
			getMinFee(month + 1, currentFee);
			return;
		}
		
		// 일일권으로 이번달을 다니고 다음 달 확인
		getMinFee(month + 1, currentFee + usePlan[month] * dailyFee);
		// 한달권으로 이번달을 다니고 다음 달 확인
		getMinFee(month + 1, currentFee + monthFee);
		// 3달권으로 이번달, 다음달, 다다음달을 다니고 다다다음달 확인
		getMinFee(month + 3, currentFee + threeMonthFee);
		// 1년권으로 이번년을 다니고 결과 확인
		getMinFee(month + 12, currentFee + yearFee);
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		dailyFee = Integer.parseInt(st.nextToken());
		monthFee = Integer.parseInt(st.nextToken());
		threeMonthFee = Integer.parseInt(st.nextToken());
		yearFee = Integer.parseInt(st.nextToken());
		
		// 운동계획
		usePlan = new int[12];
		st = new StringTokenizer(br.readLine()); 
		for (int index = 0; index < 12; index++) {
			usePlan[index] = Integer.parseInt(st.nextToken());
		}
		
		minFee = Integer.MAX_VALUE;
	}
	
}
