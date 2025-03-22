import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6550_부분문자열 {
  
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				continue;
			}

			String[] tokens = line.split(" ", 2);

			String s = tokens[0];
			String t = tokens[1];

			int sIndex = 0, tIndex = 0;
			int sLen = s.length(), tLen = t.length();

			while (tIndex < tLen && sIndex < sLen) {
				if (s.charAt(sIndex) == t.charAt(tIndex)) {
					sIndex++;
				}
				tIndex++;
			}

			if (sIndex == sLen) {
				sb.append("Yes\n");
			} else {
				sb.append("No\n");
			}
		}

		System.out.print(sb);
	}
}
