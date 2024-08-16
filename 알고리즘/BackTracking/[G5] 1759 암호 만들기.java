import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static Character[] arr;
    static boolean[] isVowel;
    static StringBuilder sb = new StringBuilder();
    static HashSet<Character> set = new HashSet<>();
    static final Character[] vowels = new Character[]{'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new Character[C];
        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < C; c++) {
            arr[c] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);
        Collections.addAll(set, vowels);

        isVowel = new boolean[C];
        for (int c = 0; c < C; c++) {
            isVowel[c] = set.contains(arr[c]);
        }

        bt(0, 0, 0, 0, "");

        System.out.print(sb);
    }

    static void bt(int idx, int depth, int vowels, int consonants, String s) {
        if (depth == L) {
            if (vowels >= 1 && consonants >= 2) {
                sb.append(s).append("\n");
            }
            return;
        }

        for (int i = idx; i <= C - L + depth; i++) {
            bt(i + 1, depth + 1, (isVowel[i]) ? vowels + 1 : vowels, (isVowel[i]) ? consonants : consonants + 1, s + arr[i]);
        }
    }
}
