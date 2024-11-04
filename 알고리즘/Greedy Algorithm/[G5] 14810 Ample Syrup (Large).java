import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static class Cake {
        int r, h;

        public Cake(int r, int h) {
            this.r = r;
            this.h = h;
        }
    }

    static final double PI = Math.PI;
    static int T, K, N;
    static ArrayList<Cake> cakes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cakes = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                cakes.add(new Cake(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            cakes.sort(Comparator.comparingInt(cake -> cake.r));
            Collections.reverse(cakes);

            double maxArea = 0;

            for (int i = 0; i <= N - K; i++) {
                Cake cake = cakes.get(i);

                double sum = 0;
                sum += (PI * cake.r * cake.r) + (2 * PI * cake.r * cake.h);

                ArrayList<Double> overCakes = new ArrayList<>();
                for (int j = i + 1; j < N; j++) {
                    overCakes.add((2 * PI * cakes.get(j).r * cakes.get(j).h));
                }

                overCakes.sort(Collections.reverseOrder());

                for (int j = 0; j < K - 1; j++) {
                    sum += overCakes.get(j);
                }

                maxArea = Math.max(maxArea, sum);
            }

            sb.append(String.format("Case #%d: %.9f\n", t + 1, maxArea));
        }

        System.out.print(sb);
    }
}
