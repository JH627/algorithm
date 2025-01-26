import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] ans;
	static Integer[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		for (int n = 0; n < N; n++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		ans = new int[M];
		arr = set.toArray(new Integer[set.size()]);
		Arrays.sort(arr);
		
		bt(0, 0);
		
		System.out.print(sb);
	}
	
	static void bt(int depth, int index) {
		if (depth == M) {
			for (int m = 0; m < M; m++) {
				sb.append(ans[m]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = index; i < arr.length; i++) {
			ans[depth] = arr[i];
			bt(depth + 1, i);
		}
	}
}
