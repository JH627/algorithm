import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LinkedList<Integer> deque = new LinkedList<>();
        for (int n = 1; n <= N; n++) {
            deque.add(n);
        }

        int cnt = 0;
        for (int m = 0; m < M; m++) {
            int idx = deque.indexOf(arr[m]);
            int mid = ((N - m) % 2 == 0) ? (N - m) / 2 - 1 : (N - m) / 2;
            if (idx <= mid) {
                for (int i = 0; i < idx; i++) {
                    deque.addLast(deque.pollFirst());
                    cnt++;
                }
            }
            else {
                for (int i = 0; i < (N - m) - idx; i++) {
                    deque.addFirst(deque.pollLast());
                    cnt++;
                }
            }
            deque.pollFirst();
        }

        System.out.print(cnt);
    }
}
