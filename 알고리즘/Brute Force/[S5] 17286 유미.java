import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17286_유미 {

    static BufferedReader br;
    static StringTokenizer st;

    static class Position {
        int x, y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }

    static Position now;
    static Position[] positions;
    static boolean[] visited;

    static double minDistance;

    public static void main(String[] args) throws IOException {
        init();
        findMinDistance();
        System.out.print((int) minDistance);
    }

    static void findMinDistance() {
        for (int person = 0; person < 3; person++) {
            visited[person] = true;
            find(1, person, getDistance(now, positions[person]));
            visited[person] = false;
        }
    }

    static void find(int selectedCount, int now, double sum) {
        if (selectedCount == 3) {
            minDistance = Math.min(minDistance, sum);
            return;
        }
        if (sum >= minDistance) {
            return;
        }
        for (int person = 0; person < 3; person++) {
            if (!visited[person]) {
                visited[person] = true;
                find(selectedCount + 1, person, sum + getDistance(positions[now], positions[person]));
                visited[person] = false;
            }
        }
    }

    static double getDistance(Position start, Position end) {
        return (Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2)));
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        positions = new Position[3];

        now = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        for (int person = 0; person < 3; person++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            positions[person] = new Position(x, y);
        }

        visited = new boolean[3];

        minDistance = Double.MAX_VALUE;
    }
}
