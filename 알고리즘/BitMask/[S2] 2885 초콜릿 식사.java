import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2885_초콜릿식사 {

    static BufferedReader br;

    static Integer target;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        target = Integer.parseInt(br.readLine());
        if (target == Integer.highestOneBit(target)) {
            System.out.printf("%d %d", target, 0);
            return;
        }

        int low = Integer.lowestOneBit(target);
        int high = Integer.highestOneBit(target) << 1;
        int count = 0;
        while (low != high) {
            count++;
            high = high >> 1;
        }

        System.out.printf("%d %d", Integer.highestOneBit(target) << 1, count);
    }
}
