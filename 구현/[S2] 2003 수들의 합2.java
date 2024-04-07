import java.util.Scanner;

public class Main {

    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int s, e, sum, cnt;
        s = e = cnt = sum = 0;

        while (true) {
            if (sum >= m) {
                sum -= arr[s++];
            }
            else if (e == n) {
                break;
            }
            else {
                sum += arr[e++];
            }

            if (sum == m) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}