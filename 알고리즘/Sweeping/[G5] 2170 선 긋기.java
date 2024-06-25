import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<Pos> arr;

    static class Pos implements Comparable<Pos> {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.add(new Pos(a, b));
        }

        Collections.sort(arr);

        int start = Integer.MIN_VALUE;
        int end = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (end < arr.get(i).x) {
                ans += end - start;
                start = arr.get(i).x;
            }
            if (end < arr.get(i).y) {
                end = arr.get(i).y;
            }
        }

        ans += end - start;
        System.out.println(ans);
    }
}