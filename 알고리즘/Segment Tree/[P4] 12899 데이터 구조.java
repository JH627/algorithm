import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, T, X;
    static int[] segTree;
    static final int MAX = 2000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        segTree = new int[MAX * 4];
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        for (int t = 0; t < N; t++) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            switch (T) {
                case 1:
                    update(1, 1, MAX, X);
                    break;
                case 2:
                    sb.append(delete(1, 1, MAX, X)).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }


    static void update(int idx, int s, int e, int x) {
        if (x < s || x > e) {
            return;
        }
        segTree[idx]++;
        if (s == e) {
            return;
        }
        int mid = (s + e) / 2;
        update(idx * 2, s, mid, x);
        update(idx * 2 + 1, mid + 1, e, x);
    }

    static int delete(int idx, int s, int e, int x) {
        segTree[idx]--;
        if (s == e) {
            return s;
        }

        int mid = (s + e) / 2;
        //왼쪽 확인해서 적은 경우 오른쪽으로 개수 갱신 후 탐색
        if (segTree[2 * idx] < x) {
            return delete(idx * 2 + 1, mid + 1, e, x - segTree[2 * idx]);
        }
        else {
            return delete(idx * 2, s, mid, x);
        }
    }
}
