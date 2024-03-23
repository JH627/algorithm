#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> v;

int search(int key) {
	int front = 0;
	int end = v.size() - 1;
	int mid = (front + end) / 2;
	while (front <= end) {
		if (v[mid] > key) {
			end = mid - 1;
		}
		else if (v[mid] < key) {
			front = mid + 1;
		}
		else {
			return 1;
		}
		mid = (front + end) / 2;
	}
	return 0;
}

int main(void) {

	int n, m, k;

	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &k);
		v.push_back(k);
	}
	
	sort(v.begin(), v.end());

	scanf("%d", &m);
	for (int i = 0; i < m - 1; i++) {
		scanf("%d", &k);
		printf("%d ", search(k));
	}

	scanf("%d", &k);
	printf("%d", search(k));
	return 0;
}
