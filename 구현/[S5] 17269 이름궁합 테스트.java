import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17269_이름궁합테스트 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] count = {3, 2, 1, 2, 4, 3, 1, 3, 1, 1, 3, 1, 3, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

	static int length;
	static int[] num;

	public static void main(String[] args) throws IOException {
		init();
		System.out.printf("%d%%", getPercent());
	}

	static int getPercent() {
		for (int step = 1; step < length - 1; step++) {
			for (int index = 0; index < length - step; index++) {
				num[index] = (num[index] + num[index + 1]) % 10;
			}
		}
		return num[0] * 10 + num[1];
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int aLength = Integer.parseInt(st.nextToken());
		int bLength = Integer.parseInt(st.nextToken());

		length = aLength + bLength;
		num = new int[length];

		st = new StringTokenizer(br.readLine());
		String a = st.nextToken();
		String b = st.nextToken();
		int index = 0;
		int aIndex = 0;	int bIndex = 0;
		while (aIndex < aLength || bIndex < bLength) {
			if (aIndex < aLength) {
				num[index++] = count[a.charAt(aIndex) - 'A'];
				aIndex++;
			}
			if (bIndex < bLength) {
				num[index++] = count[b.charAt(bIndex) - 'A'];
				bIndex++;
			}
		}

	}

}
