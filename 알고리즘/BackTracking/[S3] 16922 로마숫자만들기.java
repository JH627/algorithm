import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16922_로마숫자만들기 {

    static BufferedReader br;
    static final int[] NUMBERS = {1, 5, 10, 50};

    static int elementCount;
    static boolean[][] checked;
    static int count;

    public static void main(String[] args) throws IOException {
        init();
        getCount(0, 0);
        System.out.print(count);
    }

    static void getCount(int sum, int depth) {
        for (int next = 0; next < 4; next++) {
            if (!checked[sum + NUMBERS[next]][depth + 1]) {
                checked[sum + NUMBERS[next]][depth + 1] = true;
                if (depth + 1 == elementCount) {
                    count++;
                    continue;
                }
                getCount(sum + NUMBERS[next], depth + 1);
            }
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        elementCount = Integer.parseInt(br.readLine());
        checked = new boolean[elementCount * 50 + 1][21];
        count = 0;
    }
}
