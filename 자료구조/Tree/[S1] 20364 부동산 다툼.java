import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static boolean[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new boolean[N + 1];

        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            sb.append(find(Integer.parseInt(br.readLine()))).append("\n");
        }
        System.out.print(sb);
    }

    static int find(int x) {
        int now = x;
        int blocked = 0;
        while (now > 1) {
            if (tree[now]) {
                blocked = now;
            }
            now /= 2;
        }
        if (blocked == 0) {
            tree[x] = true;
        }
        return blocked;
    }
}
