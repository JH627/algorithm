import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, index;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        index = new int[N + 1];
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        list.add(Integer.MIN_VALUE);

        for (int i = 1; i < N + 1; i++) {
            int left = 1;
            int right = list.size() - 1;

            if (arr[i] > list.get(list.size() - 1)) {
                list.add(arr[i]);
                index[i] = list.size() - 1;
            }
            else {
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (arr[i] <= list.get(mid)) {
                        right = mid;
                    }
                    else {
                        left = mid + 1;
                    }
                }
                list.set(right, arr[i]);
                index[i] = right;
            }
        }

        StringBuilder sb = new StringBuilder();
        int size = list.size() - 1;
        sb.append(size).append("\n");
        Stack<Integer> s = new Stack<>();
        for (int i = N; i > 0; i--) {
            if (index[i] == size) {
                size--;
                s.push(arr[i]);
            }
        }
        while (!s.empty()) {
            sb.append(s.pop()).append(" ");
        }
        System.out.println(sb);
    }

}