import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22945_팀빌딩 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int elementCount;
	static int[] numbers;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(findMaxForce());
	}
	
	static int findMaxForce() {
		int l = 0;	int r = elementCount - 1;
		int maxForce = 0;
		while (l < r) {
			int force = (r - l - 1) * Math.min(numbers[l], numbers[r]);
			maxForce = Math.max(force, maxForce);
			
			if (numbers[l] < numbers[r]) {
				l++;
			}
			else if (numbers[l] == numbers[r]) {
				l++;
				r--;
			}
			else {
				r--;
			}
		}

		return maxForce;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		elementCount = Integer.parseInt(br.readLine());
		numbers = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			numbers[elementIndex] = Integer.parseInt(st.nextToken());
		}
	}
	
}
