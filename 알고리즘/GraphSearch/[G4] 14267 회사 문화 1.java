import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14267_회사문화1 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int memberCount, greatCount;
    static ArrayList<ArrayList<Integer>> graph;
    static long[] greatPoint;

    public static void main(String[] args) throws IOException {
        init();
        updatePoint(1, 0);
        printPointStatus();
    }

    static void updatePoint(int member, long great) {
        greatPoint[member] += great;
        for (Integer next : graph.get(member)) {
            updatePoint(next, greatPoint[member]);
        }
    }

    static void printPointStatus() {
        sb = new StringBuilder();
        for (int member = 1; member <= memberCount; member++) {
            sb.append(greatPoint[member]).append(" ");
        }
        System.out.print(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        memberCount = Integer.parseInt(st.nextToken());
        greatCount = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int member = 0; member <= memberCount; member++) {
            graph.add(new ArrayList<>());
        }
        greatPoint = new long[memberCount + 1];
        st = new StringTokenizer(br.readLine());
        for (int member = 1; member <= memberCount; member++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                continue;
            }
            graph.get(parent).add(member);
        }

        for (int index = 0; index < greatCount; index++) {
            st = new StringTokenizer(br.readLine());
            int member = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());
            greatPoint[member] += point;
        }
    }
}
