import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 1. 각 행마다 최대 값을 구한다.
 * 1-1. col = 0부터 하나씩 시작점으로 잡으며
 * 1-2. 조합을 통하여 선택할수 있는 벌통의 개수에 도달할때까지 분기하며 현재 행의 최대값을 갱신한다.
 * 
 * 2. 모든 행의 최대값들을 정렬한다
 * 3. 가장 큰 두개의 값을 합쳐서 출력한다.
 *
 */
public class SWEA_2115_벌꿀채취 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[][] map;
	
	static int mapSize, selectCount, bucketSize;
	
	static ArrayList<Integer> sizes;
	static int maxSize;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			for (int row = 0; row < mapSize; row++) {
				maxSize = 0;
				// 각 행의 최대 양 갱신
				for (int col = 0; col <= mapSize - selectCount; col++) {
					findSize(col, col + selectCount - 1, 0, 0, row);
				}
				sizes.add(maxSize);
			}
			
			Collections.sort(sizes);
			
			// 각 행의 최대 양 2개를 합침
			int max = sizes.get(sizes.size() - 1) + sizes.get(sizes.size() - 2);
			sb.append("#").append(testCase).append(" ").append(max).append("\n");
			
		}
		
		System.out.print(sb);
	}
	
	static void findSize(int curIndex, int limitIndex, int originSum, int powerSum, int row) {
		// 선택할 수 있는 최대 벌통의 개수를 넘은 경우 최대 양 갱신
		if (curIndex > limitIndex) {
			maxSize = Math.max(maxSize, powerSum);
			return;
		}
		
		int originValue = map[row][curIndex];
		
		// 꿀을 채취할 수 있는 최대 양을 아직 넘지 않은 경우
		if (originSum + originValue <= bucketSize) {
			findSize(curIndex + 1, limitIndex, originSum + originValue, 
					powerSum + originValue * originValue, row);
		}
		
		findSize(curIndex + 1, limitIndex, originSum, powerSum, row);
	}
	
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		mapSize = Integer.parseInt(st.nextToken());
		selectCount = Integer.parseInt(st.nextToken());
		bucketSize = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		sizes = new ArrayList<>();
	}
}
