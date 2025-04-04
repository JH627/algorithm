import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18111_마인크래프트 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static final int MAX_HEIGHT = 256;
	
	static int rowSize, colSize, limitBlock;
	static int[] blockCount;
	
	static int min, max;
	static int minTime;
	static int answerHeight;
	
	public static void main(String[] args) throws Exception {
		init();
		
		getMinTime();
		
		System.out.printf("%d %d", minTime, answerHeight);
	}
	
	static void getMinTime() {
		
		for (int height = min; height <= max; height++) {
			int timeSum = 0;
			int remainItem = limitBlock;
			// 블럭 채우기
			for (int checkHeight = min; checkHeight < height; checkHeight++) {
				remainItem -= (height - checkHeight) * blockCount[checkHeight];
				timeSum += (height - checkHeight) * blockCount[checkHeight];
			}
					
			// 블럭 없애기
			for (int checkHeight = height + 1; checkHeight <= max; checkHeight++) {
				remainItem += blockCount[checkHeight] * (checkHeight - height);
				timeSum += 2 * (blockCount[checkHeight] * (checkHeight - height));
			}
			
			if (remainItem < 0) {
				continue;
			}
			
			if (timeSum <= minTime) {
				minTime = timeSum;
				answerHeight = height;
			}
		}	
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		limitBlock = Integer.parseInt(st.nextToken());
		
		min = 256; max = 0;
		blockCount = new int[MAX_HEIGHT + 1];
		minTime = Integer.MAX_VALUE;
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				int height = Integer.parseInt(st.nextToken());
				blockCount[height]++;
				min = Math.min(min, height);
				max = Math.max(max, height);
			}
		}
	}
}
