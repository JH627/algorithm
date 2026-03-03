import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3088_화분부수기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int bucketCount;
	static int[][] numbers;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(getMinCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		bucketCount = Integer.parseInt(br.readLine());

		numbers = new int[bucketCount][3];
		for (int bucket = 0; bucket < bucketCount; bucket++) {
			st = new StringTokenizer(br.readLine());
			numbers[bucket][0] = Integer.parseInt(st.nextToken());
			numbers[bucket][1] = Integer.parseInt(st.nextToken());
			numbers[bucket][2] = Integer.parseInt(st.nextToken());
		}
	}

	static int getMinCount() {
		boolean[] active = new boolean[1000001];

		int count = 0;

		for (int bucket = 0; bucket < bucketCount; bucket++) {
			int a = numbers[bucket][0];
			int b = numbers[bucket][1];
			int c = numbers[bucket][2];

			if (!(active[a] || active[b] || active[c])) {
				count++;
			}
			
			active[a] = true;
			active[b] = true;
			active[c] = true;
		}

		return count;
	}
}
