#define _CRT_SECURE_N0_WARNINGS

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> v;

int main(void) {
	int n, m, x;
	int count = 0;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &m);
		v.push_back(m);
	}
	sort(v.begin(), v.end());

	scanf("%d", &x);

	int l = 0;
	int r = v.size() - 1;
	while (l < r) {
		if (v[l] + v[r] == x) {
			count++;
			l++;
			r--;
		}
		else (v[l] + v[r] < x) ? l++ : r--;
	}
	printf("%d", count);
	return 0;
}