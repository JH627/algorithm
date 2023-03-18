#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

vector<int> v[100001];
int parent[100001]{};

void bfs(int now) {
	queue<int> q;
	q.push(now);
	int next;
	while (!q.empty()) {
		now = q.front();
		q.pop();
		for (int i = 0; i < v[now].size(); i++) {
			next = v[now][i];
			if (parent[next] != 0) {
				continue;
			}
			parent[next] = now;
			q.push(next);
			}
	}
}

int main(void) {

	int n, a, b;
	scanf("%d", &n);
	
	for (int i = 1; i < n; i++) {
		scanf("%d %d", &a, &b);
		v[a].push_back(b);
		v[b].push_back(a);
	}

	bfs(1);

	for (int i = 2; i <= n; i++) {
		printf("%d\n", parent[i]);
	}

	return 0;
}