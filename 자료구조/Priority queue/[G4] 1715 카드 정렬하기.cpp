#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <queue>

using namespace std;

priority_queue<int> q;

int main(void) {
	int n;
	int sum = 0, tem, num;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &num);
		num *= -1;
		q.push(num);
	}

	while (q.size() >= 2) {
		tem = q.top();
		q.pop();
		tem += q.top();
		sum += tem;
		q.pop();
		q.push(tem);
	}
	printf("%d", sum * (-1));
	q.pop();
	return 0;
}