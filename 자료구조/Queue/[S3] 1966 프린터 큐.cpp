#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <queue>

using namespace std;

priority_queue<int> pq;
queue<pair<int, int>> q;
int order[101]{};

int main(void) {
	int t, n, m, k;
	int clock = 0;;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d %d", &n, &m);
		clock = 0;
		for (int j = 0; j < n; j++) {
			scanf("%d", &k);
			pq.push(k);
			q.push(make_pair(k, clock++));
		}
		int max, f, s;
		int seq = 1;
		while (!q.empty()) {
			max = pq.top();
			f = q.front().first;
			s = q.front().second;
			if (f == max) {
				order[s] = seq++;
				pq.pop();
			}
			else {
				q.push(make_pair(f, s));
			}
			q.pop();
		}
		printf("%d\n", order[m]);
	}
	return 0;
}