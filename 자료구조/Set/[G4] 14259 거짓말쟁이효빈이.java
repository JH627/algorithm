import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_14259_거짓말쟁이효빈이 {

	static BufferedReader br;
	static StringTokenizer st;

	static int areaSize, battleshipCount, battleshipSize, missileCount;
	static int[] targetPoint;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinMissileCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		areaSize = Integer.parseInt(st.nextToken());
		battleshipCount = Integer.parseInt(st.nextToken());
		battleshipSize = Integer.parseInt(st.nextToken());

		missileCount = Integer.parseInt(br.readLine());

		targetPoint = new int[areaSize];
		st = new StringTokenizer(br.readLine());
		for (int missile = 0; missile < missileCount; missile++) {
			targetPoint[missile] = Integer.parseInt(st.nextToken());
		}
	}

	static int getMinMissileCount() {
		TreeSet<Integer> blocked = new TreeSet<>();
		blocked.add(0);
		blocked.add(areaSize + 1);

		int maxBattleship = (areaSize + 1) / (battleshipSize + 1);

		for (int missile = 0; missile < missileCount; missile++) {
			int x = targetPoint[missile];

			int left = blocked.lower(x);
			int right = blocked.higher(x);

			maxBattleship -= (right - left) / (battleshipSize + 1);

			maxBattleship += (x - left) / (battleshipSize + 1);
			maxBattleship += (right - x) / (battleshipSize + 1);

			blocked.add(x);

			if (maxBattleship < battleshipCount) {
				return missile + 1;
			}
		}

		return -1;
	}
}
