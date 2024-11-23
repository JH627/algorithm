import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Score implements Comparable<Score> {
        int num;
        int gold;
        int silver;
        int bronze;

        public Score(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Score o) {
            if (this.gold != o.gold) {
                return o.gold - this.gold;
            }
            if (this.silver != o.silver) {
                return o.silver - this.silver;
            }
            if (this.bronze != o.bronze) {
                return o.bronze - this.bronze;
            }
            return (this.num == K) ? -1 : 1;
        }
    }

    static int N, K;
    static ArrayList<Score> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            arr.add(new Score(num, gold, silver, bronze));
        }

        Collections.sort(arr);

        int rank = 1;
        for (Score score : arr) {
            if (score.num == K) {
                break;
            }
            rank++;
        }

        System.out.print(rank);
    }
}
