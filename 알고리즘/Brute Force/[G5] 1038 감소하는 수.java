import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1038_감소하는수 {

    static BufferedReader br;

    static int targetNumber;
    static int count;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findNumber());
    }

    static long findNumber() {
        if (targetNumber == 0) {
            return 0;
        }

        int count = 1;
        Queue<Long> q = new LinkedList<>();
        for (long n = 1; n <= 9; n++) {
            q.add(n);
            if (count++ == targetNumber) {
                return n;
            }
        }

        while (!q.isEmpty()) {
            long now = q.poll();
            long lastNumber = now % 10;

            if (count++ - 9 == targetNumber) {
                return now;
            }

            for (int plus = 0; plus < lastNumber; plus++) {
                q.add(now * 10 + plus);
            }
        }

        return -1;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        targetNumber = Integer.parseInt(br.readLine());

        count = 0;
    }
}
