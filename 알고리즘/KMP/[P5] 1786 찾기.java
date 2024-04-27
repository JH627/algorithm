import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String t, p;
    static int ans;
    static int tLen, pLen;
    static int[] pi;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = br.readLine();
        p = br.readLine();

        tLen = t.length();
        pLen = p.length();
        pi = new int[pLen];
        sb = new StringBuilder();

        setPi();
        kmp();

        System.out.println(ans);
        System.out.println(sb.toString());
    }

    private static void setPi() {
        int j = 0;
        for (int i = 1; i < pLen; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }
            if (p.charAt(i) == p.charAt(j)) {
                pi[i] = ++j;
            }
        }
    }

    private static void kmp() {
        int j = 0;
        for (int i = 0; i < tLen; i++) {
            while (j > 0 && t.charAt(i) != p.charAt(j)) {
                j = pi[j - 1];
            }
            if (t.charAt(i) == p.charAt(j)) {
                if (j == pLen - 1) {
                    sb.append((i - j + 1) + " ");
                    ans++;
                    j = pi[j];
                }
                else {
                    j++;
                }
            }
        }
    }
}