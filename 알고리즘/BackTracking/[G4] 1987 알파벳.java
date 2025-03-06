import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 시작지점에서 부터 DFS 탐색을 시작한다.
 * 2. 만약 사용한 알파벳이 아닌 경우 탐색을 게속하고 현재 DFS의 깊이를 기준으로 최대 길이를 갱신한다.
 * 2-1. 사용한 알파벳이 아닌 경우 => (현재 가려는 알파벳) - 'A'의 값이 boolean 배열에서 false인 경우
 * 3. 최대 길이를 출력한다.
 *
 */
public class BOJ_1987_알파벳 {

	static BufferedReader br;
	
    static final int[] ADD_ROW = {1, -1, 0, 0};
    static final int[] ADD_COL = {0, 0, 1, -1};
	
    static boolean[] visited; // 등장한 알파벳
    static int[][] alpha; // 알파벳 맵 상태
    static int rowSize, colSize;
    static int maxLength; // 최대 길이

    public static void main(String[] args) throws Exception {
    	init();
    	
    	// 시작 지점 알파벳 사용 처리
        visited[alpha[0][0]] = true;
        findMaxLength(0, 0, 1);

        System.out.println(maxLength);
    }

    static void findMaxLength(int row, int col, int currentLength) {
    	// 분기한 경우 최대 길이를 갱신해봄
    	maxLength = Math.max(currentLength, maxLength);
        
        for (int index = 0; index < 4; index++) {
            int newRow = row + ADD_ROW[index];
            int newCol = col + ADD_COL[index];

            if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                continue;
            }

            int nextAlpha = alpha[newRow][newCol];
            
            // 만약 다음 알파벳이 등장하지 않았다면
            if (!visited[nextAlpha]) {
            	// 등장 처리 후 
                visited[nextAlpha] = true;
                // 탐색 시작
                findMaxLength(newRow, newCol, currentLength + 1);
                // 후처리
                visited[nextAlpha] = false;
            }
        }
    }
    
    static void init() throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        
        alpha = new int[rowSize][colSize];
        visited = new boolean[26];
        
        maxLength = Integer.MIN_VALUE;
        
        for (int row = 0; row < rowSize; row++) {
            String alphaLine = br.readLine();
            for (int col = 0; col < colSize; col++) {
            	// 저장시 0부터 26까지 저장 (a~z)
                alpha[row][col] = alphaLine.charAt(col) - 'A';
            }
        }
    }
}
