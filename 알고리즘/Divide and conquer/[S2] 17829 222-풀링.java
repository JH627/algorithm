import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n;
    static int[][] num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        num = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                num[i][j] = sc.nextInt();
            }
        }

        System.out.println(deep(0, 0, n));
    }

    public static int deep(int x, int y, int l) {
        int a[] = new int[4];

        if (l == 2) {
            int idx = 0;
            for (int i = x; i < x + 2; i++) {
                for (int j = y; j < y + 2; j++) {
                    a[idx++] = num[i][j];
                }
            }
        }
        else {
            int len = l/2;

            a[0] = deep(x, y, len);
            a[1] = deep(x, y + len, len);
            a[2] = deep(x + len, y, len);
            a[3] = deep(x + len, y + len, len);
        }
        Arrays.sort(a);
        return a[2];
    }
}