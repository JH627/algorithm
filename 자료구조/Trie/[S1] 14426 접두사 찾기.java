import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_14426_접두사찾기 {

    static BufferedReader br;
    static StringTokenizer st;

    static class Trie {
        Character c;
        HashMap<Character, Trie> children;

        public Trie(Character c) {
            this.c = c;
            this.children = new HashMap<>();
        }
    }

    static int stringCount, queryCount;
    static Trie root;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findPrefixCount());
    }

    static int findPrefixCount() throws IOException {
        int count = 0;
        for (int query = 0; query < queryCount; query++) {
            String word = br.readLine();
            boolean isPrefix = true;
            Trie cursor = root;
            for (int wordIndex = 0; wordIndex < word.length(); wordIndex++) {
                if (cursor.children.containsKey(word.charAt(wordIndex))) {
                    cursor = cursor.children.get(word.charAt(wordIndex));
                }
                else {
                    isPrefix = false;
                    break;
                }
            }

            if (isPrefix) {
                count++;
            }
        }

        return count;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        stringCount = Integer.parseInt(st.nextToken());
        queryCount = Integer.parseInt(st.nextToken());

        root = new Trie(null);
        for (int string = 0; string < stringCount; string++) {
            String word = br.readLine();
            Trie cursor = root;
            for (int wordIndex = 0; wordIndex < word.length(); wordIndex++) {
                if (cursor.children.containsKey(word.charAt(wordIndex))) {
                    cursor = cursor.children.get(word.charAt(wordIndex));
                }
                else {
                    cursor.children.put(word.charAt(wordIndex), new Trie(word.charAt(wordIndex)));
                    cursor = cursor.children.get(word.charAt(wordIndex));
                }
            }
        }
    }
}
