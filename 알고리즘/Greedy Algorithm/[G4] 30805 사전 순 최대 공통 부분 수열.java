import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_30805_사전순최대공통부분수열 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Num implements Comparable<Num> {
		int num, index;

		public Num(int num, int index) {
			this.num = num;
			this.index = index;
		}

		@Override
		public int compareTo(Num o) {
			if (this.num == o.num) {
				return this.index - o.index;
			}
			return o.num - this.num;
		}
	}

	static int aLen, bLen;
	static Num[] a, b;

	public static void main(String[] args) throws IOException {
		init();
		getLastPart();
		System.out.print(sb);
	}

	static void getLastPart() {
		int aPointer = 0, bPointer = 0;
		int aMaxIndex = -1, bMaxIndex = -1;

		while (aPointer < aLen && bPointer < bLen) {
			if (a[aPointer].num > b[bPointer].num) {
				aPointer++;
			}
			else if (a[aPointer].num < b[bPointer].num) {
				bPointer++;
			}
			else {
				aMaxIndex = a[aPointer].index;
				bMaxIndex = b[bPointer].index;
				break;
			}
		}

		if (aMaxIndex == -1) {
			sb.append(0);
			return;
		}

		int count = 0;
		while (aPointer < aLen && bPointer < bLen) {
			if (a[aPointer].num > b[bPointer].num || a[aPointer].index < aMaxIndex) {
				aPointer++;
			}
			else if (a[aPointer].num < b[bPointer].num || b[bPointer].index < bMaxIndex) {
				bPointer++;
			}
			else {
				sb.append(a[aPointer].num).append(" ");
				aMaxIndex = a[aPointer++].index;
				bMaxIndex = b[bPointer++].index;
				count++;
			}
		}

		sb.insert(0, String.format("%d\n", count));
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		aLen = Integer.parseInt(br.readLine());
		a = new Num[aLen];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < aLen; index++) {
			a[index] = new Num(Integer.parseInt(st.nextToken()), index);
		}

		bLen = Integer.parseInt(br.readLine());
		b = new Num[bLen];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < bLen; index++) {
			b[index] = new Num(Integer.parseInt(st.nextToken()), index);
		}

		Arrays.sort(a);
		Arrays.sort(b);
	}

}
