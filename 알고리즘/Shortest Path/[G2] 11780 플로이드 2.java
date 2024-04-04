import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static long[][] dist;
    static List<Integer>[][] way;
    static int n, e;
    static int a, b, c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dist = new long[n+1][n+1];
        way = new ArrayList[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                way[i][j] = new ArrayList<>();
                dist[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
            }
        }

        e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            if (dist[a][b] > c) {
                way[a][b].clear();
                way[a][b].add(a);
                way[a][b].add(b);
                dist[a][b] = c;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        updateWay(i, j, k);
                    }
                }
            }
        }

        printDist();
        printWay();
    }

    private static void printWay() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (way[i][j].size() == 0) {
                    System.out.println("0");
                    continue;
                }
                System.out.print(way[i][j].size() + " ");
                for (Object node : way[i][j]) {
                    System.out.print(node + " ");
                }
                System.out.println();
            }
        }
    }

    private static void printDist() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.printf("0 ");
                    continue;
                }
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void updateWay(int i, int j, int k) {
        way[i][j].clear();
        for (Integer prev : way[i][k]) {
            way[i][j].add(prev);
        }
        way[i][j].remove(way[i][j].size()-1);
        for (Integer post : way[k][j]) {
            way[i][j].add(post);
        }
    }
}