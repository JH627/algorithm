import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 접수 창고
 * - 고객번호가 낮은 순으로 우선 접수 -> 그냥 오는대로 
 * - 빈 창구가 여러 곳 인경우 접수 창구번호가 작은 곳으로 가자
 * 
 * 정비 창구
 * - 먼저 기다리는 고객이 우선
 * - 동시에 나왔다면 이용했던 접수 창구번호가 작은 고객이 우선
 * - 빈 창구가 여러곳인 경우 정비 창구 번호가 작은 곳으로
 * 
 * 1. 도착 순으로 접수 창구를 이용한다.
 * 
 * 1-1. 만약 이용 가능한 접수 창구가 없는 경우
 * 1-1-1. 가장 빨리 끝나는 접수 창구를 사용
 * 1-1-2. 해당 창구의 처리 종료 시간을 (기존에 끝나는 시간 + 처리시간)으로 갱신
 * 1-1-3. 사용자는 창구 정보와 접수 종료 시간 정보를 가지고 정비 창구에서 대기
 * 
 * 1-2. 만약 이용 가능한 접수 창구가 있는 경우
 * 1-2-1. 해당창구의 처리 종료 시간을 (현재시간 + 처리시간)으로 갱신
 * 1-2-2. 사용자는 창구 정보와 접수 종료 시간 정보를 가지고 정비 창구에서 대기
 * 
 * 2. 정비 창구 줄을 정렬한다.
 * 2-1. 접수가 동시에 끝났다면 접수 창구 번호가 작은 순
 * 2-2. 접수가 다른시간에 끝났다면 시간이 빠른 순
 * 
 * 3. 정렬 후 정비 창구를 이용한다.
 * 1-1. 만약 이용 가능한 정비 창구가 없는 경우
 * 1-1-1. 가장 빨리 끝나는 정비 창구를 사용
 * 1-1-2. 해당 창구의 처리 종료 시간을 (기존에 끝나는 시간 + 처리시간)으로 갱신
 * 1-1-3. 사용자의 접수 창구 번호와 정비 창구 번호를 확인해 정답에 반영
 * 
 * 1-2. 만약 이용 가능한 접수 창구가 있는 경우
 * 1-2-1. 해당창구의 처리 종료 시간을 (현재시간 + 처리시간)으로 갱신
 * 1-2-2. 사용자의 접수 창구 번호와 정비 창구 번호를 확인해 정답에 반영
 * 
 */
public class SWEA_2477_차량정비소 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class User implements Comparable<User> {
		int id; // 도착 정보
		int arrivedTime; // 도착 시간 / 접수 끝난 시간
		int usedDeskNumber; // 사용했던 창구 번호 (정비 창구는 정비창구 번호 * 20) 
		
		public User(int id, int arrivedTime, int usedDeskNumber) {
			this.id = id;
			this.arrivedTime = arrivedTime;
			this.usedDeskNumber = usedDeskNumber;
		}
		
		@Override
		public int compareTo(User o) {
			if (this.arrivedTime == o.arrivedTime) {
				return this.usedDeskNumber - o.usedDeskNumber;
			}
			return this.arrivedTime - o.arrivedTime;
		}
	}
	
	static int receptionDeskCount, repairDeskCount, userCount, usedReceptionDesk, usedRepairDesk;
	static ArrayList<User> receptionWaiting, repairWaiting;
	static int[] receptionTime, repairTime;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			useReceptionDesk();
			Collections.sort(repairWaiting);	
			useRepairDesk();
			sb.append("#").append(testCase).append(" ").append(answer == 0 ? -1 : answer).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void useReceptionDesk() {
		int[] endTime = new int[receptionDeskCount];
		
		for (User user : receptionWaiting) {
			int upperTime = Integer.MAX_VALUE; // 도착 시간 보다 큰것 중에 가장 작은 것
			int upperDeskIndex = 0;
			boolean resolved = false;
			for (int index = 0; index < receptionDeskCount; index++) {
				// 그냥 이용할 수 있는 경우
				if (endTime[index] <= user.arrivedTime) {
					endTime[index] = user.arrivedTime + receptionTime[index];
					repairWaiting.add(new User(user.id, endTime[index], index));
					resolved = true;
					break;
				}
				// 기다렸다가 이용할 수 있는 경우
				else {
					if (upperTime > endTime[index]) {
						upperTime = endTime[index];
						upperDeskIndex = index;
					}
				}
			}
			if (!resolved) {
				endTime[upperDeskIndex] += receptionTime[upperDeskIndex];
				repairWaiting.add(new User(user.id, endTime[upperDeskIndex], upperDeskIndex));
			}
		}
	}
	
	static void useRepairDesk() {
		int[] endTime = new int[repairDeskCount];
		
		for (User user : repairWaiting) {
			int upperTime = Integer.MAX_VALUE;
			int upperDeskIndex = 0;
			boolean resolved = false;
			for (int index = 0; index < repairDeskCount; index++) {
				// 그냥 이용할 수 있는 경우
				if (endTime[index] <= user.arrivedTime) {
					endTime[index] = user.arrivedTime + repairTime[index];
					resolved = true;
					if (user.usedDeskNumber == usedReceptionDesk && index == usedRepairDesk) {
						answer += user.id;
					}
					break;
				}
				// 기다렸다가 이용할 수 있는 경우
				else {
					if (upperTime > endTime[index]) {
						upperTime = endTime[index];
						upperDeskIndex = index;
					}
				}
			}
			if (!resolved) {
				endTime[upperDeskIndex] += repairTime[upperDeskIndex];
				if (user.usedDeskNumber == usedReceptionDesk && upperDeskIndex == usedRepairDesk) {
					answer += user.id;
				}
			}
		}
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		receptionDeskCount = Integer.parseInt(st.nextToken());
		repairDeskCount = Integer.parseInt(st.nextToken());
		userCount = Integer.parseInt(st.nextToken());
		usedReceptionDesk = Integer.parseInt(st.nextToken()) - 1;
		usedRepairDesk = Integer.parseInt(st.nextToken()) - 1;
		
		receptionTime = new int[receptionDeskCount];
		repairTime = new int[repairDeskCount];
		
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < receptionDeskCount; index++) {
			receptionTime[index] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < repairDeskCount; index++) {
			repairTime[index] = Integer.parseInt(st.nextToken());
		}
		
		receptionWaiting = new ArrayList<>();
		repairWaiting = new ArrayList<>();
		
		int id = 0;
		st = new StringTokenizer(br.readLine());
		for (int user = 0; user < userCount; user++) {
			int time = Integer.parseInt(st.nextToken());
			receptionWaiting.add(new User(++id, time, 0));
		}
		
		answer = 0;
	}
}
