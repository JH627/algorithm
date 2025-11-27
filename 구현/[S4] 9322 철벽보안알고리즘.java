import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_9322_철벽보안알고리즘 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int wordCount;
    static String[] fKey, sKey, problem;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < TC; testCase++) {
            init();
            solveProblem();
        }

        System.out.print(sb);
    }

    static void solveProblem() {
        HashMap<String, String> map = new HashMap<>();
        for (int word = 0; word < wordCount; word++) {
            map.put(sKey[word], problem[word]);
        }

        for (int word = 0; word < wordCount; word++) {
            sb.append(map.get(fKey[word])).append(" ");
        }

        sb.append("\n");
    }

    static void init() throws IOException {
        wordCount = Integer.parseInt(br.readLine());
        fKey = new String[wordCount];
        sKey = new String[wordCount];
        problem = new String[wordCount];

        st = new StringTokenizer(br.readLine());
        for (int word = 0; word < wordCount; word++) {
            fKey[word] = st.nextToken();
        }

        st = new StringTokenizer(br.readLine());
        for (int word = 0; word < wordCount; word++) {
            sKey[word] = st.nextToken();
        }

        st = new StringTokenizer(br.readLine());
        for (int word = 0; word < wordCount; word++) {
            problem[word] = st.nextToken();
        }
    }
}
