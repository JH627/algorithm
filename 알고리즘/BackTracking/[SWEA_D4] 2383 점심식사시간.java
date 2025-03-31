import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 계단이 2개, 사람은 10명이하
 * => 계단으로 사람들을 미리 분배하고 시간을 계산
 * => 사람이 적으므로 가능한 모든 조합 살펴보기 가능
 * 
 * 1. 입력을 받는다.
 * 1-1. 사람과 계단의 위치를 구별해서 받는다.
 * 1-1-1. 각각의 위치를 배열에 저장해놓는다.
 * 
 * 2. 사람과 계단의 거리를 미리 dist 배열에 계산해놓는다.
 * 2-1. 거리 = Math.abs(human.row - stair.row) + Math.abs(human.col - stair.col);
 * 
 * 3. 사람들을 두 계단으로 나누어 본다.
 * 3-1. 경우의 수가 결정이 되면 해당 경우의 수를 기반으로 계단으로 모두 내려가는 시간을 계산
 * 3-1-1. 최소 시간 갱신
 * 
 */
public class SWEA_2383_점심식사시간 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int STAIR_MAX = 2;
	static final int INF = Integer.MAX_VALUE;
	
	static class Position {
		int row, col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static class WaitStatus implements Comparable<WaitStatus> {
		int time, status; // status: 0 => 나감, 1 => 들어가고 싶음

		public WaitStatus(int time, int status) {
			this.time = time;
			this.status = status;
		}

		@Override
		public int compareTo(WaitStatus o) {
			if (this.time != o.time) {
				return this.time - o.time;
			}
			
			return this.status - o.status;
		}
	}
	
	static int mapSize;
	
	static ArrayList<Position> humanPositions, stairPositions;
	static int humanSize, stairSize;
	static int[] downTime; // 내려가는 데 걸리는 시간
	static int[][] distance; // 사람별 계단으로 가는데 걸리는 시간
	static boolean[] selectStair; // 해당 인덱스의 사람이 선택한 계단
	
	static int minTime;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			setDistance();
			
			selectStair = new boolean[humanSize];
			getSelectedStair(0);
			
			sb.append("#").append(testCase).append(" ").append(minTime + 1).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void setDistance() {
		distance = new int[humanSize][stairSize];
		
		for (int humanIndex = 0; humanIndex < humanSize; humanIndex++) {
			Position human = humanPositions.get(humanIndex);
			for (int stairIndex = 0; stairIndex < stairSize; stairIndex++) {
				Position stair = stairPositions.get(stairIndex);
				distance[humanIndex][stairIndex] = Math.abs(human.row - stair.row) + Math.abs(human.col - stair.col);
			}
		}
	}
	
	// 가능한 조합을 구해보자
	static void getSelectedStair(int humanIndex) {
		// 조합을 구했다면 시간 체크
		if (humanIndex == humanSize) {
			minTime = Math.min(minTime, checkDistanceBySelectedStair());
			return;
		}

		// 0번 계단 선택
		selectStair[humanIndex] = true;
		getSelectedStair(humanIndex + 1);
		
		// 1번 계단 선택
		selectStair[humanIndex] = false;
		getSelectedStair(humanIndex + 1);
	}
	
	static int checkDistanceBySelectedStair() {
		// 0번 계단을 내려가는 시간 체크 (selectStair[index] = true)
		int timeA = simulation(0);
		// 1번 계단을 내려가는 시간 체크 (selectStair[index] = false)
		int timeB = simulation(1);
		
		return Math.max(timeA, timeB);
	}
	
	static int simulation(int stairNumber) {
		PriorityQueue<WaitStatus> times = new PriorityQueue<>();
		// N번 계단을 내려가는 시간 체크 (selectStair[index] = true)
		for (int humanIndex = 0; humanIndex < humanSize; humanIndex++) {
			if (stairNumber == 0 ? selectStair[humanIndex] : !selectStair[humanIndex]) {
				int distanceToStair = distance[humanIndex][stairNumber];
				// 계단으로 가는 시간이 애초에 최소 시간 넘으면 체크할 필요 없음
				if (distanceToStair >= minTime) {
					return INF;
				}
				times.add(new WaitStatus(distance[humanIndex][stairNumber], 1));
			}
		}
		
		int currentWaitingManCount = 0;
		int time = 0;
		while (!times.isEmpty()) {
			WaitStatus now = times.poll();
			// 검사 도중 최소 시간 넘으면 검사 필요 X
			if (now.time >= minTime) {
				return INF;
			}
			time = now.time;
			// 나오는 애라면
			if (now.status == 0) {
				// 하나 감소
				currentWaitingManCount--;
			}
			// 들어가고 싶어하는 애라면
			else {
				// 근데 자리가 없음
				if (currentWaitingManCount == 3) {
					// 다음을 기다려
					times.add(new WaitStatus(now.time + 1, 1));
				}
				else {
					// 계단에 들어가고
					currentWaitingManCount++;
					// 나올 시간 등록
					times.add(new WaitStatus(now.time + downTime[stairNumber], 0));
				}
			}
		}
		
		return time;
	}
	
	static void init() throws Exception {
		mapSize = Integer.parseInt(br.readLine());
		
		humanPositions = new ArrayList<>();
		stairPositions = new ArrayList<>();
		downTime = new int[STAIR_MAX];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				int value = Integer.parseInt(st.nextToken());
				// 사람인 경우
				if (value == 1) {
					humanPositions.add(new Position(row, col));
				}
				// 계단인 경우
				else if (value >= 2) {
					downTime[stairPositions.size()] = value;
					stairPositions.add(new Position(row, col));
				}
			}
		}
		
		humanSize = humanPositions.size();
		stairSize = stairPositions.size();
		
		minTime = Integer.MAX_VALUE;
	}
}
