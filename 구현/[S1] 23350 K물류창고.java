import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_23350_K물류창고 {

    static BufferedReader br;
    static StringTokenizer st;

    static class Container {
        int priority;
        int weight;

        public Container(int priority, int weight) {
            this.priority = priority;
            this.weight = weight;
        }
    }

    static int containerCount, priorityCount;
    static Queue<Container> queue;
    static int[] containerCounts;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(findSum());
    }

    static int findSum() {
        int sum = 0;

        for (int p = priorityCount; p >= 1; p--) {
            Stack<Container> stack = new Stack<>();

            while (containerCounts[p] > 0) {
                Container now = queue.poll();

                if (now.priority < p) {
                    sum += now.weight;
                    queue.add(now);
                    continue;
                }

                containerCounts[p]--;

                Stack<Container> temp = new Stack<>();

                while (!stack.isEmpty() && stack.peek().weight < now.weight) {
                    Container c = stack.pop();
                    sum += c.weight;
                    temp.push(c);
                }

                sum += now.weight;
                stack.push(now);

                while (!temp.isEmpty()) {
                    Container c = temp.pop();
                    sum += c.weight;
                    stack.push(c);
                }
            }
        }

        return sum;
    }


    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        containerCount = Integer.parseInt(st.nextToken());
        priorityCount = Integer.parseInt(st.nextToken());

        containerCounts = new int[priorityCount + 1];
        queue = new LinkedList<>();
        for (int container = 0; container < containerCount; container++) {
            st = new StringTokenizer(br.readLine());
            int priority = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            queue.add(new Container(priority, weight));
            containerCounts[priority]++;
        }
    }
}
