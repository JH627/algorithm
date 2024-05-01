import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static class Student implements Comparable<Student> {
        String name;
        int lan;
        int eng;
        int math;

        public Student(String name, int lan, int eng, int math) {
            this.name = name;
            this.lan = lan;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {
            if (this.lan != o.lan) {
                return o.lan - this.lan;
            }
            if (this.eng != o.eng) {
                return this.eng - o.eng;
            }
            if (this.math != o.math) {
                return o.math - this.math;
            }
            return this.name.compareTo(o.name);
        }
    }

    static int n;
    static ArrayList<Student> arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        sb = new StringBuilder();

        String[] str;
        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            arr.add(new Student(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3])));
        }

        Collections.sort(arr);

        for (Student student : arr) {
            sb.append(student.name).append("\n");
        }

        System.out.println(sb);
    }
}