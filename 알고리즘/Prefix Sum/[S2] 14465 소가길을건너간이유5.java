import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14465_소가길을건너간이유5 {

	static BufferedReader br;
	static StringTokenizer st;

	static int roadLength, needLength, disableCount;
	static int[] status;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		roadLength = Integer.parseInt(st.nextToken());
		needLength = Integer.parseInt(st.nextToken());
		disableCount = Integer.parseInt(st.nextToken());

		status = new int[roadLength + 1];
		for (int disable = 0; disable < disableCount; disable++) {
			status[Integer.parseInt(br.readLine())] = 1;
		}

		for (int road = 1; road < roadLength + 1; road++) {
			status[road] += status[road - 1];
		}
	}

	static int getMinCount() {
		int min = status[needLength];

		for (int road = needLength + 1; road < roadLength + 1; road++) {
			min = Math.min(min, status[road] - status[road - needLength]);
		}

		return min;
	}
}
