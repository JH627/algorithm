import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6581_HTML {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int LINE_MAX = 80;
    static final String HR = "--------------------------------------------------------------------------------";
    static final String HR_TAG = "<hr>";
    static final String BR_TAG = "<br>";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String line = null;
        int currentCharCount = 0;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                if (HR_TAG.equals(word)) {
                    if (currentCharCount != 0) {
                        sb.append("\n");
                    }
                    sb.append(HR).append("\n");
                    currentCharCount = 0;
                    continue;
                }
                if (BR_TAG.equals(word)) {
                    sb.append("\n");
                    currentCharCount = 0;
                    continue;
                }

                if (currentCharCount == 0) {
                    sb.append(word);
                    currentCharCount = word.length();
                }
                else {
                    if (currentCharCount + word.length() + 1 > LINE_MAX) {
                        sb.append("\n").append(word);
                        currentCharCount = word.length();
                    }
                    else {
                        sb.append(" ").append(word);
                        currentCharCount += word.length() + 1;
                    }
                }
            }
        }


        System.out.print(sb);
    }
}
