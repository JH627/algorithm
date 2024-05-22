import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));

        StringBuilder sb = new StringBuilder();
        int num;
        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());

            if (minQueue.size() == maxQueue.size()) {
                maxQueue.add(num);
            } else {
                minQueue.add(num);
            }

            if (!minQueue.isEmpty() && !maxQueue.isEmpty()) {
                if (minQueue.peek() < maxQueue.peek()) {
                    int tmp = minQueue.poll();
                    minQueue.add(maxQueue.poll());
                    maxQueue.add(tmp);
                }
            }
            sb.append(maxQueue.peek()).append("\n");
        }

        System.out.println(sb);
    }
}