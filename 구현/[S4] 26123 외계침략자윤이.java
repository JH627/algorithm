import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_26123_외계침략자윤이 {

	static BufferedReader br;
	static StringTokenizer st;

	static int buildingCount, dayCount;
	static int[] height;
	static int maxHeight;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getLaserCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		buildingCount = Integer.parseInt(st.nextToken());
		dayCount = Integer.parseInt(st.nextToken());

		height = new int[buildingCount];
		maxHeight = 0;
		st = new StringTokenizer(br.readLine());
		for (int building = 0; building < buildingCount; building++) {
			height[building] = Integer.parseInt(st.nextToken());
			maxHeight = Math.max(maxHeight, height[building]);
		}
	}

	static long getLaserCount() {
		long count = 0;

		int lastMaxHeight = Math.max(0, maxHeight - dayCount);
		for (int building = 0; building < buildingCount; building++) {
			count += Math.max(0, height[building] - lastMaxHeight);
		}

		return count;
	}
}
