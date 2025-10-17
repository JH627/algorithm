import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_19699_소난다 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int cowCount, selectCount;
    static int[] weight;
    static boolean[] isPrime;
    static ArrayList<Integer> answer;

    public static void main(String[] args) throws IOException {
        init();
        findAnswer(0, 0, 0);
        printAnswer();
    }

    static void findAnswer(int index, int selectedCount, int sum) {
        if (selectedCount == selectCount) {
            if (isPrime[sum]) {
                answer.add(sum);
            }
            return;
        }

        if (index == cowCount) {
            return;
        }

        findAnswer(index + 1, selectedCount + 1, sum + weight[index]);
        findAnswer(index + 1, selectedCount, sum);
    }

    static void printAnswer() {
        sb = new StringBuilder();
        if (answer.isEmpty()) {
            sb.append("-1");
        }
        else {
            Collections.sort(answer);
            int prev = 0;
            for (Integer weight : answer) {
                if (weight == prev) {
                    continue;
                }
                prev = weight;
                sb.append(weight).append(" ");
            }
        }
        System.out.print(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        cowCount = Integer.parseInt(st.nextToken());
        selectCount = Integer.parseInt(st.nextToken());

        weight = new int[cowCount];
        st = new StringTokenizer(br.readLine());
        for (int cow = 0; cow < cowCount; cow++) {
            weight[cow] = Integer.parseInt(st.nextToken());
        }

        isPrime = new boolean[10000];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int num = 2; num < 10000; num++) {
            if (!isPrime[num]) {
                continue;
            }
            for (int mul = 2; num * mul < 10000; mul++) {
                isPrime[num * mul] = false;
            }
        }

        answer = new ArrayList<>();
    }
}
