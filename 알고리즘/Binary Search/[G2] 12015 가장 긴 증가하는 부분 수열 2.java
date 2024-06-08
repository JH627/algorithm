import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static ArrayList<Integer> arr;
    static ArrayList<Integer> LIS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new ArrayList<>();
        LIS = new ArrayList<>();

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        LIS.add(0);
        for (int i = 0; i < N; i++) {
            int now = arr.get(i);
            if (LIS.get(LIS.size() - 1) < now) {
                LIS.add(now);
            }
            else {
                int start = 0;
                int end = LIS.size() - 1;
                while (start < end) {
                    int mid = (start + end) / 2;
                    if (LIS.get(mid) < now) {
                        start = mid + 1;
                    }
                    else {
                        end = mid;
                    }
                }
                LIS.set(end, now);
            }
        }

        System.out.println(LIS.size() - 1);
    }
}