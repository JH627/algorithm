import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_32133_가위바위보 {

    static int N, M, K;
    static String[] srp;
    static final String MAX = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    static String ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        srp = new String[N];
        for (int n = 0; n < N; n++) {
            srp[n] = br.readLine();
        }

        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tmp.add(i);
        }
        ans = MAX;
        dfs(-1, "", tmp);

        if (ans.equals(MAX)) {
            System.out.print(-1);
        }
        else {
            System.out.println(ans.length());
            System.out.print(ans);
        }
    }

    static void dfs(int idx, String pro, ArrayList<Integer> remain) {
        if (idx != -1 && remain.size() <= K) {
            if (ans.length() >= pro.length()) {
                ans = pro;
            }
            return;
        }

        if (idx + 1 >= M) {
            return;
        }

        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (Integer person : remain) {
            Character next = srp[person].charAt(idx + 1);
            map.computeIfAbsent(next, k -> new ArrayList<>()).add(person);
        }

        for (Character next : map.keySet()) {
            char nextOp;
            if (next == 'R') {
                nextOp = 'S';
            }
            else if (next == 'P') {
                nextOp = 'R';
            }
            else {
                nextOp = 'P';
            }
            dfs(idx + 1, pro + nextOp, map.get(next));
        }
    }
}
