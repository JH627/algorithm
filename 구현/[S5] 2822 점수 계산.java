import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            arr.add(Integer.parseInt(br.readLine()));
            sorted.add(arr.get(i));
        }

        Collections.sort(sorted);

        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            if (arr.get(i) > sorted.get(2)) {
                sum += arr.get(i);
                sb.append(i + 1).append(" ");
            }
        }

        sb.insert(0, sum + "\n");

        System.out.println(sb);
    }
}