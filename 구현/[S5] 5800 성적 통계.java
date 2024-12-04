import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    
    static int K, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            ArrayList<Integer> list = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(list);
            int min = list.get(0);
            int max = list.get(N - 1);

            int maxGap = list.get(1) - list.get(0);
            for (int n = 2; n < N; n++) {
                maxGap = Math.max(maxGap, list.get(n) - list.get(n - 1));
            }
            sb.append("Class ").append(k + 1).append("\n");
            sb.append(String.format("Max %d, Min %d, Largest gap %d\n", max, min, maxGap));
        }

        System.out.print(sb);
    }
}
