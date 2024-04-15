import java.util.Scanner;

public class Main {

    static int n;
    static int[] cost;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        cost = new int[n+1];

        int t, p;
        for (int i = 0; i < n; i++) {
            t = sc.nextInt();
            p = sc.nextInt();

            int endDay = i + t;
            if (endDay <= n) {
                cost[endDay] = Math.max(cost[i] + p, cost[endDay]);
            }
            cost[i + 1] = Math.max(cost[i + 1], cost[i]);
        }

        System.out.println(cost[n]);
    }
}