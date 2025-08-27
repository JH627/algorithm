import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1911_흙길보수하기 {

    static BufferedReader br;
    static StringTokenizer st;

    static class Hole implements Comparable<Hole> {
        int start, end;

        public Hole(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Hole o) {
            return this.start - o.start;
        }
    }

    static int holeCount, treeLength;
    static Hole[] holes;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(getMinTreeCount());
    }

    static int getMinTreeCount() {
        int count = 0;
        int curLastIndex = -1;

        for (int index = 0; index < holeCount; index++) {
            if (curLastIndex < holes[index].start) {
                curLastIndex = holes[index].start;
            }
            while (curLastIndex < holes[index].end) {
                curLastIndex += treeLength;
                count++;
            }
        }

        return count;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        holeCount = Integer.parseInt(st.nextToken());
        treeLength = Integer.parseInt(st.nextToken());

        holes = new Hole[holeCount];
        for (int holeIndex = 0; holeIndex < holeCount; holeIndex++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            holes[holeIndex] = new Hole(start, end);
        }

        Arrays.sort(holes);
    }
}
