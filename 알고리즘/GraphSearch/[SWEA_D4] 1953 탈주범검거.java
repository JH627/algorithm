import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 1. 시작 터널에서 BFS 탐색을 시작한다.
 * 
 * 2. 현재 위치의 상태를 확인
 * 2-1. 방문한 터널 개수에 추가
 * 2-2. 현재 터널의 종류에 따라 이동할 방향을 확인
 * 2-2-1. 만약 이동할 방향에 있는 터널이 현재 터널과 연결이 되어있는 경우
 * 2-2-2. 방문할 곳에 추가
 * 2-3-1. 만약 이동하는 경우 제한시간이 되는 경우
 * 2-3-2. 방문할 곳에 추가하지 않고 방문한 터널 개수만 추가 
 * 
 * 3. 방문한 터널 개수 출력
 *
 */
public class SWEA_1953_탈주범검거 {
     
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
     
    // 상, 하, 좌, 우
    static final int[] ADD_ROW = {-1, 1, 0, 0};
    static final int[] ADD_COL = {0, 0, -1, 1};
     
    static int[] tunnelDirection;
     
    static class Point {
        int row, col, type, time;
 
        public Point(int row, int col, int type, int time) {
            this.row = row;
            this.col = col;
            this.type = type;
            this.time = time;
        }
    }
     
    static int rowSize, colSize;
    static int startRow, startCol;
    static int timeLimit;
    static int[][] map;
     
    public static void main(String[] args) throws Exception {
        globalInit();
         
        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= TC; testCase++) {
            init();
             
            int areaSize = getAreaSize();
             
            sb.append("#").append(testCase).append(" ").append(areaSize).append("\n");
        }
         
        System.out.print(sb);
    }
     
    static int getAreaSize() {
        ArrayDeque<Point> toVisit = new ArrayDeque<>();
        toVisit.add(new Point(startRow, startCol, map[startRow][startCol] - 1, 1));
        map[startRow][startCol] = 0;
         
        int areaSize = 0;
        while (!toVisit.isEmpty()) {
            Point now = toVisit.poll();
            areaSize++; 
             
            if (now.time == timeLimit) {
                continue;
            }
 
            int newRow, newCol;
            for (int direction = 0; direction < 4; direction++) {
                if ((tunnelDirection[now.type] & (1 << direction)) == 0) {
                    continue;
                }
                newRow = now.row + ADD_ROW[direction];
                newCol = now.col + ADD_COL[direction];
                 
                if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
                    continue;
                }
                 
                int nextType = map[newRow][newCol] - 1;
                if (nextType == -1) {
                    continue;
                }
                 
                // 연결되어 있는 경우 반대 쪽 터널의 방향
                int checkDirection = ((direction & 1) == 1 ? direction - 1 : direction + 1);
                // 이동할 터널이 현재 터널과 연결되어있는지
                if ((tunnelDirection[nextType] & (1 << checkDirection)) >= 1) {
                    map[newRow][newCol] = 0;
                    // 시간제한이 다 되는 경우
                    if (now.time + 1 == timeLimit) {                
                        areaSize++;
                    }
                    // 시간 여유가 있는 경우
                    else {
                        toVisit.add(new Point(newRow, newCol, nextType, now.time + 1));
                    }
                }
            }
        }
 
        return areaSize;        
    }
     
    static void init() throws Exception {       
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
         
        startRow = Integer.parseInt(st.nextToken());
        startCol = Integer.parseInt(st.nextToken());
         
        timeLimit = Integer.parseInt(st.nextToken());
         
        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
     
    static void globalInit() {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
        tunnelDirection = new int[7];
        // 1번 터널 (상, 하, 좌, 우)
        tunnelDirection[0] = 1 + 2 + 4 + 8;
        // 2번 터널 (상, 하)
        tunnelDirection[1] = 1 + 2;
        // 3번 터널 (좌, 우)
        tunnelDirection[2] = 4 + 8;
        // 4번 터널 (상, 우)
        tunnelDirection[3] = 1 + 8;
        // 5번 터널 (하, 우)
        tunnelDirection[4] = 2 + 8;
        // 6번 터널 (하, 좌)
        tunnelDirection[5] = 2 + 4;
        // 7번 터널 (상, 좌)
        tunnelDirection[6] = 1 + 4;
    }
}
