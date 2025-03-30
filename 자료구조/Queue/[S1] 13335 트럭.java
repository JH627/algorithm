import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13335_트럭 {

	static BufferedReader br;
	static StringTokenizer st;

	static int truckCount, bridgeLength, weightLimit;
	static int[] trucks;
	static Queue<Integer> bridge;

	public static void main(String[] args) throws IOException {
		init();

		int time = 0;
		int currentWeight = 0;
		int truckIndex = 0;
		while (truckIndex < truckCount) {
			time++;

			currentWeight -= bridge.poll();

			if (currentWeight + trucks[truckIndex] <= weightLimit) {
				bridge.add(trucks[truckIndex]);
				currentWeight += trucks[truckIndex];
				truckIndex++;
			}
			else {
				bridge.add(0);
			}
		}

		time += bridgeLength;

		System.out.print(time);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		truckCount = Integer.parseInt(st.nextToken());
		bridgeLength = Integer.parseInt(st.nextToken());
		weightLimit = Integer.parseInt(st.nextToken());

		trucks = new int[truckCount];
		st = new StringTokenizer(br.readLine());
		for (int truck = 0; truck < truckCount; truck++) {
			trucks[truck] = Integer.parseInt(st.nextToken());
		}

		bridge = new LinkedList<>();
		for (int len = 0; len < bridgeLength; len++) {
			bridge.add(0);
		}
	}
}
