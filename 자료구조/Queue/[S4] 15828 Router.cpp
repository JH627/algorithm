#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <queue>

using namespace std;

queue<int> q;

int main(void) {
	int n, num;
	scanf("%d", &n);

	while (1) {
		scanf("%d", &num);
		if (num == -1) {
			break;
		}
		if (num == 0) {
			q.pop();
		}
		else {
			if (q.size() < n) {
				q.push(num);
			}
		}
	}
	if (q.empty()) {
		printf("empty");
	}
	else {
		while (!q.empty()) {
			printf("%d ", q.front());
			q.pop();
		}
	}
	return 0;
}