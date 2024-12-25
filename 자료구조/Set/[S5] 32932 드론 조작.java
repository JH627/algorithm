import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    static class Block {
        int x, y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Block block = (Block) o;
            return x == block.x && y == block.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int N, K;
    static HashSet<Block> blocks;
    static final String op = "LDRU";
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        blocks = new HashSet<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            blocks.add(new Block(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int x = 0;
        int y = 0;

        String s = br.readLine();
        for (int k = 0; k < K; k++) {
            char c = s.charAt(k);
            int func = op.indexOf(c);
            int new_x = x + dx[func];
            int new_y = y + dy[func];
            if (blocks.contains(new Block(new_x, new_y))) {
                continue;
            }
            x = new_x;
            y = new_y;
        }

        System.out.printf("%d %d", x, y);
    }
}
