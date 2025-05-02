import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1497_기타콘서트 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int guitarCount;
	static int songCount;
	
	static long max;
	static int minGuitarCount;
	static long[] avaliableSong;
	
	public static void main(String[] args) throws Exception {
		init();
		
		if (max == 0) {
			System.out.println(-1);
			return;
		}
		
		findMinGuitarCount(-1, 0, 0);
		
		System.out.println(minGuitarCount);
	}
	
	static void findMinGuitarCount(int guitarIndex, int selectCount, long status) {
		if (status == max) {
			minGuitarCount = Math.min(selectCount, minGuitarCount);
			return;
		}
		
		if (guitarIndex == guitarCount - 1 || selectCount >= minGuitarCount - 1) {
			return;
		}
		
		for (int nextGuitarIndex = guitarIndex + 1; nextGuitarIndex < guitarCount; nextGuitarIndex++) {
			long newStatus = status | avaliableSong[nextGuitarIndex];
			if (newStatus == status) {
				continue;
			}
			findMinGuitarCount(nextGuitarIndex, selectCount + 1, newStatus);
		}
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		guitarCount = Integer.parseInt(st.nextToken());
		songCount = Integer.parseInt(st.nextToken());
		
		avaliableSong = new long[guitarCount];
		
		max = 0;
		for (int guitarIndex = 0; guitarIndex < guitarCount; guitarIndex++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			
			String s = st.nextToken();
			for (int songIndex = 0; songIndex < songCount; songIndex++) {
				if (s.charAt(songIndex) == 'Y') {
					avaliableSong[guitarIndex] |= (1L << songIndex);
					max |= (1L << songIndex);
				}
			}
		}
		
		minGuitarCount = guitarCount;
	}
}
