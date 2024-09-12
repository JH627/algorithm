import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] degree;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<Integer> ans;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        degree = new int[N + 1];
        q = new LinkedList<>();
        ans = new ArrayList<>();
        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int[] order = new int[c];
            for (int i = 0; i < c; i++) {
                order[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < c - 1; i++) {
                graph.get(order[i]).add(order[i + 1]);
                degree[order[i + 1]]++;
            }
        }

        find();

        StringBuilder sb = new StringBuilder();
        for (Integer n : ans) {
            sb.append(n).append("\n");
        }
        System.out.print((ans.size() == N) ? sb : "0");
    }

    static void find() {
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            ans.add(now);

            for (Integer next : graph.get(now)) {
                if (--degree[next] == 0) {
                    q.add(next);
                }
            }
        }
    }
}
