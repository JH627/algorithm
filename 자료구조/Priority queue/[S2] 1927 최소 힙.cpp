#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <queue>

using namespace std;

priority_queue<int> q;

int main(void) {
	int n, x;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &x);
		if (x == 0) {
			if (q.size() == 0) {
				printf("0\n");
			}
			else {
				printf("%d\n", -q.top());
				q.pop();
			}
			continue;
		}
		q.push(-x);
	}
	return 0;
}