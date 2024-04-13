import java.util.Scanner;

public class Main {

    static int n, m;
    static int[][] dist;
    static final int MAX = 400 * 10000 + 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        dist = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dist[i][j] = MAX;
            }
        }

        for (int i = 0; i < m; i++) {
            dist[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int min = MAX;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] != MAX && dist[j][i] != MAX) {
                    min = Math.min(min, dist[i][j] + dist[j][i]);
                }
            }
        }

        System.out.println((min == MAX) ? -1 : min);
    }
}