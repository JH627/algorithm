import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_5670_휴대폰자판 {

    static BufferedReader br;
    static StringBuilder sb;

    static class Node {
        HashMap<Character, Node> childNode;
        boolean isLeaf;

        public Node() {
            childNode = new HashMap<>();
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(char[] str) {
            Node cur = root;
            for (int index = 0; index < str.length; index++) {
				cur.childNode.computeIfAbsent(str[index], k -> new Node());
                cur = cur.childNode.get(str[index]);
                if (index == str.length - 1) {
                    cur.isLeaf = true;
                }
            }
        }
    }

    static int wordCount;
    static int count;
    static Trie trie;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (init()) {
            insertWords();
            getSearchInputCount(trie.root, -1);
			sb.append(String.format("%.2f\n", getAvg()));
        }

        System.out.print(sb);
    }

    static void insertWords() throws IOException {
        for (int word = 0; word < wordCount; word++) {
            trie.insert(br.readLine().toCharArray());
        }
    }

    static void getSearchInputCount(Node node, int inputCount) {
        if (node == trie.root || node.childNode.size() > 1 || node.isLeaf) {
            inputCount++;
        }

        if (node.isLeaf) {
            count += inputCount;
        }

        for (Node child : node.childNode.values()) {
            getSearchInputCount(child, inputCount);
        }
    }

    static double getAvg() {
		return (double)count / (double)wordCount;
    }

    static boolean init() throws IOException {
        String wordCountStr;
        if ((wordCountStr = br.readLine()) != null) {
            wordCount = Integer.parseInt(wordCountStr);
            trie = new Trie();

            count = 0;
            return true;
        }
        return false;
    }

}
