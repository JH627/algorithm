#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>

using namespace std;

vector <pair<int, int>> v;
int w[102][100002];

int main(void) {
	int n, k;
	int a, b;
	scanf("%d %d", &n, &k);

	v.push_back(make_pair(0, 0));
	for (int i = 0; i < n; i++) {
		scanf("%d %d", &a, &b);
		v.push_back(make_pair(a, b));
	}

	int wei, val;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= k; j++) {
			wei = v[i].first;
			val = v[i].second;
			if (wei <= j) {
				if (val + w[i - 1][j - wei] > w[i - 1][j]) {
					w[i][j] = val + w[i - 1][j - wei];
				}
				else {
					w[i][j] = w[i - 1][j];
				}
			}
			else {
					w[i][j] = w[i - 1][j];
			}
		}
	}
	printf("%d", w[n][k]);
	return 0;
}