import java.util.Scanner;

public class Main {

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[] op;
    static int[] num;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        op = new int[4];
        num = new int[n];

        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            op[i] = sc.nextInt();
        }

        dfs(num[0], 1);

        System.out.println(max);
        System.out.println(min);
    }


    public static void dfs(int sum, int idx) {
        if (idx == n) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i]--; // 방문 표시

                switch (i) {
                    case 0:
                        dfs(sum + num[idx], idx + 1);
                        break;
                    case 1:
                        dfs(sum - num[idx], idx + 1);
                        break;
                    case 2:
                        dfs(sum * num[idx], idx + 1);
                        break;
                    case 3:
                        dfs(sum / num[idx], idx + 1);
                        break;
                }

                op[i]++; // 복구
            }
        }
    }
}