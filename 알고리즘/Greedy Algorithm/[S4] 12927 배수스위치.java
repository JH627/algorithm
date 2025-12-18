import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12927_배수스위치 {

	static BufferedReader br;

	static char[] bulbs;
	static int bulbCount;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinCount());
	}

	static int findMinCount() {
		int pressCount = 0;

		for (int switchIndex = 0; switchIndex < bulbCount; switchIndex++) {
			if (bulbs[switchIndex] == 'Y') {
				pressCount++;

				int step = switchIndex + 1;
				for (int bulbIndex = switchIndex; bulbIndex < bulbCount; bulbIndex += step) {
					bulbs[bulbIndex] = (bulbs[bulbIndex] == 'Y') ? 'N' : 'Y';
				}
			}
		}

		for (int bulb = 0; bulb < bulbCount; bulb++) {
			if (bulbs[bulb] == 'Y') {
				return -1;
			}
		}

		return pressCount;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		bulbs = br.readLine().toCharArray();
		bulbCount = bulbs.length;
	}
}
