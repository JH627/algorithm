import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  슬라이딩 윈도우 + 누적합
 *  
 *  1. 인덱스마다 문자의 등장 횟수를 배열에 저장
 *  2. 왼쪽부터 문자열의 길이를 고정한 상태로 누적합을 통해 각 문자가 몇번 등장했는지 계산
 *  3. 비밀번호 생성 조건에 맞는다면 정답을 1 올린다.
 *  	
 */
public class BOJ_12891_DNA비밀번호 {

	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int elementSize, selectSize, count;
	static String elements;
	static int[][] appearCount;
	static int[] limit;
	
	public static void main(String[] args) throws Exception {
		init();
		
		getPossiblePasswordSet();
		
		System.out.print(count);
	}
	
	static void getPossiblePasswordSet() {
		int elementsLength = elements.length();
		for (int index = 0; index <= elementsLength - selectSize; index++) {			
			if (appearCount[0][index + selectSize] - appearCount[0][index] < limit[0]) {
				continue;
			}
			if (appearCount[1][index + selectSize] - appearCount[1][index] < limit[1]) {
				continue;
			}
			if (appearCount[2][index + selectSize] - appearCount[2][index] < limit[2]) {
				continue;
			}
			if (appearCount[3][index + selectSize] - appearCount[3][index] < limit[3]) {
				continue;
			}
			count++;			
		}	
	}
	
	
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		elementSize = Integer.parseInt(st.nextToken());
		selectSize = Integer.parseInt(st.nextToken());
		
		elements = br.readLine();
		limit = new int[4];
		int elementsLength = elements.length();
		appearCount = new int[4][elementsLength + 1];
		for (int index = 0; index < elementsLength; index++) {
			char now  = elements.charAt(index); 
			appearCount[0][index + 1] = appearCount[0][index];
			appearCount[1][index + 1] = appearCount[1][index];
			appearCount[2][index + 1] = appearCount[2][index];
			appearCount[3][index + 1] = appearCount[3][index];
			
			if (now == 'A') {
				appearCount[0][index + 1]++;
			}
			else if (now == 'C') {
				appearCount[1][index + 1]++;
			}
			else if (now == 'G') {
				appearCount[2][index + 1]++;
			}
			else if (now == 'T') {
				appearCount[3][index + 1]++;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < 4; index++) {
			limit[index] = Integer.parseInt(st.nextToken());
		}
		
		count = 0;
	}
}
