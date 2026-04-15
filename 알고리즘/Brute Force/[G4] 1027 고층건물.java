import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1027_고층건물 {

	static BufferedReader br;
	static StringTokenizer st;

	static int buildingCount;
	static int[] height;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxBuildingViewCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		buildingCount = Integer.parseInt(br.readLine());

		height = new int[buildingCount];
		st = new StringTokenizer(br.readLine());
		for (int building = 0; building < buildingCount; building++) {
			height[building] = Integer.parseInt(st.nextToken());
		}
	}

	static int getMaxBuildingViewCount() {
		int maxViewCount = 0;

		for (int building = 0; building < buildingCount; building++) {
			maxViewCount = Math.max(maxViewCount, findViewCount(building));
		}

		return maxViewCount;
	}

	static int findViewCount(int building) {
		int viewCount = 0;

		double viewLine = Double.MAX_VALUE;
		for (int buildingIndex = building - 1; buildingIndex >= 0; buildingIndex--) {
			double line = (double) (height[building] - height[buildingIndex]) / (building - buildingIndex);

			if (viewLine > line) {
				viewCount++;
				viewLine = line;
			}
		}

		viewLine = Double.NEGATIVE_INFINITY;
		for (int buildingIndex = building + 1; buildingIndex < buildingCount; buildingIndex++) {
			double line = (double) (height[building] - height[buildingIndex]) / (building - buildingIndex);

			if (viewLine < line) {
				viewCount++;
				viewLine = line;
			}
		}

		return viewCount;
	}
}
