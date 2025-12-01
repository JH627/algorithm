import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_22115_창영이와커피 {

    static BufferedReader br;
    static StringTokenizer st;

    static final int INF = 1111111;

    static int coffeeCount, targetCaffeine;
    static int[] caffeine, minCaffeine;

    public static void main(String[] args) throws IOException {
        init();
        findMinCount();
        System.out.print(minCaffeine[targetCaffeine] == INF ? "-1" : minCaffeine[targetCaffeine]);
    }

    static void findMinCount() {
        for (int coffee = 0; coffee < coffeeCount; coffee++) {
            for (int sum = targetCaffeine; sum >= caffeine[coffee]; sum--) {
                if (minCaffeine[sum - caffeine[coffee]] + 1 < minCaffeine[sum]) {
                    minCaffeine[sum] = minCaffeine[sum - caffeine[coffee]] + 1;
                }
            }
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        coffeeCount = Integer.parseInt(st.nextToken());
        targetCaffeine = Integer.parseInt(st.nextToken());

        caffeine = new int[coffeeCount];
        minCaffeine = new int[targetCaffeine + 1];

        st = new StringTokenizer(br.readLine());
        for (int coffee = 0; coffee < coffeeCount; coffee++) {
            caffeine[coffee] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(minCaffeine, INF);
        minCaffeine[0] = 0;
    }
}
