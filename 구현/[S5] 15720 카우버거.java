import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int B, C, D;
    static int min = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        min = Math.min(B, C);
        min = Math.min(min, D);

        arr = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arr.add(new ArrayList<>());
        }

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            arr.get(0).add(Integer.parseInt(st.nextToken()));
            sum += arr.get(0).get(i);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr.get(1).add(Integer.parseInt(st.nextToken()));
            sum += arr.get(1).get(i);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < D; i++) {
            arr.get(2).add(Integer.parseInt(st.nextToken()));
            sum += arr.get(2).get(i);
        }

        for (int i = 0; i < 3; i++) {
            Collections.sort(arr.get(i));
            Collections.reverse(arr.get(i));
        }

        int saleSum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < min; j++) {
                saleSum += arr.get(i).get(j) * 0.9;
            }
            for (int j = min; j < arr.get(i).size(); j++) {
                saleSum += arr.get(i).get(j);
            }
        }

        System.out.println(sum);
        System.out.print(saleSum);
    }
}
