import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ_20291_파일정리 {

	static BufferedReader br;
	static StringBuilder sb;

	static int wordCount;
	static Map<String, Integer> words;

	public static void main(String[] args) throws IOException {
		init();
		getOrder();
		System.out.print(sb);
	}

	static void getOrder() {
		String[] keys = new String[words.size()];
		words.keySet().toArray(keys);
		Arrays.sort(keys);
		for (String key : keys) {
			sb.append(key).append(" ").append(words.get(key)).append('\n');
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		wordCount = Integer.parseInt(br.readLine());
		words = new HashMap<>();
		for (int word = 0; word < wordCount; word++) {
			String[] s = br.readLine().split("\\.");
			words.compute(s[1], (key, value) -> value == null ? 1 : value + 1);
		}
	}
}
