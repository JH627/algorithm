package problemsss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. BC의 정보를 입력 받고 성능이 오름차순이 되도록 정렬한다
 * 2. 맨 앞 BC부터 확인하며 전류 공급이 가능한 곳을 표시한다. (비트에 표시 (1 << (정렬 후 BC의 인덱스))
 * 3. 유저를 이동시키며 각 시간에 얻을 수 있는 최대 충전량을 구한다.
 * 3-1. 사용자 A가 받을 수 있는 전기가 없는 경우
 *      => B가 받을수있다면 B의 가장 강한 충전 값 반환
 * 3-2. 사용자 B가 받을 수 있는 전기가 없는 경우
 *      => A가 받을수있다면 A의 가장 강한 충전 값 반환
 * 3-3. 최대로 얻을 수 있는 충전기가 다른 경우 각자 하나씩 선택
 * 3-4.	최대로 얻을 수 있는 충전기가 같은 경우
 *		=> 일단 나누는 경우든 혼자먹든 제일 쎈 파워는 가져감
 *	    => 두번째로 강한 파워를 하나 더 가져감 (하나라도 존재한다면)
 *
 */
public class SWEA_5644_무선충전 {
	
	static BufferedReader br;
	
	static final int MAP_SIZE = 10;
	// Stay, Up, Right, Down, Left
	static final int[] ADD_ROW = {0, -1, 0, 1, 0};
	static final int[] ADD_COL = {0, 0, 1, 0, -1};
	
	static class Bc implements Comparable<Bc> {
		int col, row;
		int range, power;
		
		public Bc(int col, int row, int range, int power) {
			this.col = col;
			this.row = row;
			this.range = range;
			this.power = power;
		}

		@Override
		public int compareTo(Bc o) {
			return this.power - o.power;
		}
	}
	
	static int timeLimit, bcCount; // timeLimit: 총 이동시간, bcCount: BC의 개수
	static int aRow, aCol, bRow, bCol; // A유저와 B유저의 위치
	static int[] aMoveQueries, bMoveQueries; // 각 유저의 이동 정보
	static int[][] nearBcStatus; // 각 위치에서 전류 공급이 가능한 BC 상태 (비트로 표시)
	static ArrayList<Bc> bcList; // BC들의 정보
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			// 전력 공급이 가능한 곳 표시
			for (int bcIndex = 0; bcIndex < bcCount; bcIndex++) {	
				checkNearStatus(bcIndex);
			}
			
			int sumPower = findMaxPower(); // 0초에도 전류를 얻을 수 있음
			for (int time = 0; time < timeLimit; time++) {
				// 유저 이동
				moveUser(time);
				// 현재 위치에서 전력을 가장 많이 받울 수 있는 경우의 수 구하기
				sumPower += findMaxPower();				
			}
			
			sb.append("#").append(testCase).append(" ").append(sumPower).append("\n");
		}
		
		System.out.print(sb);
	}
	
	// BFS로 주변 노드에 전기가 공급가능한지 표시
	static void checkNearStatus(int bcIndex) {
		Queue<int[]> toVisit = new LinkedList<>();
		Bc nowBc = bcList.get(bcIndex);
		// 현재 BC의 거리제한
		int rangeLimit = nowBc.range;
		
		boolean[][] visited = new boolean[MAP_SIZE][MAP_SIZE];
		visited[nowBc.row][nowBc.col] = true;
		
		toVisit.add(new int[] {nowBc.row, nowBc.col, 0});
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			int nowRow = now[0];
			int nowCol = now[1];
			int nowDist = now[2];
			
			// 방문한 노드에 현재 BC에서 전류가 공급이 가능함을 표시
			nearBcStatus[nowRow][nowCol] |= (1 << bcIndex);
			
			// 최대 거리까지 탐색한 경우 탐색 종료
			if (nowDist == rangeLimit) {
				continue;
			}
			
			// 주변에 방문안한 곳 탐색
			for (int index = 1; index < 5; index++) {
				int newRow = nowRow + ADD_ROW[index];
				int newCol = nowCol + ADD_COL[index];
				
				if (newRow < 0 || newRow >= MAP_SIZE || newCol < 0 || newCol >= MAP_SIZE) {
					continue;
				}
				
				if (visited[newRow][newCol]) {
					continue;
				}
				
				visited[newRow][newCol] = true;
				toVisit.add(new int[] {newRow, newCol, nowDist + 1});
			}
		}
	}
	
	// 움직이기
	static void moveUser(int time) {		
		int aDirection = aMoveQueries[time];
		int bDirection = bMoveQueries[time];
		
		aRow += ADD_ROW[aDirection];
		aCol += ADD_COL[aDirection];
		bRow += ADD_ROW[bDirection];
		bCol += ADD_COL[bDirection];
	}
	
	static int findMaxPower() {
		int aNearStatus = nearBcStatus[aRow][aCol]; // 현재  A가 받을 수 있는 BC종류 상태
		int bNearStatus = nearBcStatus[bRow][bCol]; // 현재  B가 받을 수 있는 BC종류 상태
		
		// 가장 왼쪽에 있는 비트 인덱스 (받을 수 있는 가장 센 파워)
		int aMaxIndex = 31 - Integer.numberOfLeadingZeros(aNearStatus);
		int bMaxIndex = 31 - Integer.numberOfLeadingZeros(bNearStatus);
		
		// 사용자 A가 받을 수 있는 전기가 없는 경우
		if (aMaxIndex == -1) {
			// B가 받을수있다면 B의 가장 강한 충전 값 반환
			return (bMaxIndex == -1) ? 0 : bcList.get(bMaxIndex).power;			
		}
		// 사용자 B가 받을 수 있는 전기가 없는 경우
		if (bMaxIndex == -1) {
			// A가 받을수있다면 A의 가장 강한 충전 값 반환
			return (aMaxIndex == -1) ? 0 : bcList.get(aMaxIndex).power;			
		}
		
		// 최대로 얻을 수 있는 충전기가 다른 경우 각자 하나씩 선택
		if (aMaxIndex != bMaxIndex) {
			return bcList.get(aMaxIndex).power + bcList.get(bMaxIndex).power;
		}
		
		// 같은 경우
		// 일단 나누는 경우든 혼자먹든 제일 쎈 파워는 가져감
		int sum = bcList.get(aMaxIndex).power;
		
		// 두번째로 강한 파워를 하나 더 가져감 (하나라도 존재한다면)
		// A와 B의 받을 수 있는 BC의 상태를 합친 후 가장 센 파워하나는 제외함
		int secondIndex = 31 - Integer.numberOfLeadingZeros((aNearStatus | bNearStatus) - (1 << aMaxIndex));
		
		return (secondIndex == -1) ? sum : sum + bcList.get(secondIndex).power;
	}
	
	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		timeLimit = Integer.parseInt(st.nextToken());
		bcCount = Integer.parseInt(st.nextToken());
		
		aMoveQueries = new int[timeLimit];
		bMoveQueries = new int[timeLimit];
		
		nearBcStatus = new int[MAP_SIZE][MAP_SIZE];
		
		st = new StringTokenizer(br.readLine());
		for (int queryIndex = 0; queryIndex < timeLimit; queryIndex++) {
			aMoveQueries[queryIndex] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int queryIndex = 0; queryIndex < timeLimit; queryIndex++) {
			bMoveQueries[queryIndex] = Integer.parseInt(st.nextToken());
		}
		
		bcList = new ArrayList<>();
		for (int bcIndex = 0; bcIndex < bcCount; bcIndex++) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken()) - 1;
			int row = Integer.parseInt(st.nextToken()) - 1;
			int range = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());
			
			bcList.add(new Bc(col, row, range, power));
		}
		
		// power 오름차순으로 정렬
		Collections.sort(bcList);
		
		// 시작 좌표 세팅
		aRow = 0; aCol = 0;
		bRow = MAP_SIZE - 1; bCol = MAP_SIZE - 1;
	}
}
