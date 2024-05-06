import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Pair implements Comparable<Pair> {
        int i;
        int h;

        public Pair(int i, int h) {
            this.i = i;
            this.h = h;
        }

        @Override
        public int compareTo(Pair o) {
            return this.h - o.h;
        }
    }

    static int N, M;
    static ArrayList<Pair> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr.add(new Pair(i, Integer.parseInt(st.nextToken())));
            arr.add(new Pair(i, Integer.parseInt(st.nextToken())));
        }

        Collections.sort(arr);

        N -= 1;
        N %= (M * 2);

        System.out.println(arr.get(N).i);
    }
}