import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20301_반전요세푸스 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numberCount, jumpSize, pattern;

    public static void main(String[] args) throws IOException {
        init();
        findSequence();
        System.out.print(sb);
    }

    static void findSequence() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numberCount; i++) {
            list.add(i);
        }

        int curIndex = jumpSize - 1;
        int curCount = numberCount;
        boolean direction = true;
        for (int num = 1; num <= numberCount; num++) {
            sb.append(list.remove(curIndex) + 1).append("\n");
            curCount--;
            if (curCount == 0) {
                break;
            }

            if (num % pattern == 0) {
                direction = !direction;
            }

            if (direction) {
                curIndex = (curIndex + (jumpSize - 1)) % curCount;
            }
            else {
                int step = jumpSize % curCount;
                curIndex = (curIndex - step) % curCount;
                if (curIndex < 0) {
                    curIndex += curCount;
                }
            }
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        numberCount = Integer.parseInt(st.nextToken());
        jumpSize = Integer.parseInt(st.nextToken());
        pattern = Integer.parseInt(st.nextToken());
    }
}
