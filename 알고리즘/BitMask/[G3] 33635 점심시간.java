import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_33635_점심시간 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int MAX_GENRE_COUNT = 200;

	static int genreCount, bookCount, queryCount;
	static HashMap<String, Integer> genreNumber;
	static BitSet[] bookGenres;

	public static void main(String[] args) throws IOException {
		init();

		useQuery();

		System.out.print(sb);
	}

	static void useQuery() throws IOException {
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int queryGenreCount = Integer.parseInt(st.nextToken());
			BitSet mask = new BitSet(MAX_GENRE_COUNT);
			for (int genreIndex = 0; genreIndex < queryGenreCount; genreIndex++) {
				mask.set(genreNumber.get(st.nextToken()));
			}

			int count = 0;
			for (int bookIndex = 0; bookIndex < bookCount; bookIndex++) {
				BitSet temp = (BitSet) mask.clone();
				temp.and(bookGenres[bookIndex]);
				if (temp.equals(mask)) {
					count++;
				}
			}

			sb.append(count).append('\n');
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		genreNumber = new HashMap<>();
		genreCount = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int genreIndex = 0; genreIndex < genreCount; genreIndex++) {
			genreNumber.put(st.nextToken(), genreIndex);
		}

		bookCount = Integer.parseInt(br.readLine());
		bookGenres = new BitSet[bookCount];
		for (int bookIndex = 0; bookIndex < bookCount; bookIndex++) {
			bookGenres[bookIndex] = new BitSet(MAX_GENRE_COUNT);
			st = new StringTokenizer(br.readLine());
			int bookGenreCount = Integer.parseInt(st.nextToken());
			st.nextToken(); // bookName
			for (int genreIndex = 0; genreIndex < bookGenreCount; genreIndex++) {
				bookGenres[bookIndex].set(genreNumber.get(st.nextToken()));
			}
		}

		queryCount = Integer.parseInt(br.readLine());
	}
}
