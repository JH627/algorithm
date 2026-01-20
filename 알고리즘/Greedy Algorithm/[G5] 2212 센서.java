import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2212_센서 {

	static BufferedReader br;
	static StringTokenizer st;

	static int sensorCount, towerCount;
	static int[] positions;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinDistance());
	}

	static int getMinDistance() {
		if (towerCount >= sensorCount) {
			return 0;
		}

		Arrays.sort(positions);

		Integer[] distances = new Integer[sensorCount - 1];
		for (int tower = 1; tower < sensorCount; tower++) {
			distances[tower - 1] = positions[tower] - positions[tower - 1];
		}

		Arrays.sort(distances, Collections.reverseOrder());

		int sum = 0;
		for (int tower = towerCount - 1; tower < sensorCount - 1; tower++) {
			sum += distances[tower];
		}
		return sum;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sensorCount = Integer.parseInt(br.readLine());
		towerCount = Integer.parseInt(br.readLine());

		positions = new int[sensorCount];
		st = new StringTokenizer(br.readLine());
		for (int tower = 0; tower < sensorCount; tower++) {
			positions[tower] = Integer.parseInt(st.nextToken());
		}
	}
}
