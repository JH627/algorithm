import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<Time> arr;

    static class Time implements Comparable<Time> {
        int start, end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        int s, e;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            arr.add(new Time(s, e));
        }

        Collections.sort(arr);

        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.offer(arr.get(0).end);
        
        for (int i = 1; i < N; i++) {
            if (q.peek() <= arr.get(i).start) {
                q.poll();
            }
            q.add(arr.get(i).end);
        }

        System.out.print(q.size());
    }
}
