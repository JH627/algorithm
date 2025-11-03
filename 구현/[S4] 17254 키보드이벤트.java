import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17254_키보드이벤트 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static class Click implements Comparable<Click> {
        int notebookId;
        int time;
        char alpha;

        public Click(int notebookId, int time, char alpha) {
            this.notebookId = notebookId;
            this.time = time;
            this.alpha = alpha;
        }

        @Override
        public int compareTo(Click o) {
            if (this.time == o.time) {
                return this.notebookId - o.notebookId;
            }
            return this.time - o.time;
        }
    }

    static int notebookCount, clickCount;
    static Click[] clicks;

    public static void main(String[] args) throws IOException {
        init();
        printWord();
    }

    static void printWord() {
        sb = new StringBuilder();
        for (int click = 0; click < clickCount; click++) {
            sb.append(clicks[click].alpha);
        }
        System.out.print(sb);
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        notebookCount = Integer.parseInt(st.nextToken());
        clickCount = Integer.parseInt(st.nextToken());

        clicks = new Click[clickCount];
        for (int click = 0; click < clickCount; click++) {
            st = new StringTokenizer(br.readLine());
            clicks[click] = new Click(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }

        Arrays.sort(clicks);
    }
}
