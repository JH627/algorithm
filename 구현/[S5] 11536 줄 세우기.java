import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		String[] name = new String[N];
		for (int n = 0; n < N; n++) {
			name[n] = br.readLine();
		}
		int s = 0;
		for (int n = 0; n < N - 1; n++) {
			s += (name[n].compareTo(name[n + 1]) > 0) ? 1 : -1;
		}

		if (s == N - 1) {
			System.out.print("DECREASING");
		}
		else if (s == 1 - N) {
			System.out.print("INCREASING");
		}
		else {
			System.out.print("NEITHER");
		}
	}
}
