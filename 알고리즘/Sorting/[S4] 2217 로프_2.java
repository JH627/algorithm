import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Integer[] list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        list = new Integer[n];

        for (int i = 0; i < n; i++) {
            list[i] = sc.nextInt();
        }

        myQSort(0, n - 1);

        int max = 0;
        for (int i = 0; i < n; i++) {
            int weight = list[i] * (n - i);
            max = Math.max(max, weight);
        }

        System.out.println(max);
    }

    public static void myQSort(int left, int right) {
        int part = partition(left, right);
        if (left < part - 1) {
            myQSort(left, part - 1);
        }
        if (part < right) {
            myQSort(part, right);
        }
    }

    public static int partition(int left, int right) {
        int pivot = list[(left + right) / 2];
        while (left <= right) {
            while (list[left] < pivot) {
                left++;
            }
            while (list[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = list[left];
                list[left] = list[right];
                list[right] = temp;

                left++;
                right--;
            }
        }
        return left;
    }
}
