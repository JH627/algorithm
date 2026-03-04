import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_22232_가희와파일탐색기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class File implements Comparable<File> {
		String name;
		String extension;

		public File(String name, String extension) {
			this.name = name;
			this.extension = extension;
		}

		@Override
		public int compareTo(File o) {
			if (this.name.compareTo(o.name) != 0) {
				return this.name.compareTo(o.name);
			}

			boolean thisKnown = extensions.contains(this.extension);
			boolean otherKnown = extensions.contains(o.extension);

			if (thisKnown != otherKnown) {
				return thisKnown ? -1 : 1;
			}

			return this.extension.compareTo(o.extension);
		}

	}

	static int fileCount, extensionCount;
	static HashSet<String> extensions;
	static ArrayList<File> files;

	public static void main(String[] args) throws IOException {
		init();
		printSortedFile();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		fileCount = Integer.parseInt(st.nextToken());
		extensionCount = Integer.parseInt(st.nextToken());

		files = new ArrayList<>();
		for (int file = 0; file < fileCount; file++) {
			String[] name = br.readLine().split("\\.");
			files.add(new File(name[0], name[1]));
		}

		extensions = new HashSet<>();
		for (int extension = 0; extension < extensionCount; extension++) {
			extensions.add(br.readLine());
		}
	}

	static void printSortedFile() {
		Collections.sort(files);

		sb = new StringBuilder();
		for (File file : files) {
			sb.append(file.name).append(".").append(file.extension).append("\n");
		}
		System.out.print(sb);
	}
}
