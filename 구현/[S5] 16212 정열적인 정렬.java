import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		ArrayList<Integer> arr = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(arr);

		StringBuilder sb = new StringBuilder();
		for (Integer n : arr) {
			sb.append(n).append(" ");
		}
		System.out.print(sb);

	}
}
