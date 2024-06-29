import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, R, Q;
    static ArrayList<ArrayList<Integer>> arr;
    static int[] child;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        child = new int[N + 1];
        arr = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        countChild(R, -1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(child[q]).append("\n");
        }

        System.out.println(sb);
    }

    static void countChild(int cur, int parent) {
        child[cur] = 1;
        for (Integer next : arr.get(cur)) {
            if (next != parent) {
                countChild(next, cur);
                child[cur] += child[next];
            }
        }
    }
}