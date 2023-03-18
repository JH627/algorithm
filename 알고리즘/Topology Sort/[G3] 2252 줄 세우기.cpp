#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <queue>
#include <vector>

using namespace std;

vector<int> v[32001];
int ind[32001] = {0, };

queue<int> visit;

int main(void) {
	int n, m;
	int a, b;
	scanf("%d %d", &n, &m);
	for (int i = 0; i < m; i++) {
		scanf("%d %d", &a, &b);
		v[a].push_back(b);
		ind[b]++;
	}

	for (int i = 1; i <= n; i++) {
		if (ind[i] == 0) {
			visit.push(i);
		}
	}

	int now, next;
	while (!visit.empty()) {
		now = visit.front();
		visit.pop();
		printf("%d ", now);
		for (int i = 0; i < v[now].size(); i++) {
			next = v[now][i];
			ind[next]--;
			if (ind[next] == 0) {
				visit.push(next);
			}
		}
	}
	return 0;
}