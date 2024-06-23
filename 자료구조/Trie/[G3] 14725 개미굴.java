import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static StringBuilder sb;

    static class Node {
        TreeMap<String, Node> map;

        public Node() {
            map = new TreeMap<>();
        }
    }

    static class Trie {
        Node root;

        Trie() {
            root = new Node();
        }

        Node getRoot() {
            return root;
        }

        void insert(String[] strings) {
            Node node = root;
            for (String s : strings) {
                if (!node.map.containsKey(s)) {
                    node.map.put(s, new Node());
                }
                node = node.map.get(s);
            }
        }

        void print(Node now, int depth) {
            for (String s : now.map.keySet()) {
                for (int i = 0; i < depth; i++) {
                    sb.append("--");
                }
                sb.append(s).append("\n");

                Node next = now.map.get(s);
                print(next, depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            String[] s = new String[n];
            for (int j = 0; j < n; j++) {
                s[j] = st.nextToken();
            }
            trie.insert(s);
        }

        Node root = trie.getRoot();
        trie.print(root, 0);

        System.out.println(sb);
    }
}