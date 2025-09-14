import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15887_욱제는결벽증이야 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Swap {
		int a, b;

		public Swap(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	static int elementCount;
	static int[] elements;
	static ArrayList<Swap> swaps;

	public static void main(String[] args) throws IOException {
		init();
		findAnswer();
		printAnswer();
	}

	static void findAnswer() {
		for (int left = 0; left < elementCount; left++) {
			int target = left + 1;
			if (elements[left] == target) {
				continue;
			}

			int right = left + 1;
			while (right < elementCount && elements[right] != target) {
				right++;
			}

			if (left < right) {
				reverse(left, right);
				swaps.add(new Swap(left + 1, right + 1));
			}
		}
	}

	static void reverse(int left, int right) {
		while (left < right) {
			int tmp = elements[left];
			elements[left] = elements[right];
			elements[right] = tmp;
			left++;
			right--;
		}
	}

	static void printAnswer() {
		sb = new StringBuilder();
		sb.append(swaps.size()).append('\n');
		for (Swap swap : swaps) {
			sb.append(swap.a).append(' ').append(swap.b).append('\n');
		}
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}

		swaps = new ArrayList<>();
	}

}
