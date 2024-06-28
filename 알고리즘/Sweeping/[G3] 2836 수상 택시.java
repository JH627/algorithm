import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Way implements Comparable<Way> {
        long start;
        long end;

        public Way(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Way o) {
            return (int) (this.start - o.start);
        }
    }

    static int N, M;
    static ArrayList<Way> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            if (end < start) {
                arr.add(new Way(end, start));
            }
        }

        Collections.sort(arr);

        long sum = M;
        long l = 0;
        long r = 0;
        for (Way way : arr) {
            long s = way.start;
            long e = way.end;
            if (s > r) {
                sum += 2 * (r - l);
                l = s;
                r = e;
            } else {
                r = Math.max(r, e);
            }
        }
        sum += 2 * (r - l);
        System.out.println(sum);
    }
}