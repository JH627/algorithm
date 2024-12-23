import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] ans;
    static ArrayList<Integer> arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = new int[M];

        arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);

        ans = new int[M];
        bt(0);

        System.out.print(sb);
    }

    static void bt(int depth) {
        if (depth == M) {
            for (int m = 0; m < M; m++) {
                sb.append(ans[m]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int n = 0; n < N; n++) {
            ans[depth] = arr.get(n);
            bt(depth + 1);
        }
    }
}
