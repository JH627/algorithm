import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static final int SIZE = 10002;

    static class MyQueue {
        int front, rear;
        int[] arr;

        public MyQueue() {
            this.arr = new int[SIZE];
            front = 0;
            rear = 0;
        }

        public void push(int x) {
            rear = next(rear);
            arr[rear] = x;
        }

        public int pop() {
            if (isEmpty()) {
                return -1;
            }
            front = next(front);
            return arr[front];
        }

        public int size() {
            if (rear >= front) {
                return rear - front;
            }
            return SIZE - front + rear;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public int front() {
            return (isEmpty()) ? -1 : arr[next(front)];
        }

        public int back() {
            return (isEmpty()) ? -1 : arr[rear];
        }

        private int next(int n) {
            return (n + 1 == SIZE) ? 0 : n + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        MyQueue q = new MyQueue();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            switch (s) {
                case "push":
                    q.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(q.pop()).append("\n");
                    break;
                case "size":
                    sb.append(q.size()).append("\n");
                    break;
                case "empty":
                    sb.append(q.isEmpty() ? "1" : "0").append("\n");
                    break;
                case "front":
                    sb.append(q.front()).append("\n");
                    break;
                case "back":
                    sb.append(q.back()).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }
}
