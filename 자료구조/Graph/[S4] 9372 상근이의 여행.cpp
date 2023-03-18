#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>

using namespace std;

vector<int> v[1001];
int post[1001] = {0,};
int clock = 0;

void cleanVector(int n) {
	for (int i = 1; i <= n; i++) {
		v[i].clear();
		post[i] = -1;
	}
	clock = 0;
}

void dfs(int s){
	post[s] = clock++;
	for (int i = 0; i < v[s].size(); i++) {
		if (post[v[s][i]] == -1) {
			dfs(v[s][i]);
		}
	}
}

int main(void) {
	int t, n, m;
	int a, b;
	scanf("%d", &t);

	for (int i = 0; i < t; i++) {
		scanf("%d %d", &n, &m);
		cleanVector(n);
		for (int j = 0; j < m; j++) {
			scanf("%d %d", &a, &b);
			v[a].push_back(b);
			v[b].push_back(a);
		}

		for (int k = 1; k <= n; k++) {
			if (post[k] == -1) {
				dfs(k);
			}
		}

		printf("%d\n", clock-1);
	}
		return 0;
}