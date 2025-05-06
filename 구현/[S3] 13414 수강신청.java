import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_13414_수강신청 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class student {
		String id;
		int version;

		public student(String id, int version) {
			this.id = id;
			this.version = version;
		}
	}

	static HashMap<String, Integer> currentVersion;
	static ArrayList<student> students;

	static int limit;
	static int studentCount;

	public static void main(String[] args) throws Exception {
		init();

		getSuccessStudents();

		System.out.print(sb);
	}

	static void getSuccessStudents() throws IOException {
		currentVersion = new HashMap<>();
		students = new ArrayList<>();

		int version = 0;
		for (int studentIndex = 0; studentIndex < studentCount; studentIndex++) {
			String id = br.readLine();
			students.add(new student(id, version));
			currentVersion.put(id, version++);
		}

		int count = 0;
		for (int studentIndex = 0; studentIndex < studentCount; studentIndex++) {
			if (students.get(studentIndex).version == currentVersion.get(students.get(studentIndex).id)) {
				sb.append(students.get(studentIndex).id).append("\n");
				if (++count == limit) {
					break;
				}
			}
		}
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());

		limit = Integer.parseInt(st.nextToken());
		studentCount = Integer.parseInt(st.nextToken());
	}
}
