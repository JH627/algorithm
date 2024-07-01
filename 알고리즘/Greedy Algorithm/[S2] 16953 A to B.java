import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Num {
        long n;
        int clock;

        public Num(long n, int clock) {
            this.n = n;
            this.clock = clock;
        }
    }

    static long A, B;
    static Queue<Num> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        q = new LinkedList<>();
        q.add(new Num(A, 0));

        int ans = -1;
        Num now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (2 * now.n == B || 10 * now.n + 1 == B) {
                ans = now.clock + 2;
                break;
            }
            if (2 * now.n < B) {
                q.add(new Num(2 * now.n, now.clock + 1));
            }
            if (10 * now.n + 1 < B) {
                q.add(new Num(10 * now.n + 1, now.clock + 1));
            }
        }

        System.out.println(ans);
    }
}
