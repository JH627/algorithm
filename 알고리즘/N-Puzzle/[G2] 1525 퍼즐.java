import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static HashSet<String> visited;
    static final int SIZE = 3;
    static final String answer = "123456780";

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    static class Puzzle {
        String puzzle;
        int cnt;

        public Puzzle(String puzzle, int cnt) {
            this.puzzle = puzzle;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                sb.append(st.nextToken());
            }
        }

        visited = new HashSet<>();
        System.out.println(bfs(sb.toString()));
    }

    static int bfs(String init) {
        visited.add(init);
        if (answer.equals(init)) {
            return 0;
        }
        Queue<Puzzle> q = new LinkedList<>();
        q.add(new Puzzle(init, 0));

        while(!q.isEmpty()) {
            Puzzle puzzle = q.poll();

            int empty = puzzle.puzzle.indexOf('0');
            int x = empty % SIZE;
            int y = empty / SIZE;

            int new_x, new_y;
            for (int i = 0; i < 4; i++) {
                new_x = x + dx[i];
                new_y = y + dy[i];

                if (new_x >= SIZE || new_y >= SIZE || new_x < 0 || new_y < 0) {
                    continue;
                }

                int nPos = new_y * SIZE + new_x;
                StringBuilder sb = new StringBuilder(puzzle.puzzle);
                sb.setCharAt(empty, sb.charAt(nPos));
                sb.setCharAt(nPos, '0');

                String newPuzzle = sb.toString();
                if (!visited.contains(newPuzzle)) {
                    if (answer.equals(newPuzzle)) {
                        return puzzle.cnt + 1;
                    }
                    visited.add(newPuzzle);
                    q.add(new Puzzle(newPuzzle, puzzle.cnt + 1));
                }
            }
        }

        return -1;
    }
}
