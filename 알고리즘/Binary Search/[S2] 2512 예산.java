import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static public int n, m;
    static public ArrayList<Integer> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        Collections.sort(list);

        m = sc.nextInt();

        long left = 0;
        long right = list.get(n - 1);
        long mid;

        while (left <= right) {
            mid = (left + right) / 2;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (list.get(i) > mid) {
                    sum += mid;
                }
                else {
                    sum += list.get(i);
                }
            }

            if (sum <= m) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }
}