#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>
#include <vector>

#define MAX 32001

using namespace std;

vector<int> v[MAX];
vector<int> ans;
priority_queue<int, vector<int>, greater<int>> q;
int ind[MAX]{};

int main(void) {
	int n, m;
	scanf("%d %d", &n, &m);

	int a, b;
	for (int i = 0; i < m; i++)	{
		scanf("%d %d", &a, &b);
		v[a].push_back(b);
		ind[b]++;
	}

	for (int i = 1; i <= n; i++) {
		if (ind[i] == 0) {
			q.push(i);
		}
	}

	int now, next;
	while (!q.empty()) {
		now = q.top();
		q.pop();
		ans.push_back(now);
		for (int i = 0; i < v[now].size(); i++) {
			next = v[now][i];
			ind[next]--;
			if (ind[next] == 0) {
				q.push(next);
			}
		}
	}

	for (int i = 0; i < ans.size(); i++) {
		printf("%d ", ans[i]);
	}

	return 0;
}
