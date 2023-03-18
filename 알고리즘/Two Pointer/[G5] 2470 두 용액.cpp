#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> v;

int main(void) {
	int n, k, l, r;
	int min, sum;
	pair<int, int> p;

	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &k);
		v.push_back(k);
	}
	sort(v.begin(), v.end());

	l = 0;
	r = n - 1;
	min = abs(v[l] + v[r]);
	p = make_pair(v[l], v[r]);
	while (l < r) {
		sum = v[l] + v[r];
		if (abs(sum) < min) {
			min = abs(sum);
			p = make_pair(v[l], v[r]);
		}
		if (sum == 0) {
			break;
		}
		else {
			(sum < 0) ? l++ : r--;
		}
	}
	printf("%d %d", p.first, p.second);
	return 0;
}