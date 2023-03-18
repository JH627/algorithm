#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>

using namespace std;

int tmp[500001]{};
int num[500001]{};

int n, k, ans;
int count = 0;

void merge(int l, int m, int r) {
	int i = l;	int j = m + 1;	int t = 1;
	while (i <= m && j <= r) {
		tmp[t++] = (num[i] <= num[j]) ? num[i++] : num[j++];
	}
	while (i <= m) {
		tmp[t++] = num[i++];
	}
	while (j <= r) {
		tmp[t++] = num[j++];
	}
	i = l; t = 1;
	while (i <= r) {
		num[i++] = tmp[t++];
		if (++count == k) {
			ans = tmp[t - 1];
		}
	}
}

void merge_sort(int l, int r) {
	if (l < r) {
		int m = (l + r) / 2;
		merge_sort(l, m);
		merge_sort(m + 1, r);
		merge(l, m, r);
	}
}

int main(void) {
	scanf("%d %d", &n, &k);

	for (int i = 0; i < n; i++) {
		scanf("%d", &num[i]);
	}

	merge_sort(0, n - 1);
	printf("%d", (count <= k) ? -1 : ans);
	return 0;
}