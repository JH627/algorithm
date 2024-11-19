import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Person implements Comparable<Person> {
        String name;
        int year;
        int month;
        int day;

        public Person(String name, int day, int month, int year) {
            this.name = name;
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public int compareTo(Person o) {
            if (this.year != o.year) {
                return this.year - o.year;
            }
            if (this.month != o.month) {
                return this.month - o.month;
            }
            return this.day - o.day;
        }
    }

    static ArrayList<Person> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            arr.add(new Person(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(arr);

        StringBuilder sb = new StringBuilder();
        sb.append(arr.get(arr.size() - 1).name).append("\n");
        sb.append(arr.get(0).name);
        System.out.print(sb);
    }
}
