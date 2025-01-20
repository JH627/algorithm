import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int min;
	static ArrayList<Integer> arr = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		M = Integer.parseInt(br.readLine());
		
		min = Math.abs(N - 100);
		
		boolean[] able = new boolean[10];
		Arrays.fill(able, true);
		if (M != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				able[Integer.parseInt(st.nextToken())] = false;
			}
		}
		
		for (int n = 0; n < 10; n++) {
			if (able[n]) {
				arr.add(n);
			}
		}

		min = Math.abs(N - 100);
		for (int n : arr) {
			dfs(n, 1);
		}
		
		System.out.print(min);
	}
	
	static void dfs(int now, int length) {
		if (now == 0) {
			min = Math.min(min, N + 1);
			return;
		}
		
		min = Math.min(min, Math.abs(N - now) + length);
		
		for (int n : arr) {
			int next = now * 10 + n;
			if (next > 1000000) {
				break;
			}
			dfs(next, length + 1);
		}
	}

}
