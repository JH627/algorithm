import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_15665_N과M11 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int elementCount, selectCount;
	static ArrayList<Integer> arr;
	static int[] answer;

	public static void main(String[] args) throws IOException {
		init();

		getPermutation(0);

		System.out.print(sb);
	}

	static void getPermutation(int selectedCount) {
		if (selectedCount == selectCount) {
			for (int index = 0; index < selectCount; index++) {
				sb.append(answer[index]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int index = 0; index < arr.size(); index++) {
			answer[selectedCount] = arr.get(index);
			getPermutation(selectedCount + 1);
		}
	}


	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());

		elementCount = Integer.parseInt(st.nextToken());
		selectCount = Integer.parseInt(st.nextToken());

		HashSet<Integer> set = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			set.add(Integer.parseInt(st.nextToken()));
		}

		arr = new ArrayList<>(set);

		Collections.sort(arr);

		answer = new int[selectCount];
	}
}
