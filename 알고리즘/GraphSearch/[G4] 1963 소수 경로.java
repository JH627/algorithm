import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1963_소수경로 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int start, end;
    static boolean[] isPrime, visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        fillIsPrime();
        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < TC; testCase++) {
            init();
            sb.append(findMinCount()).append("\n");
        }
        System.out.print(sb);
    }

    static int findMinCount() {
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{start, 0});
        Arrays.fill(visited, false);
        visited[start] = true;

        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            int nowNumber = now[0];
            int nowCount = now[1];
            if (nowNumber == end) {
                return nowCount;
            }

            for (int index = 0; index < 4; index++) {
                for (int change = 0; change <= 9; change++) {
                    char[] num = String.valueOf(nowNumber).toCharArray();
                    num[index] = (char) ('0' + change);
                    int newNum = Integer.parseInt(String.valueOf(num));
                    if (newNum < 1000 || newNum >= 10000) {
                        continue;
                    }
                    if (visited[newNum]) {
                        continue;
                    }
                    if (!isPrime[newNum]) {
                        continue;
                    }
                    visited[newNum] = true;
                    toVisit.add(new int[]{newNum, nowCount + 1});
                }
            }
        }

        return 0;
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void fillIsPrime() {
        isPrime = new boolean[10000];
        visited = new boolean[10000];

        Arrays.fill(isPrime, true);
        for (int number = 2; number < 10000; number++) {
            if (!isPrime[number]) {
                continue;
            }
            for (int mul = 2; number * mul < 10000; mul++) {
                isPrime[number * mul] = false;
            }
        }
    }

}
