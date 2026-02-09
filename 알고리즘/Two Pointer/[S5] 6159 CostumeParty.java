import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_6159_CostumeParty {

	static BufferedReader br;
	static StringTokenizer st;

	static int cowCount, lengthLimit;
	static ArrayList<Integer> lengths;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		cowCount = Integer.parseInt(st.nextToken());
		lengthLimit = Integer.parseInt(st.nextToken());

		lengths = new ArrayList<>();
		for (int cow = 0; cow < cowCount; cow++) {
			int length = Integer.parseInt(br.readLine());
			if (length >= lengthLimit) {
				continue;
			}
			lengths.add(length);
		}

		cowCount = lengths.size();
	}

	static long getCount() {
		Collections.sort(lengths);

		long count = 0;
		int l = 0;
		int r = cowCount - 1;
		while (l < r) {
			if (lengths.get(l) + lengths.get(r) <= lengthLimit) {
				count += (r - l);
				l++;
			}
			else {
				r--;
			}
		}
		return count;
	}
}
