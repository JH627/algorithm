import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static ArrayList<Jewel> jewels;
    static int[] bag;

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int cost;

        public Jewel(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Jewel o) {
            if (this.weight == o.weight) {
                return o.cost - this.cost;
            }
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new ArrayList<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(jewels);

        bag = new int[K];
        for (int k = 0; k < K; k++) {
            bag[k] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bag);

        long ans = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        int j = 0;
        for (int i = 0; i < K; i++) {
            while (j < N && jewels.get(j).weight <= bag[i]) {
                q.add(jewels.get(j).cost);
                j++;
            }

            if (!q.isEmpty()) {
                ans += q.poll();
            }
        }

        System.out.print(ans);
    }
}
