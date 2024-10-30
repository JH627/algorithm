import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idx = 1;
        int max = 0;
        HashSet<Integer> set;
        for (int i = 0; i < N; i++) {
            set = new HashSet<>();
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < N; k++) {
                    if (k == j) {
                        continue;
                    }
                    if (arr[i][j] == arr[k][j]) {
                        set.add(k);
                    }
                }
            }
            if (max < set.size()) {
                max = set.size();
                idx = i;
            }
        }

        System.out.print(idx + 1);
    }
}
