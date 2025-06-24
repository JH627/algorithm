import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_27964_콰트로치즈피자 {

	static BufferedReader br;
	static StringTokenizer st;

	static final String CHEESE = "Cheese";

	static int elementCount;
	static HashMap<String, Integer> map;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		map = new HashMap<>();

		elementCount = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			String word = st.nextToken();
			if (word.endsWith(CHEESE)) {
				map.put(word, 1);
				if (map.size() == 4) {
					System.out.print("yummy");
					return;
				}
			}
		}

		System.out.print("sad");
	}

}
