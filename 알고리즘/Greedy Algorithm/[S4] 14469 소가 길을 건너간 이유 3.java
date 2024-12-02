import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Time implements Comparable<Time> {
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            if (this.start != o.start) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ArrayList<Time> times = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times.add(new Time(start, end));
        }

        Collections.sort(times);

        int cnt = 0;
        for (Time time : times) {
            if (time.start > cnt) {
                cnt = time.start + time.end;
            }
            else {
                cnt += time.end;
            }
        }

        System.out.print(cnt);
    }
}
