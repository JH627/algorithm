import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1713_후보추천하기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int frameSize, recommendCount;
	static int[] recommendOrder;
	static List<Integer> frames;
	static Map<Integer, Integer> recommendMap;
	static Map<Integer, Integer> timeMap;

	public static void main(String[] args) throws IOException {
		init();
		processRecommend();
		printFinalCandidates();
	}

	static void processRecommend() {
		int time = 0;
		for (int i = 0; i < recommendCount; i++) {
			int student = recommendOrder[i];

			if (frames.contains(student)) {
				recommendMap.put(student, recommendMap.get(student) + 1);
			} else {
				if (frames.size() == frameSize) {
					int removeTarget = getRemoveTarget();
					frames.remove((Integer) removeTarget);
					recommendMap.remove(removeTarget);
					timeMap.remove(removeTarget);
				}
				frames.add(student);
				recommendMap.put(student, 1);
				timeMap.put(student, time);
			}
			time++;
		}
	}

	static int getRemoveTarget() {
		int minRecommend = Integer.MAX_VALUE;
		int oldestTime = Integer.MAX_VALUE;
		int removeTarget = -1;

		for (int student : frames) {
			int rec = recommendMap.get(student);
			int t = timeMap.get(student);
			if (rec < minRecommend || (rec == minRecommend && t < oldestTime)) {
				minRecommend = rec;
				oldestTime = t;
				removeTarget = student;
			}
		}
		return removeTarget;
	}

	static void printFinalCandidates() {
		Collections.sort(frames);
		for (int student : frames) {
			sb.append(student).append(" ");
		}
		System.out.print(sb);
	}


	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		frameSize = Integer.parseInt(br.readLine());
		recommendCount = Integer.parseInt(br.readLine());
		recommendOrder = new int[recommendCount];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < recommendCount; i++) {
			recommendOrder[i] = Integer.parseInt(st.nextToken());
		}

		frames = new ArrayList<>();
		recommendMap = new HashMap<>();
		timeMap = new HashMap<>();
	}
}
