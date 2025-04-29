import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1817_짐챙기는숌 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int bookCount;
	static Integer[] weight;
	static int boxLimit;
	
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(getBoxCount());
	}
	
	static int getBoxCount() {
		int count = 0;
		int sum = 0;
		for (int bookIndex = 0; bookIndex < bookCount; bookIndex++) {
			if (bookIndex == 0) {
				count++;
			}
			if (sum + weight[bookIndex] > boxLimit) {
				count++;
				sum = weight[bookIndex];
			}
			else {
				sum += weight[bookIndex];
			}
		}
		
		return count;
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		bookCount = Integer.parseInt(st.nextToken());
		boxLimit = Integer.parseInt(st.nextToken());
		
		weight = new Integer[bookCount];
		if (bookCount > 0) {
			st = new StringTokenizer(br.readLine());
		}
		for (int bookIndex = 0; bookIndex < bookCount; bookIndex++) {
			weight[bookIndex] = Integer.parseInt(st.nextToken());
		}
	}
}
