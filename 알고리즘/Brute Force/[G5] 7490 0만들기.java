import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_7490_0만들기 {

    static BufferedReader br;
    static StringBuilder sb;

    static int num;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < TC; testCase++) {
            init();
            findAnswer(0, 0, 0, "");
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void findAnswer(int index, int sum, int prev, String oper) {
        if (index == 0) {
            findAnswer(2, 0, 1, "1");
            return;
        }

        if (index == num + 1) {
            sum += prev;
            if (sum == 0) {
                sb.append(oper).append("\n");
            }
            return;
        }

        int concatPrev = (prev >= 0) ? (prev * 10 + index) : (prev * 10 - index);
        findAnswer(index + 1, sum, concatPrev, oper + " " + index);
        findAnswer(index + 1, sum + prev, +index, oper + "+" + index);
        findAnswer(index + 1, sum + prev, -index, oper + "-" + index);
    }

    static void init() throws IOException {
        num = Integer.parseInt(br.readLine());
    }
}
