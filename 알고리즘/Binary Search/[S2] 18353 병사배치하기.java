import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_18353_병사배치하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static int[] elements;
	static ArrayList<Integer> LDS;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findOutManCount());
	}

	static int findOutManCount() {
		LDS = new ArrayList<>();

		LDS.add(Integer.MAX_VALUE);
		for (int element = 0; element < elementCount; element++) {
			int now = elements[element];

			if (LDS.get(LDS.size() - 1) > now) {
				LDS.add(now);
				continue;
			}

			int l = 0;
			int r = LDS.size() - 1;
			while (l < r) {
				int mid = (l + r) / 2;
				if (LDS.get(mid) > now) {
					l = mid + 1;
				}
				else {
					r = mid;
				}
			}

			LDS.set(l, now);
		}

		return elementCount - LDS.size() + 1;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount];

		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}
	}

}
