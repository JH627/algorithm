import java.util.Scanner;

public class Main {

    static int n;
    static int[] water;
    static int[] answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        water = new int[n];

        for (int i = 0; i < n; i++) {
            water[i] = sc.nextInt();
        }

        answer = new int[2];
        long sum;
        long min = Long.MAX_VALUE;

        int left = 0;
        int right = n - 1;
        while (left < right) {
             sum = water[left] + water[right];
             if (min > Math.abs(sum)) {
                 min = Math.abs(sum);
                 answer[0] = water[left];
                 answer[1] = water[right];
             }

             if (sum > 0) {
                 right--;
             }
             else if (sum < 0) {
                 left++;
             }
             else {
                 break;
             }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}