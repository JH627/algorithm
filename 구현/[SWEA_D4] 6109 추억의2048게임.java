import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 이동이 한번 뿐이니 그냥 옮겨보자
 * 
 * (왼쪽으로 모이는 경우 예시)
 * 1. Stack을 활용
 * 2. 오른쪽 요소부터 왼쪽까지 모두 하나씩 스택에 넣는다
 * 2-1. 단, 0이라면 넣지않는다.
 * 
 * 3. 스택에서 하나씩 꺼내며 map의 왼쪽부터 채운다. (스택이 다 빌때까지)
 * 3-1. 마지막요소를 꺼낸경우 그냥 비어있는 칸중 가장 왼쪽에 채운다.
 * 
 * 3-2. 마지막 요소가 아닌경우 현재스택에 있는 요소와 비교
 * 3-2-a. 만약 숫자가 같다면 가장 왼쪽 비어있는 칸에 현재 스택에있는 요소 * 2를 넣고, 요소를 하나 더 pop한다.
 * 3-2-b. 만약 숫자가 다르다면 그냥 비어있는 칸중 가장 왼쪽에 채운다.
 * 
 */
public class SWEA_6109_추억의2048게임 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static String operation; // 움직일 방향
	static int[][] map; // 숫자들을 저장할 공간
	static int mapSize; // 맵의 크기
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			merge();

			// 맵 출력
			sb.append("#").append(testCase).append("\n");
			for (int row = 0; row < mapSize; row++) {
				for (int col = 0; col < mapSize; col++) {
					sb.append(map[row][col]).append(" ");
				}
				sb.append("\n");
			}
		}

		System.out.print(sb);
	}
	
	// 숫자들을 이동시키는 함수
	static void merge() {
		Stack<Integer> numbers = new Stack<>();
		
		int index;
		switch (operation) {
			// 왼쪽으로 몰아보자
			case "left":
				for (int row = mapSize - 1; row >= 0; row--) {
					// 왼쪽으로 모아야하므로 0 인덱스부터 채움
					index = 0;
					// 오른쪽 요소부터 스택에 추가
					for (int col = mapSize - 1; col >= 0; col--) {
						if (map[row][col] != 0) {
							numbers.add(map[row][col]);
						}
					}
					
					// 스택이 빌때까지 반복
					while (!numbers.isEmpty()) {
						// 요소를 하나 꺼냄
						int now = numbers.pop();
						
						// 만약 마지막 요소라면 비어있는 인덱스중 가장 왼쪽에 저장
						if (numbers.isEmpty()) {
							map[row][index++] = now;
						}
						// 마지막 요소가 아니라면
						else {
							// 스택의 가장 윗 요소와 비교
							// 같다면
							if (numbers.peek() == now) {
								// 2배 값을 배열에 채우고, 스택에서 하나더 꺼냄
								map[row][index++] = now * 2;
								numbers.pop();
							}
							else {
								// 같지 않다면 방금 꺼낸 값을 비어있는 인덱스 중 가장 왼쪽에 채움
								map[row][index++] = now;
							}
						}
					}
					
					// 비어있는 칸은 0으로 채우기
					for (int mapIndex = index; mapIndex < mapSize; mapIndex++) {
						map[row][mapIndex] = 0;
					}
				}
				
				break;
				
				
			case "right":
				// 오른쪽으로 몰아보자
				for (int row = 0; row < mapSize; row++) {
					// 오른쪽으로 모아야하므로 mapSize - 1 인덱스부터 채움
					index = mapSize - 1;
					// 왼쪽 요소부터 스택에 추가
					for (int col = 0; col < mapSize; col++) {
						if (map[row][col] != 0) {
							numbers.add(map[row][col]);
						}
					}
					
					// 스택이 빌때까지 반복
					while (!numbers.isEmpty()) {
						// 요소를 하나 꺼냄
						int now = numbers.pop();
						
						// 만약 마지막 요소라면 비어있는 인덱스중 가장 오른쪽에 저장
						if (numbers.isEmpty()) {
							map[row][index--] = now;
						}
						// 마지막 요소가 아니라면
						else {
							// 스택의 가장 윗 요소와 비교
							// 같다면
							if (numbers.peek() == now) {
								// 2배 값을 배열에 채우고, 스택에서 하나더 꺼냄
								map[row][index--] = now * 2;
								numbers.pop();
							}
							// 같지 않다면 방금 꺼낸 값을 비어있는 인덱스 중 가장 오른쪽에 채움
							else {
								map[row][index--] = now;
							}
						}
					}
					
					// 비어있는 칸은 0으로 채우기
					for (int mapIndex = index; mapIndex >= 0; mapIndex--) {
						map[row][mapIndex] = 0;
					}
				}
				
				break;

			case "up":
				// 위쪽으로 몰아보자
				for (int col = mapSize - 1; col >= 0; col--) {
					// 위쪽으로 모아야하므로 0 인덱스부터 채움
					index = 0;
					// 아래 요소부터 스택에 추가
					for (int row = mapSize - 1; row >= 0; row--) {
						if (map[row][col] != 0) {
							numbers.add(map[row][col]);
						}
					}
					
					// 스택이 빌때까지 반복
					while (!numbers.isEmpty()) {
						// 요소를 하나 꺼냄
						int now = numbers.pop();
						
						// 만약 마지막 요소라면 비어있는 인덱스중 가장 위에 저장
						if (numbers.isEmpty()) {
							map[index++][col] = now;
						}
						// 마지막 요소가 아니라면
						else {
							// 스택의 가장 윗 요소와 비교
							// 같다면
							if (numbers.peek() == now) {
								// 2배 값을 배열에 채우고, 스택에서 하나더 꺼냄
								map[index++][col] = now * 2;
								numbers.pop();
							}
							// 같지 않다면 방금 꺼낸 값을 비어있는 인덱스 중 가장 위에 채움
							else {
								map[index++][col] = now;
							}
						}
					}
					
					// 비어있는 칸은 0으로 채우기
					for (int mapIndex = index; mapIndex < mapSize; mapIndex++) {
						map[mapIndex][col] = 0;
					}
				}
				
				break;

			case "down":
				// 아래쪽으로 몰아보자
				for (int col = 0; col < mapSize; col++) {
					// 아래쪽으로 모아야하므로 mapSize - 1 인덱스부터 채움
					index = mapSize - 1;
					// 위쪽 요소부터 스택에 추가
					for (int row = 0; row < mapSize; row++) {
						if (map[row][col] != 0) {
							numbers.add(map[row][col]);
						}
					}
					
					// 스택이 빌때까지 반복
					while (!numbers.isEmpty()) {
						// 요소를 하나 꺼냄
						int now = numbers.pop();
						
						// 만약 마지막 요소라면 비어있는 인덱스중 가장 아래에 저장
						if (numbers.isEmpty()) {
							map[index--][col] = now;
						}
						// 마지막 요소가 아니라면
						else {
							// 스택의 가장 윗 요소와 비교
							// 같다면
							if (numbers.peek() == now) {
								// 2배 값을 배열에 채우고, 스택에서 하나더 꺼냄
								map[index--][col] = now * 2;
								numbers.pop();
							}
							// 같지 않다면 방금 꺼낸 값을 비어있는 인덱스 중 가장 아래에 채움
							else {
								map[index--][col] = now;
							}
						}
					}
					
					// 비어있는 칸은 0으로 채우기
					for (int mapIndex = index; mapIndex >= 0; mapIndex--) {
						map[mapIndex][col] = 0;
					}
				}
				
				break;
		}
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		operation = st.nextToken();
		
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
