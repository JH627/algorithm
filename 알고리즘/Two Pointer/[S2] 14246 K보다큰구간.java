import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14246_K보다큰구간 {

	static BufferedReader br;
	
	static int elementCount;
	static int[] nums;
	static int limit;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(getCount());
	}
	
	static long getCount() {
		long count = 0;

	    int l = 0;
	    int curSum = 0;
	    for (int r = 0; r < elementCount; r++) {
	        curSum += nums[r];

	        while (l <= r && curSum > limit) {
	            count += elementCount - r;
	            curSum -= nums[l];
	            l++;
	        }
	    }

	    return count;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		elementCount = Integer.parseInt(br.readLine());
		nums = new int[elementCount];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			nums[elementIndex] = Integer.parseInt(st.nextToken());
		}
		
		limit = Integer.parseInt(br.readLine());
	}
}
