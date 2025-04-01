import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_30804_과일탕후루 {

	static int elementCount;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			elements[elementIndex] = Integer.parseInt(st.nextToken());
		}

		int[] count = new int[200001];

		int kind = 0;
		int maxLen = 0;
		int l = 0;
		for (int r = 0; r < elementCount; r++) {
			if (count[elements[r]]++ == 0) {
				kind++;
			}

			if (kind > 2) {
				while (kind > 2) {
					if (--count[elements[l++]] == 0) {
						kind--;
					}
				}
			}

			maxLen = Math.max(maxLen, r - l + 1);
		}

		System.out.print(maxLen);
	}
}
