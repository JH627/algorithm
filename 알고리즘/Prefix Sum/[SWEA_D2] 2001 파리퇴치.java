import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 누적합 문제
 * 유의 사항
 * - 파리채는 M*M으로 항상 정사각형이다
 *
 * 풀이
 * 1. 배열을 입력받으며 각 칸의 누적합을 구한다.
 * 1-1. 입력받으며 오른쪽 위, 왼쪽 옆 값을 내 원래 값과 더한다.
 * 2. M*M의 공간이 나오는 경우 현재 누적합 값과 최대 값을 비교하여 갱신한다.
 * 2-1. 정사각형 공간의 누적합
 * 
 * M*M 공간의 값 계산 M == 2인 경우
 * ㅁㅁㅁㅁ       ㅇㅇㅇㅇ       ㅇㅇㅇㅇ       ㅇㅇㅁㅁ      ㅇㅇㅁㅁ
 * ㅁㅁㅁㅁ >  ㅇㅇㅇㅇ   - ㅇㅇㅇㅇ   - ㅇㅇㅁㅁ      ㅇㅇㅁㅁ
 * ㅁㅁ◼◼          ㅇㅇㅇㅇ       ㅁㅁ◼◼         ㅇㅇ◼◼         ㅁㅁ◼◼
 * ㅁㅁ◼◼          ㅇㅇㅇㅇ       ㅁㅁ◼◼         ㅇㅇ◼◼         ㅁㅁ◼◼
 *          (o값을 계산)                  (겹치는 공간은 두번 빠지므로 다시 더해줌) 
 */
public class SWEA_2001_파리퇴치 {

	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int mapSize, flapperSize; // mapSize: 맵 크기, flapperSzie: 파리채 크기
	static int maxValue = 0; // 최대값 
	static int[][] valueSum; // 누적합을 저장할 공간
	
	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			calculateFlapperPower();
			
			sb.append("#").append(testCase).append(" ").append(maxValue).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void calculateFlapperPower() throws Exception {
		// 1번 인덱스 부터 시작한다
		// 하나 전 인덱스에 접근해서 누적합을 구해야하기 때문
		for (int row = 1; row < mapSize + 1; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col < mapSize + 1; col++) {
				// 현재 칸의 원래 값
				int value = Integer.parseInt(st.nextToken());
				
				// 누적 합 계산
				valueSum[row][col] = value + valueSum[row - 1][col] + valueSum[row][col - 1] - valueSum[row - 1][col - 1];
				
				// 만약 M * M 공간이 확보 된 경우
                if (row >= flapperSize && col >= flapperSize) {
                	// M * M 공간의 값 계산 후 최대값 갱신
                    maxValue = Math.max(maxValue,
                            valueSum[row][col] 
                            - valueSum[row - flapperSize][col] 
                            - valueSum[row][col - flapperSize]
                            + valueSum[row - flapperSize][col - flapperSize]);
                }
			}
		}
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		flapperSize = Integer.parseInt(st.nextToken());
		
		valueSum = new int[mapSize + 1][mapSize + 1]; // 누적합 계산을 위해 한칸 더 크게 설정
		maxValue = 0;
	}
}
