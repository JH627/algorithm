import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[] nct = {'A', 'C', 'G', 'T'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] c = new char[N][M];

        for (int n = 0; n < N; n++) {
            String s = br.readLine();
            for (int m = 0; m < M; m++) {
                c[n][m] = s.charAt(m);
            }
        }

        StringBuilder sb = new StringBuilder();
        int distSum = 0;
        for (int m = 0; m < M; m++) {
            int[] count = new int[4];
            for (int n = 0; n < N; n++) {
                for (int i = 0; i < 4; i++) {
                    if (c[n][m] == nct[i]) {
                        count[i]++;
                        break;
                    }
                }
            }
            int maxIdx = 0;
            for (int i = 1; i < 4; i++) {
                if (count[maxIdx] < count[i]) {
                    maxIdx = i;
                }
            }
            sb.append(nct[maxIdx]);
            distSum += N - count[maxIdx];
        }

        sb.append("\n").append(distSum);
        System.out.print(sb);
    }
}
