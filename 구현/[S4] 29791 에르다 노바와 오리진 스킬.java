import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_29791_에르다노바와오리진스킬 {

	static BufferedReader br;
	static StringTokenizer st;

	static int novaCount, originCount;
	static int[] novaTime, originTime;

	public static void main(String[] args) throws IOException {
		init();
		findTime();
	}

	static void findTime() {
		int novaStack = 0;
		int originStack = 0;

		int lastNovaTime = 0;
		int lastOriginTime = 0;
		for (int index = 0; index < novaCount; index++) {
			if (novaTime[index] >= lastNovaTime) {
				novaStack++;
				lastNovaTime = novaTime[index] + 100;
			}
		}
		for (int index = 0; index < originCount; index++) {
			if (originTime[index] >= lastOriginTime) {
				originStack++;
				lastOriginTime = originTime[index] + 360;
			}
		}

		System.out.printf("%d %d", novaStack, originStack);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		novaCount = Integer.parseInt(st.nextToken());
		originCount = Integer.parseInt(st.nextToken());

		novaTime = new int[novaCount];
		originTime = new int[originCount];

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < novaCount; index++) {
			novaTime[index] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < originCount; index++) {
			originTime[index] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(novaTime);
		Arrays.sort(originTime);
	}
}
