import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> list = new ArrayList<>();

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        Collections.sort(list);

        int max = 0;
        for (int i = 0; i < n; i++) {
            int weight = list.get(i) * (n - i);
            max = Math.max(max, weight);
        }

        System.out.println(max);
    }
}