import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class Main {

    static class Trie {
        TreeMap<Character, Trie> map;
        boolean end;

        public Trie() {
            map = new TreeMap<>();
            this.end = false;
        }

        public boolean insert(String s) {
            Trie now = this;
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);

                now.map.putIfAbsent(c, new Trie());
                now = now.map.get(c);

                if (now.end) {
                    return false;
                }

                if (i == len - 1) {
                    now.end = true;
                }
            }
            return true;
        }
    }

    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            ArrayList<String> arr = new ArrayList<>();
            Trie trie = new Trie();
            for (int i = 0; i < N; i++) {
                arr.add(br.readLine());
            }
            arr.sort(Comparator.comparing(o -> o.length()));

            boolean possible = true;
            for (String s : arr) {
                s = s.trim();
                if (!trie.insert(s)) {
                    possible = false;
                    break;
                }
            }

            sb.append(possible ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }
}
