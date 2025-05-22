import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1015_수열정렬 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Num implements Comparable<Num> {
		int value;
		int originIndex;

		public Num(int value, int originIndex) {
			this.value = value;
			this.originIndex = originIndex;
		}

		public int compareTo(Num o) {
			if (this.value == o.value) {
				return this.originIndex - o.originIndex;
			}
			return this.value - o.value;
		}
	}

	static int elementCount;
	static Num[] nums;
	static int[] answer;

	public static void main(String[] args) throws IOException {
		init();

		getAnswer();

		System.out.print(sb);
	}

	static void getAnswer() {
		answer = new int[elementCount];
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			answer[nums[elementIndex].originIndex] = elementIndex;
		}

		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			sb.append(answer[elementIndex]).append(" ");
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		elementCount = Integer.parseInt(br.readLine());

		nums = new Num[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			nums[elementIndex] = new Num(Integer.parseInt(st.nextToken()), elementIndex);
		}

		Arrays.sort(nums);
	}

}
