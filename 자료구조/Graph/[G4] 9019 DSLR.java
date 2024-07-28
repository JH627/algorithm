import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        Integer num;
        String method;

        public Node(Integer num, String method) {
            this.num = num;
            this.method = method;
        }
    }

    static int T;
    static int a, b;
    static Queue<Node> q;
    static HashSet<Integer> set;
    static final Character[] methods = {'D', 'S', 'L', 'R'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            q = new LinkedList<>();
            set = new HashSet<>();

            sb.append(bfs()).append("\n");
        }

        System.out.println(sb);
    }

    static String bfs() {
        q.add(new Node(a, ""));
        while (!q.isEmpty()) {
            Node now = q.poll();
            Integer[] nextNumber = getNextNumber(now.num);
            for (int i = 0; i < 4; i++) {
                String nextMethod = now.method + methods[i];
                if (nextNumber[i] == b) {
                    return nextMethod;
                }
                if (!set.contains(nextNumber[i])) {
                    set.add(nextNumber[i]);
                    q.add(new Node(nextNumber[i], nextMethod));
                }
            }
        }
        return null;
    }

    static Integer[] getNextNumber(Integer input) {
        Integer[] res = new Integer[4];
        res[0] = (input * 2) % 10000;
        res[1] = (10000 + input - 1) % 10000;
        res[2] = (input * 10) % 10000 + (input / 1000);
        res[3] = (input / 10) + 1000 * (input % 10);
        return res;
    }
}
