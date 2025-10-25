import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2015_수들의합4 {

	static BufferedReader br;
	static StringTokenizer st;

	static HashMap<Integer, Integer> map;
	static int[] sum;
	static int target, elementCount;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findCount());
	}

	static long findCount() {
		long count = 0;
		for (int index = 1; index < elementCount + 1; index++) {
			count += map.getOrDefault(sum[index] - target, 0);
			map.put(sum[index], map.getOrDefault(sum[index], 0) + 1);
		}

		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		sum = new int[elementCount + 1];
		st = new StringTokenizer(br.readLine());
		for (int element = 1; element < elementCount + 1; element++) {
			sum[element] = sum[element - 1] + Integer.parseInt(st.nextToken());
		}

		map = new HashMap<>();
		map.put(0, 1);
	}
}
