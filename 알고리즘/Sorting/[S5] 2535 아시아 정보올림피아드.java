import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static class Person {
        int country;
        int code;
        int score;

        public Person(int country, int code, int score) {
            this.country = country;
            this.code = code;
            this.score = score;
        }
    }

    static int N;
    static ArrayList<Person> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr.add(new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(arr, Comparator.comparingInt(o -> -o.score));

        int[] count = new int[N + 1];
        StringBuilder sb = new StringBuilder();
        int clock = 0;
        for (Person p : arr) {
            if (count[p.country] < 2) {
                sb.append(p.country).append(" ").append(p.code).append("\n");
                count[p.country]++;
                clock++;
            }
            if (clock == 3) {
                break;
            }
        }

        System.out.println(sb);
    }
}
