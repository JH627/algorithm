#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <cmath>
#include <queue>

using namespace std;

priority_queue<int> mq;
priority_queue<int, vector<int>, greater<int>> q;

int main(void) {
	int n, x;
	int a, b;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &x);
		if (x == 0) {
			a = (mq.size() != 0) ? abs(mq.top()) : 0;
			b = (q.size() != 0) ? abs(q.top()) : 0;
			if (a == 0) {
				printf("%d\n", (b != 0) ? b : 0);
				if (b != 0) q.pop();
			}
			else if (b == 0) {
				printf("%d\n", (a != 0) ? -a : 0);
				if (a != 0) mq.pop();
			}
			else {
				printf("%d\n", (a <= b) ? -a : b);
				(a <= b) ? mq.pop() : q.pop();
			}
		}
		else {
			(x < 0) ? mq.push(x) : q.push(x);
		}
	}
	return 0;
}