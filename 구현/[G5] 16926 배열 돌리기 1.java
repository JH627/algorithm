import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 배열을 직접 돌려보자
 * 
 * 1. 문제 조건에 min(N, M) mod 2 = 0 가 있기 때문에 반드시 2로 나누어 떨어지는 rowSize, colSize가 존재
 * 2. 따라서 회전 층을 나누고 층의 개수를 구할 수 있음 => 층의 개수 = Math.min(rowSize, colSize) / 2
 * 3. 각 층 마다 회전 => 총 rotateCount 회 만큼 진행
 * 4. 배열 회전 (layerNumber: 층 depth)
 * 4-1. 맨 처음 값 남기기 (덮어씌우는 경우 날아가기때문)
 * 4-2. 상단 부분 왼쪽으로 (오른쪽 값으로 왼쪽을 덮어씌움)
 *      originArray[layerNumber][index] = originArray[layerNumber][index + 1];
 * 4-3. 우측 부분 위쪽으로  (아래쪽 값으로 위쪽을 덮어씌움)
 *      originArray[index][colSize - 1 - layerNumber] = originArray[index + 1][colSize - 1 - layerNumber];
 * 4-4. 하단부분 오른쪽으로 (왼쪽 값으로 오른쪽을 덮어씌움)
 *      originArray[rowSize - 1 - layerNumber][index] = originArray[rowSize - 1 - layerNumber][index - 1];
 * 4-5. 좌측부분 아래쪽으로 (위쪽 값으로 아래쪽을 덮어씌움)
 *      originArray[index][layerNumber] = originArray[index - 1][layerNumber];
 * 4-6. 맨처음에 덮어 씌워진 값 좌측 배열 상단에서 두번째에 복원 => 한칸씩 아래로 내려온 효과
 *
 */
public class BOJ_16926_배열돌리기1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int rowSize, colSize, rotateCount;
	static int[][] originArray; // 값을 저장할 공간

	public static void main(String[] args) throws IOException {
		init();
		
		// 배열을 주어진 회전의 수 만큼 회전
		for (int rotateNumber = 0; rotateNumber < rotateCount; rotateNumber++) {
			rotateArray();
		}	
		
		printArray();
	}
	
	
	static void rotateArray() {
		// 회전 층을 나누고 층의 개수를 구하기 위함
		int layer = Math.min(rowSize, colSize) / 2;
		
		for (int layerNumber = 0; layerNumber < layer; layerNumber++) {
			// 맨 처음 값 남기기
			int firstValue = originArray[layerNumber][layerNumber];
			
			// 상단부분 왼쪽으로
			for (int index = layerNumber; index < colSize - layerNumber - 1; index++) {
				originArray[layerNumber][index] = originArray[layerNumber][index + 1];
			}
			
			// 우측부분 위쪽으로
			for (int index = layerNumber; index < rowSize - layerNumber - 1; index++) {
				originArray[index][colSize - 1 - layerNumber] = originArray[index + 1][colSize - 1 - layerNumber];
			}
			
			// 하단부분 오른쪽으로
			for (int index = colSize - layerNumber - 1; index > layerNumber; index--) {
				originArray[rowSize - 1 - layerNumber][index] = originArray[rowSize - 1 - layerNumber][index - 1];
			}
						
			// 좌측부분 아래쪽으로
			for (int index = rowSize - layerNumber - 1; index > layerNumber; index--) {
				originArray[index][layerNumber] = originArray[index - 1][layerNumber];
			}
			
			// 맨처음 값 복원
			originArray[layerNumber + 1][layerNumber] = firstValue;
		}
	}
	
	// 배열 출력 함수
	static void printArray() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				sb.append(originArray[row][col]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		rotateCount = Integer.parseInt(st.nextToken());
		
		originArray = new int[rowSize][colSize];
		
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				originArray[row][col] = Integer.parseInt(st.nextToken());
			}			
		}
	}
	
}
