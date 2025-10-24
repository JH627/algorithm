import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1503_세수고르기 {

    static BufferedReader br;
    static StringTokenizer st;

    static int N, groupSize;
    static boolean[] disable;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findMin());
    }

    static int findMin() {
        int min = Integer.MAX_VALUE;
        for (int x = 1; x <= 1001; x++) {
            if (disable[x]) continue;
            for (int y = 1; y <= 1001; y++) {
                if (disable[y]) continue;
                if (N - x * y < 0 && Math.abs(N - x * y) >= min) {
                    break;
                }
                for (int z = 1; z <= 1001; z++) {
                    if (disable[z]) continue;
                    if (N - x * y * z < 0 && Math.abs(N - x * y * z) >= min) {
                        break;
                    }
                    min = Math.min(Math.abs(N - x * y * z), min);
                }
            }
            if (min == 0) {
                break;
            }
        }
        return min;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        groupSize = Integer.parseInt(st.nextToken());

        disable = new boolean[1002];
        st = new StringTokenizer(br.readLine());
        for (int num = 0; num < groupSize; num++) {
            disable[Integer.parseInt(st.nextToken())] = true;
        }
    }
}
