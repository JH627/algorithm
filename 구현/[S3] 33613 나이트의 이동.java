import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_33613_나이트의이동 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static long mapSize, startRow, startCol;
	
	public static void main(String[] args) throws Exception {
		init();
		
		if (mapSize == 3) {
			if (startRow == 1 && startCol == 1) {
				System.out.print(1);
				return;
			}
			System.out.print(4);
			return;
		}
		
		boolean isEven = (startRow + startCol) % 2 == 0;
		
		if (mapSize % 2 == 0) {
			System.out.print((mapSize * mapSize) / 2);
		}
		else {
			System.out.print(isEven ? (mapSize * mapSize) / 2 + 1 : (mapSize * mapSize) / 2);
		}
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		mapSize = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		startRow = Integer.parseInt(st.nextToken()) - 1;
		startCol = Integer.parseInt(st.nextToken()) - 1;
	}

}
