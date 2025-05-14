import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2840_행운의바퀴 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int boardSize, queryCount;
	static boolean[] alpha;
	static Integer[] board;
	static int index;
	
	public static void main(String[] args) throws Exception {
		init();
		
		boolean possible = useQuery();
		
		if (possible) {
			for (int point = 0; point < boardSize; point++) {
				int idx = (index - point + boardSize) % boardSize;
				sb.append(String.format("%c", board[idx] == null ? '?' : 'A' + board[idx]));
			}
		}
		else {
			sb.append("!");
		}
		
		System.out.print(sb);
	}
	
	static boolean useQuery() throws Exception {
		index = 0;
		
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int moveCount = Integer.parseInt(st.nextToken());
			int alphabet = st.nextToken().charAt(0) - 'A';
			
			index += moveCount;
			index %= boardSize;
			
			// 보드가 비어있다면
			if (board[index] == null) {
				// 넣는 알파벳이 이미 등장한 경우
				if (alpha[alphabet]) {
					// 불가능
					return false;
				}
				// 이미 등장하지 않은 경우에는 빈칸에 알파벳을 넣고
				board[index] = alphabet;
				// 알파벳 사용 표시
				alpha[alphabet] = true;
			}
			// 보드가 비어있지 않다면
			else {
				// 만약 보드에 있는 알파벳과 지금 알파벳과 같다면 패스
				if (board[index] == alphabet) {
					continue;
				}
				// 다르다면 불가능
				return false;
			}
		}
		
		return true;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		boardSize = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());
		
		board = new Integer[boardSize];
		alpha = new boolean[26];
	}
}
