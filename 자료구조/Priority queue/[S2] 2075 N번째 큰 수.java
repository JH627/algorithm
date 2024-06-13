import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> q = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                q.add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < N - 1; i++) {
            q.poll();
        }

        System.out.println(q.peek());
    }
}