import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16472_고냥이 {

    static BufferedReader br;

    static int wordLimit;
    static char[] word;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(getMaxLength());
    }

    static int getMaxLength() {
        int[] appeared = new int[26];
        int uniqueWordCount = 0;
        int maxLength = 0;

        int l = 0;
        for (int r = 0; r < word.length; r++) {
            if (appeared[word[r] - 'a'] == 0) {
                uniqueWordCount++;
            }

            appeared[word[r] - 'a']++;

            while (uniqueWordCount > wordLimit) {
                if (--appeared[word[l] - 'a'] == 0) {
                    uniqueWordCount--;
                }
                l++;
            }

            maxLength = Math.max(maxLength, r - l + 1);
        }

        return maxLength;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        wordLimit = Integer.parseInt(br.readLine());
        word = br.readLine().toCharArray();
    }
}
