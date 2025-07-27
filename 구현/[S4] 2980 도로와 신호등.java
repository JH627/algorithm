import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2980_도로와신호등 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int signalCount, roadLength;
	static int[][] signalInfo;

	public static void main(String[] args) throws IOException {
		init();
		simulate();
	}

	static void simulate() {
		int currentTime = 0;
		int currentPosition = 0;
		int signalIndex = 0;

		while (currentPosition < roadLength) {
			if (signalIndex < signalCount && currentPosition == signalInfo[signalIndex][0]) {
				int redTime = signalInfo[signalIndex][1];
				int greenTime = signalInfo[signalIndex][2];
				int signalCycle = redTime + greenTime;
				int timeInCycle = currentTime % signalCycle;

				if (timeInCycle < redTime) {
					currentTime += redTime - timeInCycle;
				}

				signalIndex++;
			}

			currentPosition++;
			currentTime++;
		}

		System.out.print(currentTime);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		signalCount = Integer.parseInt(st.nextToken());
		roadLength = Integer.parseInt(st.nextToken());

		signalInfo = new int[signalCount][3];
		for (int index = 0; index < signalCount; index++) {
			st = new StringTokenizer(br.readLine());
			int signalPosition = Integer.parseInt(st.nextToken());
			int redDuration = Integer.parseInt(st.nextToken());
			int greenDuration = Integer.parseInt(st.nextToken());

			signalInfo[index][0] = signalPosition;
			signalInfo[index][1] = redDuration;
			signalInfo[index][2] = greenDuration;
		}
	}
}
