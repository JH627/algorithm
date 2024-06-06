import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> arr = new ArrayList<>(set);
        Collections.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (Integer n : arr) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}