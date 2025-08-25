import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1781_컵라면 {

    static BufferedReader br;
    static StringTokenizer st;

    static class Homework implements Comparable<Homework> {
        int deadLine;
        int cupCount;

        public Homework(int deadLine, int cupCount) {
            this.deadLine = deadLine;
            this.cupCount = cupCount;
        }

        @Override
        public int compareTo(Homework o) {
            if (this.cupCount != o.cupCount) {
                return o.deadLine - this.deadLine;
            }
            return o.deadLine - this.deadLine;
        }
    }

    static int homeworkCount;
    static PriorityQueue<Homework> homeworks;
    static Homework[] homeworkArray;

    public static void main(String[] args) throws IOException {
        init();
        System.out.print(getMaxCupCount());
    }

    static int getMaxCupCount() {
        int maxCupCount = 0;
        homeworks = new PriorityQueue<>(new Comparator<Homework>() {
            @Override
            public int compare(Homework o1, Homework o2) {
                return o2.cupCount - o1.cupCount;
            }
        });

        int day = homeworkArray[0].deadLine;
        int homeworkIndex = 0;
        while (!homeworks.isEmpty() || homeworkIndex < homeworkCount) {
            while (homeworkIndex < homeworkCount && day <= homeworkArray[homeworkIndex].deadLine) {
                homeworks.add(homeworkArray[homeworkIndex++]);
            }

            day--;

            if (homeworks.isEmpty()) {
                continue;
            }

            Homework homework = homeworks.poll();
            maxCupCount += homework.cupCount;

            if (day == 0) {
                break;
            }
        }

        return maxCupCount;
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        homeworkCount = Integer.parseInt(br.readLine());
        homeworkArray = new Homework[homeworkCount];
        for (int homework = 0; homework < homeworkCount; homework++) {
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cupCount = Integer.parseInt(st.nextToken());
            homeworkArray[homework] = new Homework(deadLine, cupCount);
        }

        Arrays.sort(homeworkArray);
    }
}
