import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        arr.sort(Collections.reverseOrder());

        int max = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n = 0; n < N; n++) {
            while (!pq.isEmpty() && pq.peek() <= n) {
                pq.poll();
            }
            pq.add(arr.get(n) + n);
            max = Math.max(max, pq.size());
        }

        System.out.print(max);
    }
}
