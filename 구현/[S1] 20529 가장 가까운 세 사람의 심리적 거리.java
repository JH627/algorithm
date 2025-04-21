import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20529_가장가까운세사람의심리적거리 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int elementCount;
	static String[] elements;
	static String[] selected;
	static int minDistance;
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			
			if (elementCount >= 48) {
				sb.append(0).append("\n");
				continue;
			}
			
			minDistance = Integer.MAX_VALUE;
			getMinDistance(0, 0, 0);
			sb.append(minDistance).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void getMinDistance(int elementIndex, int selectedIndex, int distSum) {
		if (minDistance == 0) {
			return;
		}
		
		if (selectedIndex == 3) {
			minDistance = Math.min(minDistance, distSum);
			return;
		}
		
		if (elementIndex == elementCount) {
			return;
		}
		
		for (int index = elementIndex; index < elementCount; index++) {
			int dist = 0;
			for (int prev = selectedIndex - 1; prev >= 0; prev--) {
				dist += getDistance(selected[prev], elements[index]);
			}
			
			if (distSum + dist < minDistance) {
				selected[selectedIndex] = elements[index];
				getMinDistance(index + 1, selectedIndex + 1, distSum + dist);
			}
		}
	}
	
	static void init() throws Exception {
		elementCount = Integer.parseInt(br.readLine());
		
		if (elementCount >= 48) {
			br.readLine();
			return;
		}
		
		elements = new String[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			elements[elementIndex] = st.nextToken();
		}
		
		selected = new String[3];
	}
	
	static int getDistance(String a, String b) {
		int dist = 0;
		for (int index = 0; index < 4; index++) {
			if (a.charAt(index) != b.charAt(index)) {
				dist++;
			}
		}
		return dist;
	}
}
