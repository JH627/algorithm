import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Edge> arr;
    static ArrayList<Integer> dp, ans;
    static int[] index;

    static class Edge implements Comparable<Edge> {
        int start, end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Edge o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        dp = new ArrayList<>();
        index = new int[N];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            arr.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(arr);

        dp.add(Integer.MIN_VALUE);
        for (int i = 0; i < N; i++) {
            if (arr.get(i).end > dp.get(dp.size() - 1)) {
                dp.add(arr.get(i).end);
                index[i] = dp.size() - 1;
                continue;
            }

            int left = 1;
            int right = dp.size() - 1;

            //List로 변환 시 Arrays.binarySearch() 활용가능
            while (left < right) {
                int mid = (left + right) / 2;
                if (arr.get(i).end <= dp.get(mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            dp.set(right, arr.get(i).end);
            index[i] = right;
        }

        StringBuilder sb = new StringBuilder();
        ans = new ArrayList<>();
        int size = dp.size() - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (index[i] == size) {
                size--;
            }
            else {
                ans.add(arr.get(i).start);
            }
        }

        sb.append(ans.size()).append("\n");
        for (int i = ans.size() - 1; i >= 0; i--) {
            sb.append(ans.get(i)).append("\n");
        }

        System.out.print(sb);
    }
}
