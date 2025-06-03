import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1092_배 {

	static BufferedReader br;
	static StringTokenizer st;

	static int craneCount, boxCount;
	static ArrayList<Integer> cranePower, boxWeight;

	public static void main(String[] args) throws IOException {
		if (!init()) {
			System.out.print(-1);
			return;
		}

		System.out.print(getMinTime());
	}

	static int getMinTime() {
		int remainBoxCount = boxCount;
		int time = 0;
		while (remainBoxCount > 0) {
			time++;

			int boxIndexPointer = boxWeight.size() - 1;
			for (int crane = craneCount - 1; crane >= 0; crane--) {
				int power = cranePower.get(crane);

				while (boxIndexPointer >= 0) {
					if (boxWeight.get(boxIndexPointer) <= power) {
						boxWeight.remove(boxIndexPointer);
						remainBoxCount--;
						boxIndexPointer--;
						break;
					}

					boxIndexPointer--;
				}
			}
		}

		return time;
	}

	static boolean init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		int craneMax = 0;
		craneCount = Integer.parseInt(br.readLine());
		cranePower = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int crane = 0; crane < craneCount; crane++) {
			int power = Integer.parseInt(st.nextToken());
			cranePower.add(power);
			craneMax = Math.max(craneMax, power);
		}

		int boxMax = 0;
		boxCount = Integer.parseInt(br.readLine());
		boxWeight = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int box = 0; box < boxCount; box++) {
			int weight = Integer.parseInt(st.nextToken());
			boxWeight.add(weight);
			boxMax = Math.max(boxMax, weight);
		}

		if (boxMax > craneMax) {
			return false;
		}

		Collections.sort(cranePower);
		Collections.sort(boxWeight);

		return true;
	}


}
