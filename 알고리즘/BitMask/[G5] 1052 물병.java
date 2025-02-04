import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, K;
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int base = 1;
		int sum = 0;
		while (true) {
			if (Integer.bitCount(N) <= K) {
				System.out.print(sum);
				return;
			}
			
			if ((N & 1) != 0) {
				sum += base;
			}
			else {
				if (N == 0) {
					break;
				}
				sum += base * 2;
			}
			
			N = (N >> 1);
			N++;
			base *= 2;
		}
		
		System.out.print(-1);
	}
}
