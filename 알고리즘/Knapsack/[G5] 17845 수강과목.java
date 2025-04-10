import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 얻을 수 있는 최대 중요도를 계산한다.
 * 2-1. 만약 현재 계산중인 시간이 넣으려는 과목의 시간보다 짧은 경우
 * 	2-1-1. 과목을 하나 덜 넣었을 때, 현재 시간의 최대 중요도 값으로 초기화한다.
 * 2-2. 만약 현재 계산중인 시간이 넣으려는 과목의 시간보다 길거나 같은 경우
 * 	2-2-1. 과목을 하나 덜 넣었을 때, 현재 과목을 넣는 경우와 현재 과목을 넣지 않을 때의 값을 비교한다.
 *
 */
public class BOJ_17845_수강과목 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	
	static int timeLimit, subjectLimit;
	static int[] importance, time;
	static int[][] maxImportance;
	
	public static void main(String[] args) throws Exception {
		init();
		
		int maxValue = getMaxValue();
		
		System.out.println(maxValue);
	}
	
	static int getMaxValue() {
		maxImportance = new int[subjectLimit + 1][timeLimit + 1];
		
		for (int subjectIndex = 1; subjectIndex <= subjectLimit; subjectIndex++) {
			int prev = (subjectIndex + 1) % 2;
			int now = subjectIndex % 2;
			
			for (int t = 1; t <= timeLimit; t++) {
				if (time[subjectIndex] > t) {
					maxImportance[now][t] = maxImportance[prev][t];
				}
				else {
					maxImportance[now][t] = Math.max(
							maxImportance[prev][t - time[subjectIndex]] + importance[subjectIndex],
							maxImportance[prev][t]);
				}
			}
		}
		
		return maxImportance[subjectLimit % 2][timeLimit];
	} 

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		timeLimit = Integer.parseInt(st.nextToken());
		subjectLimit = Integer.parseInt(st.nextToken());
		
		importance = new int[subjectLimit + 1];
		time = new int[subjectLimit + 1];
		
		for (int subjectIndex = 1; subjectIndex < subjectLimit + 1; subjectIndex++) {
			st = new StringTokenizer(br.readLine());
			importance[subjectIndex] = Integer.parseInt(st.nextToken());
			time[subjectIndex] = Integer.parseInt(st.nextToken());
		}
	}
}
