#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>

using namespace std;

vector<int> v;

int main(void) {
	int n, s, k;
	int l, r, sum;
	scanf("%d %d", &n, &s);

	v.push_back(0);

	for (int i = 0; i < n; i++) {
		scanf("%d", &k);
		k += v[i];
		v.push_back(k);
	}

	int len = 100000001;
	l = r = 1;
	while (r <= n && l <= n) {
		sum = v[r] - v[l-1];
		if (sum >= s) {
			len = (r - l < len) ? r - l + 1 : len;
			l++;
		}
		else {
			(sum > s) ? l++ : r++;
		}
	}
	printf("%d", (len == 100000001) ? 0 : len);

	return 0;
}