import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Log implements Comparable<Log> {
        int t, s, n;

        public Log(int t, int s, int n) {
            this.t = t;
            this.s = s;
            this.n = n;
        }

        @Override
        public int compareTo(Log o) {
            return this.t - o.t;
        }
    }

    static int X, C, K;
    static ArrayList<Log> logs;
    static int[] seat, person;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        logs = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            logs.add(new Log(t, s, n));
        }

        Collections.sort(logs);

        seat = new int[C + 1]; // 누구 자리인지
        person = new int[X + 1]; // 몇번 자리 차지중인지
        for (Log log : logs) {
            if (person[log.n] != 0) {
                if (seat[log.s] != 0) {
                    continue;
                }
                seat[person[log.n]] = 0;
            }
            if (seat[log.s] == 0) {
                seat[log.s] = log.n;
                person[log.n] = log.s;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int x = 1; x < X + 1; x++) {
            if (person[x] != 0) {
                sb.append(x).append(" ").append(person[x]).append("\n");
            }
        }
        System.out.print(sb);
    }

}
