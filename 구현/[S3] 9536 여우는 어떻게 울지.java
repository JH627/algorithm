import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_9536_여우는어떻게울지 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static ArrayList<String> sounds;
    static HashSet<String> other;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < TC; testCase++) {
            init();
            findFoxSound();
        }

        System.out.print(sb);
    }

    static void findFoxSound() {
        for (String sound : sounds) {
            if (!other.contains(sound)) {
                sb.append(sound).append(" ");
            }
        }
        sb.append("\n");
    }

    static void init() throws IOException {
        sounds = new ArrayList<>();
        other = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            sounds.add(st.nextToken());
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            if (st.countTokens() == 5) {
                break;
            }

            while (st.hasMoreTokens()) {
                String animal = st.nextToken();
                String goes = st.nextToken();
                other.add(st.nextToken());
            }
        }
    }
}
