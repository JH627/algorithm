import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_21275_눈치우기 {

	private static final int MAX_TIME = 1440;

	public static void main(String[] args) throws IOException {
		PriorityQueue<Integer> snowQueue = readInput();
		int result = calculateMinimumTime(snowQueue);
		System.out.println(result);
	}

	private static PriorityQueue<Integer> readInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int houseCount = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> snowQueue =
			new PriorityQueue<>(Collections.reverseOrder());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int house = 0; house < houseCount; house++) {
			snowQueue.add(Integer.parseInt(st.nextToken()));
		}

		return snowQueue;
	}

	private static int calculateMinimumTime(PriorityQueue<Integer> snowQueue) {
		int time = 0;

		while (!snowQueue.isEmpty()) {
			int largest = snowQueue.poll();
			int secondLargest = snowQueue.isEmpty() ? 0 : snowQueue.poll();

			largest--;
			if (secondLargest > 0) {
				secondLargest--;
			}

			if (largest > 0) {
				snowQueue.add(largest);
			}
			if (secondLargest > 0) {
				snowQueue.add(secondLargest);
			}

			time++;
			if (time > MAX_TIME) {
				return -1;
			}
		}

		return time;
	}
}
