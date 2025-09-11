import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17089_세친구 {

    static BufferedReader br;
    static StringTokenizer st;

    static int peopleCount, friendCount;
    static ArrayList<ArrayList<Integer>> friends;
    static boolean[] visited;
    static int[] selected;
    static int min;

    public static void main(String[] args) throws IOException {
        init();
        for (int people = 0; people < peopleCount; people++) {
            visited[people] = true;
            getMinFriendCount(people, 0, 0);
            visited[people] = false;
        }
        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void getMinFriendCount(int now, int sum, int depth) {
        sum += friends.get(now).size();

        if (depth == 2) {
            sum -= 2;
            boolean connected = false;
            for (Integer next : friends.get(now)) {
                for (int index = 0; index < 2; index++) {
                    if (next == selected[index]) {
                        if (index == 0) {
                            connected = true;
                        }
                        sum -= 2;
                    }
                }
            }

            if (connected) {
                min = Math.min(min, sum);
            }
            return;
        }
        selected[depth] = now;

        for (Integer next : friends.get(now)) {
            if (!visited[next]) {
                visited[next] = true;
                getMinFriendCount(next, sum, depth + 1);
                visited[next] = false;
            }
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        peopleCount = Integer.parseInt(st.nextToken());
        friendCount = Integer.parseInt(st.nextToken());

        friends = new ArrayList<>();
        for (int people = 0; people < peopleCount; people++) {
            friends.add(new ArrayList<>());
        }

        for (int friend = 0; friend < friendCount; friend++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            friends.get(x).add(y);
            friends.get(y).add(x);
        }

        visited = new boolean[peopleCount];
        selected = new int[2];

        min = Integer.MAX_VALUE;
    }
}
