import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_6068_시간관리하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Work implements Comparable<Work> {
		int needTime;
		int endTime;

		public Work(int needTime, int endTime) {
			this.needTime = needTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Work o) {
			return Integer.compare(this.endTime, o.endTime);
		}
	}

	static int jobCount;
	static Work[] works;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxWakeUpTime());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		jobCount = Integer.parseInt(br.readLine());

		works = new Work[jobCount];
		for (int job = 0; job < jobCount; job++) {
			st = new StringTokenizer(br.readLine());
			int needTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			works[job] = new Work(needTime, endTime);
		}
	}

	static int findMaxWakeUpTime() {
		Arrays.sort(works);

		int currentTime = works[works.length - 1].endTime - works[works.length - 1].needTime;
		for (int job = works.length - 2; job >= 0; job--) {
			currentTime = Math.min(currentTime, works[job].endTime) - works[job].needTime;
		}

		return (currentTime < 0) ? -1 : currentTime;
	}
}
