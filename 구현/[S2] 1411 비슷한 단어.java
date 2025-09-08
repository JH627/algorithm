import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_1411_비슷한단어 {

	static BufferedReader br;

	static int wordCount;
	static HashMap<String, Integer> map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getCount());
	}

	static int getCount() {
		int count = 0;
		for (Integer value : map.values()) {
			int comb = ((value) * (value - 1)) / 2;
			count += comb;
		}
		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		wordCount = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		for (int word = 0; word < wordCount; word++) {
			char[] line = br.readLine().toCharArray();
			char[] number = new char[line.length];
			int[] clocks = new int[26];
			int clock = 1;
			for (int index = 0; index < line.length; index++) {
				if (clocks[line[index] - 'a'] == 0) {
					clocks[line[index] - 'a'] = clock++;
				}
				number[index] = (char)('a' + clocks[line[index] - 'a'] - 1);
			}

			map.put(String.valueOf(number), map.getOrDefault(String.valueOf(number), 0) + 1);
		}
	}
}
