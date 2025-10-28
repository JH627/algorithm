import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12892_생일선물 {

    static BufferedReader br;
    static StringTokenizer st;

    static class Item implements Comparable<Item> {
        long cost, joy;

        public Item(long cost, long joy) {
            this.cost = cost;
            this.joy = joy;
        }

        @Override
        public int compareTo(Item o) {
            return Long.compare(cost, o.cost);
        }
    }

    static int itemCount, gapLimit;
    static Item[] items;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findMaxJoy());
    }

    static long findMaxJoy() {
        long maxJoy = 0;

        int l = 0;
        long curJoy = 0;
        for (int r = 0; r < itemCount; r++) {
            curJoy += items[r].joy;
            while (items[r].cost - items[l].cost >= gapLimit) {
                curJoy -= items[l].joy;
                l++;
            }
            maxJoy = Math.max(maxJoy, curJoy);
        }

        return maxJoy;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        itemCount = Integer.parseInt(st.nextToken());
        gapLimit = Integer.parseInt(st.nextToken());

        items = new Item[itemCount];
        for (int item = 0; item < itemCount; item++) {
            st = new StringTokenizer(br.readLine());
            items[item] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(items);
    }
}
