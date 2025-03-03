import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * lazy propagation + segment Tree
 * 
 * A. 트리 생성
 * 1. segment Tree의 공간은 4 * N으로 할당해보자 (https://www.acmicpc.net/board/view/79441)
 *    - 세그먼트 트리에 필요한 노드 수는 2 * N (정확히는 2 * N - 1)
 *    	=> 세그먼트 트리는 주어진 배열의 크기 N에 대해, 리프 노드 수가 N개 필요
 *         리프 노드들을 결합하여 상위 노드를 생성하기 때문에, 필요한 전체 노드 수는 2N - 1개
 *    	   구현상의 편의를 위해 0번 인덱스를 비워두기 때문에 2 * N개
 *    - 그렇다면 왜 4 * N개나 할당할까
 *      => 어려운 문제에서는 트리 내에서 이진 탐색을 수행해야하는 경우가 있음
 *         이를 위해서는 항상 왼쪽 자식이 왼쪽 구간을 담당해야한다
 *         이를 보장하기 위해서는 완전 이진 트리 형태를 유지해야하며, 이를 위해 리프 노드의 개수를 가장 가까운 2^k 꼴로 올림해야함
 *         즉, N을 초과하는 가장 작은 2^k를 리프 노드 개수로 설정해야하며, 이 경우 노드의 개수는 2 * 2^k가 됨
 *         가장 가까운 2^k를 구하는 작업이 번거로울 수 있으므로, 상한을 그냥 2 * N으로 설정 (N <= 2^k < 2N 을 이용해보자)
 *         그렇다면 리프노드의 개수가 2 * N 이 되는데 1번 질의에 따라 필요한 노드의 수는 2 * (2 * N)개가 됨 => 즉 4 * N
 *
 * 2. lazy 값을 저장할 공간 또한 4 * N으로 설정해 보자 (세그먼트 트리의 노드와 1대1 매칭)
 *
 * 3. makeSegTree(int start, int end, int node) => start: 현재 구간의 시작 인덱스 / end: 현재 구간의 끝 인덱스 / node: 세그먼트 트리의 현재 노드 인덱스
 * 3-1. 기저조건 (start == end) => 원하는 구간 (리프노드) 에 도달한 경우
 *      리프노드에 현재 element[start]의 값을 할당
 * 3-2. 만약 구간의 길이가 2 이상인 경우 (mid = (start + end) / 2)
 * 3-2-1. 왼쪽 자식 노드(node * 2)로 분기 => 왼쪽 구간을 가져가자 (start, mid)
 * 3-2-2. 오른쪽 자식 노드(node * 2 + 1)로 분기 => 오른쪽 구간을 가져가자 (mid + 1, end)
 * 3-3. 분기를 끝내고 돌아오면 현재 노드(분기 했으므로 구간노드)의 값을 자식 값을 기준으로 갱신 위 문제에서는 구간 합이므로 segTree[node * 2] + segTree[node * 2 + 1]
 *
 * ==========================================================
 *
 * B. 구간 업데이트 쿼리
 * 1. 값을 가지고 한칸씩 내려가보자
 * 2. updateRangeQuery(int start, int end, int node, int left, int right, long delta)
 *    => start: 현재 구간의 시작 인덱스 / end: 현재 구간의 끝 인덱스 / node: 세그먼트 트리의 현재 노드 인덱스
 *       left: 업데이트를 적용하려는 범위의 시작 인덱스 / right: 업데이트를 적용하려는 범위의 끝 인덱스 / delta: 업데이트 할 값
 *
 * 3. 현재 노드에 대해 lazy값을 반영 (C 호출) => lazy[node]가 0임을 보장
 *
 * (기저 조건)
 * 4. 갱신 하려는 범위에 현재 트리의 범위가 포함이 되지 않는 경우 업데이트 조기 종료
 *    (left > end || right < start)
 *
 * 5. 갱신 하려는 범위가 현재 트리의 범위에 완전히 포함이 된 경우
 *    (left <= start && end <= right)
 * 5-1. 현재 세그먼트 트리 노드의 값을 갱신 (세그먼트 트리의 노드 값을 갱신할때만 delta 값을 연산하자)
 *      => (아래에 갱신해야하는 노드의 개수) * 변경값 => (end - start + 1) * delta
 * 5-2-1. 리프 노드인 경우 (start == end) 구간 업데이트 종료
 * 5-2-2. 리프 노드가 아닌 경우 (start != end)
 *         => 자식 노드에 lazy 값에 delta 값을 반영하고 구간 업데이트 종료
 *            lazy[node * 2] += delta;
 * 			  lazy[node * 2 + 1] += delta;
 *
 * 6. 만약 갱신하려는 범위가 현재 트리의 범위에 걸쳐지는 경우
 * 6-1. 왼쪽 자식 노드(node * 2)로 분기 => 왼쪽 구간을 가져가자 (start, mid)
 * 6-2. 오른쪽 자식 노드(node * 2 + 1)로 분기 => 오른쪽 구간을 가져가자 (mid + 1, end)
 * 6-3. 분기를 끝내고 돌아오면 현재 노드(분기 했으므로 구간노드)의 값을 자식 값을 기준으로 갱신 위 문제에서는 구간 합이므로 segTree[node * 2] + segTree[node * 2 + 1]
 *
 * ===========================================================
 *
 * C. lazy 값 반영 함수
 *
 * updateLazyRangeQuery(int start, int end, int node)
 * (기저 조건)
 * 1. 만약 현재 노드에 lazy하게 반영할 값이 없다면 (lazy[node] == 0) 갱신 종료
 *
 * 2. 현재 노드에 lazy하게 반영할 값이 있다면
 *    => 세그먼트 트리의 노드 값을 (아래에 갱신해야하는 노드의 개수) * 현재 노드에 저장된 lazy => (end - start + 1) * lazy[node]
 *
 * 3. 만약 리프 노드라면
 * 3-1. lazy[node] = 0;으로 하고 종료
 *
 * 4. 만약 리프 노드가 아니라면 (구간 노드라면)
 * 4-1. 자식노드에 lazy값을 전달하고 현재 노드의 lazy값을 초기화
 *      => lazy[node * 2] += lazy[node];
 * 		   lazy[node * 2 + 1] += lazy[node];
 * 		   lazy[node] = 0;
 * 
 * D. 구간 합 구하기 쿼리
 * getQuery(int start, int end, int node, int left, int right)
 *
 * 1. 현재 노드에 대해서 lazy 값 처리
 *
 * (기저 조건)
 * 2. 만약 탐색할 범위가 현재 트리 범위에 아예 포함이 안되는 경우 0 반환하고 조기 종료
 *    (end < left || right < start)
 * 3. 만약 탐색할 범위가 현재 트리 범위에 완전히 포함되는 경우 현재 segTree[node]의 값을 반환
 *    (left <= start && end <= right)
 *
 * 4. 탐색할 범위가 트리 범위에 걸쳐있는 경우
 * 4-1. 왼쪽 자식 노드(node * 2)로 분기 => 왼쪽 구간을 가져가자 (start, mid)
 * 4-2. 오른쪽 자식 노드(node * 2 + 1)로 분기 => 오른쪽 구간을 가져가자 (mid + 1, end)
 *
 * 5. 왼쪽구간에서 얻은 query값과 오른쪽에서 얻은 query 값을 합쳐서 반환
 *    getQuery(start, mid, node * 2, left, right) + getQuery(mid + 1, end, node * 2 + 1, left, right);
 *
 */
public class BOJ_10999_구간합구하기2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int elementCount, updateQueryCount, getQueryCount;
	static long[] segTree, lazy, element;

	public static void main(String[] args) throws IOException {
		init();

		makeSegTree(1, elementCount, 1);

		StringTokenizer st;
		int startIndex, endIndex;
		long updateValue;
		for (int queryIndex = 0; queryIndex < updateQueryCount + getQueryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int operation = Integer.parseInt(st.nextToken());

			switch (operation) {
				case 1:
					startIndex = Integer.parseInt(st.nextToken());
					endIndex = Integer.parseInt(st.nextToken());
					updateValue = Long.parseLong(st.nextToken());
					updateRangeQuery(1, elementCount, 1, startIndex, endIndex, updateValue);
					break;
				case 2:
					startIndex = Integer.parseInt(st.nextToken());
					endIndex = Integer.parseInt(st.nextToken());
					sb.append(getQuery(1, elementCount, 1, startIndex, endIndex)).append("\n");
					break;
			}
		}

		System.out.print(sb);
	}

	static void makeSegTree(int start, int end, int node) {
		if (start == end) {
			segTree[node] = element[start];
			return;
		}

		int mid = (start + end) / 2;
		makeSegTree(start, mid, node * 2);
		makeSegTree(mid + 1, end, node * 2 + 1);
		segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
	}

	static void updateLazyRangeQuery(int start, int end, int node) {
		// 만약 lazy하게 업데이트 할 값이 없다면 lazyUpdate를 멈춤
		if (lazy[node] == 0) {
			return;
		}

		// 업데이트 할 값이 있는 경우
		// 애초에 범위가 맞아서 lazy값이 남은 상태 자식 노드의 개수만큼 lazy 값 업데이트
		// 갱신
		segTree[node] += (end - start + 1) * lazy[node];

		// 리프노드라면 lazy값 갱신 후 종료
		if (start == end) {
			lazy[node] = 0;
			return;
		}

		// 리프 노드가 아니라면 lazy값을 나누어서 분배
		lazy[node * 2] += lazy[node];
		lazy[node * 2 + 1] += lazy[node];

		// 현재 노드 lazy 값 초기화
		lazy[node] = 0;
	}

	static void updateRangeQuery(int start, int end, int node, int left, int right, long delta) {

		updateLazyRangeQuery(start, end, node);

		// 갱신하려는 범위에 현재 트리의 범위가 아예 포함이 안된 경우
		if (start > right || end < left) {
			return;
		}

		// 범위가 완전히 포함된 경우 현재 세그먼트 트리 노드의 값을 갱신하고
		// 구간 노드(리프 노드X) 인 경우 자식 노드에 lazy값을 남기고 탐색 종료
		if (left <= start && end <= right) {
			segTree[node] += (long)(end - start + 1) * delta;
			if (start != end) {
				lazy[node * 2] += delta;
				lazy[node * 2 + 1] += delta;
			}
			return;
		}

		// 걸치는 범위
		// 아래 구간을 업데이트 하고
		// 아래 구간 값을 바탕으로 현재 구간 노드의 값도 갱신 후 종료
		int mid = (start + end) / 2;
		updateRangeQuery(start, mid, node * 2, left, right, delta);
		updateRangeQuery(mid + 1, end, node * 2 + 1, left, right, delta);
		segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
	}

	static long getQuery(int start, int end, int node, int left, int right) {
		// 현재 노드에 대해서 lazy 값을 갱신
		updateLazyRangeQuery(start, end, node);

		// 범위에 아예 포함이 안되는 경우
		if (left > end || right < start) {
			return 0;
		}

		// 범위에 포함이 되는 경우 
		if (left <= start && end <= right) {
			return segTree[node];
		}

		// 걸치는 경우
		int mid = (start + end) / 2;
		return getQuery(start, mid, node * 2, left, right) + getQuery(mid + 1, end, node * 2 + 1, left, right);
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		updateQueryCount = Integer.parseInt(st.nextToken());
		getQueryCount = Integer.parseInt(st.nextToken());

		segTree = new long[elementCount * 4];
		lazy = new long[elementCount * 4];
		element = new long[elementCount + 1];

		for (int elementIndex = 1; elementIndex <= elementCount; elementIndex++) {
			element[elementIndex] = Long.parseLong(br.readLine());
		}
	}
}
