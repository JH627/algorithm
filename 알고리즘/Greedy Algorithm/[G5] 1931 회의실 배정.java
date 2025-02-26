import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 회의들을 입력받아
 * 2. 종료 시간이 빠른 순으로 정렬한다 만약 종료 시간이 같다면 시작시간이 빠른 순으로 정렬한다
 * 3. 요소를 하나씩 가져오며 현재 진행하고 있는 회의(리스트의 가장 상단에 있는 회의)의 종료시간보다,
 *    가져온 회의의 시작 시간이 같거나 늦는 경우 리스트에 추가한다. 
 * 4. 최종적으로 리스트에 남아있는 요소의 개수가 최대로 진행할 수 있는 회의의 개수
 * 
 */
public class BOJ_1931_회의실배정 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Meeting implements Comparable<Meeting>{
		int start, end; // 회의 시작, 종료 시간

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		// 회의 시작, 종료 시간이 같은 경우도 있을 경우 : 종료시간이 빠른 순, 같다면 시작시간이 빠른 순
		// (1, 2), (2, 3), (3, 3) : 3개  => 답
		// (1, 2), (3, 3), (2, 3) : 2개
		@Override
		public int compareTo(Meeting o) {
			return this.end != o.end ? this.end - o.end : this.start - o.start;
		}
	}
	
	static int elementCount;
	static Meeting[] meetings; // 회의들의 정보
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(getMaxMeetingSize());
	}
	
	static int getMaxMeetingSize() {
		List<Meeting> result = new ArrayList<>();
		// 맨 처음 회의를 넣는다  => 정렬로 인해 종료시간이 제일 빠른 회의
		result.add(meetings[0]);
		
		// 요소를 하나씩 확인하며
		for (int meetingIndex = 1; meetingIndex < elementCount; meetingIndex++) {
			// 가장 마지막에 넣은 요소의 종료 시간보다 확인한 요소의 시작시간이 늦거나 같은 경우 리스트에 추가
			if (result.get(result.size() - 1).end <= meetings[meetingIndex].start) {
				result.add(meetings[meetingIndex]);
			}
		}
		
		// 최종적으로 리스트에 남은 회의의 개수가 최대로 많이 진행할 수 있는 회의의 개수
		return result.size();
	}
	
	
	static void init() throws Exception {
		elementCount = Integer.parseInt(br.readLine());
		meetings = new Meeting[elementCount];
		
		for (int index = 0; index < elementCount; index++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			meetings[index] = new Meeting(startTime, endTime);
		}
		
		// 정렬
		Arrays.sort(meetings);
	}
	
}
