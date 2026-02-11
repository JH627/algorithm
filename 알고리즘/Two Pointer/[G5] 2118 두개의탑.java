import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2118_두개의탑 {

	static BufferedReader br;

	static int pointCount, distanceSum;
	static int[] distance;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxDistance());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		pointCount = Integer.parseInt(br.readLine());
		distance = new int[pointCount];
		distanceSum = 0;
		for (int pointIndex = 0; pointIndex < pointCount; pointIndex++) {
			distance[pointIndex] = Integer.parseInt(br.readLine());
			distanceSum += distance[pointIndex];
		}
	}

	static int getMaxDistance() {
		int l = 0;
		int r = 0;

		int current = 0;
		int maxDistance = 0;

		int halfDistance = distanceSum / 2;

		while (l < pointCount) {
			while (r < l + pointCount && current + distance[r % pointCount] <= halfDistance) {
				current += distance[r % pointCount];
				r++;
			}
			
			maxDistance = Math.max(maxDistance, Math.min(current, distanceSum - current));

			current -= distance[l];
			l++;
		}

		return maxDistance;
	}
}
