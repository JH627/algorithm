import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10025_게으른백곰 {

    static BufferedReader br;
    static StringTokenizer st;

    static class Bucket implements Comparable<Bucket> {
        int position, size;

        public Bucket(int position, int size) {
            this.position = position;
            this.size = size;
        }

        @Override
        public int compareTo(Bucket o) {
            return Integer.compare(this.position, o.position);
        }
    }

    static int bucketCount, maxDistance;
    static Bucket[] buckets;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findMaxCount());
    }

    static int findMaxCount() {
        int max = 0;
        int left = 0;
        int sum = 0;
        for (int bucket = 0; bucket < bucketCount; bucket++) {
            sum += buckets[bucket].size;
            while (buckets[bucket].position - buckets[left].position > maxDistance) {
                sum -= buckets[left].size;
                left++;
            }
            max = Math.max(sum, max);
        }
        return max;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        bucketCount = Integer.parseInt(st.nextToken());
        maxDistance = Integer.parseInt(st.nextToken()) * 2 + 1;

        buckets = new Bucket[bucketCount];
        for (int bucket = 0; bucket < bucketCount; bucket++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int position = Integer.parseInt(st.nextToken());
            buckets[bucket] = new Bucket(position, size);
        }

        Arrays.sort(buckets);
    }
}
