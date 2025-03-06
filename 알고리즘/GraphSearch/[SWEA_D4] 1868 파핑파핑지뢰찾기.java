import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 풀이
 * 
 * 1. 지뢰를 입력받으면 배열에 지뢰의 위치는 -1로 설정, 주변은 1씩 숫자를 늘린다.
 * 2. 배열을 하나씩 탐색하며 만약 0인경우(주변 8칸 이내에 지뢰가 없는 경우) 클릭횟수를 1 늘리고, BFS를 하며 주변 8칸을 확인한다
 * 2-1. 만약 0인 지역이 있으면 Queue에 넣고 해당 위치에서도 8칸을 확인한다.
 * 3. 배열을 모두 탐색했으면 아직 확인하지 못한 곳의 개수를 클릭 횟수에 추가하고 정답을 출력한다
 *
 */
public class SWEA_1868_파핑파핑지뢰찾기 {
    
    static BufferedReader br;
    
    static final int[] ADD_ROW = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int[] ADD_COL = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    static int mapSize;
    static int[][] map; // 현재 맵 상태
    static boolean[][] visited; // 확인한 했는지 표시할 배열
    static int bombCount; // 폭탄의 개수
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= TC; testCase++) {
            init();
            
            int areaCount = checkMap();
            
            sb.append("#").append(testCase).append(" ").append(areaCount).append("\n");
        }        
        
        System.out.print(sb);
    }
    
    static int checkMap() {
        int clickCount = 0;
        int uncheckedAreaSize = mapSize * mapSize - bombCount;
        
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
            	// 만약 0이고 방문하지 않은 곳이라면 BFS 시작, 클릭횟수 ++
                if (map[row][col] == 0 && !visited[row][col]) {
                    clickCount++;
                    // 체크된 지역만큼 남은 체크 지역 개수에서 뺌
                    uncheckedAreaSize -= getCheckedAreaSizeFrom(row, col);
                }
                
                // 만약 전부 체크했으면 클릭횟수 반환
                if (uncheckedAreaSize == 0) {
                    return clickCount;
                }
            }
        }
        
        // 0 인 지역에서 시작했을때 모두 확인할 수 없는 경우
        // 클릭횟수 + 남은 지역개수 반환
        return clickCount + uncheckedAreaSize;
    }
    
    static int getCheckedAreaSizeFrom(int row, int col) {
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{row, col});
        
        int checkedAreaSize = 1;
        visited[row][col] = true;
        
        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowRow = now[0];
            int nowCol = now[1];
            
            for (int index = 0; index < 8; index++) {
                int newRow = nowRow + ADD_ROW[index];
                int newCol = nowCol + ADD_COL[index];
                
                if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
                    continue;
                }
                
                // 만약 방문한 곳이라면 패스
                if (visited[newRow][newCol]) {
                    continue;
                }
                
                visited[newRow][newCol] = true;
                // 체크된 지역 개수 ++
                checkedAreaSize++;
                
                // 만약 0이라면 0에서도 탐색해야하므로 Queue에 추가
                if (map[newRow][newCol] == 0) {
                    toVisit.add(new int[] {newRow, newCol});
                }
            }
        }
        
        return checkedAreaSize;
    }
    
    static void init() throws Exception {
        mapSize = Integer.parseInt(br.readLine());
        
        bombCount = 0;
        map = new int[mapSize][mapSize];
        visited = new boolean[mapSize][mapSize];
        
        // 폭탄 주변에 개수 표시
        for (int row = 0; row < mapSize; row++) {
            String mapLine = br.readLine();
            for (int col = 0; col < mapSize; col++) {
                if (mapLine.charAt(col) == '*') {
                    map[row][col] = -1; // 지뢰 표시
                    visited[row][col] = true;
                    
                    bombCount++;
                    
                    for (int index = 0; index < 8; index++) {
                        int newRow = row + ADD_ROW[index];
                        int newCol = col + ADD_COL[index];
                        
                        if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
                            continue;
                        }
                        
                        // 지뢰인 곳은 패스
                        if (map[newRow][newCol] != -1) {
                            map[newRow][newCol]++;
                        }
                    }
                }
            }
        }
    }
}
