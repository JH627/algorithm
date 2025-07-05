import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1544_사이클단어 {

	static BufferedReader br;

	static int wordCount;
	static List<String> words;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(getWordCount());
	}

	static int getWordCount() {
		List<String> uniqueWords = new ArrayList<>();

		for (String word : words) {
			boolean isDuplicate = false;

			for (String unique : uniqueWords) {
				if (word.length() != unique.length()) {
					continue;
				}
				if ((unique + unique).contains(word)) {
					isDuplicate = true;
					break;
				}
			}

			if (!isDuplicate) {
				uniqueWords.add(word);
			}
		}

		return uniqueWords.size();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		wordCount = Integer.parseInt(br.readLine());
		words = new ArrayList<>();

		for (int word = 0; word < wordCount; word++) {
			words.add(br.readLine());
		}
	}
}
