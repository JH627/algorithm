import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    static int N;
    static ArrayList<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(arr, Comparator.comparingInt(o -> -o));
        
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (i % 3 == 2) {
                continue;
            }
            sum += arr.get(i);
        }

        System.out.println(sum);
    }
}
