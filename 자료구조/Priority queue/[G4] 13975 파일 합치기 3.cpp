#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <queue>

using namespace std;

priority_queue<long long, vector <long long> , greater<long long>> q;

int main(void) {
	int t, k;
	long long sum, tem, num;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d", &k);
		sum = 0;
		for (int j = 0; j < k; j++) {
			scanf("%lld", &num);
			q.push(num);
		}
		while (q.size() >= 2) {
			tem = q.top();
			q.pop();
			tem += q.top();
			q.pop();
			sum += tem;
			q.push(tem);
		}
		printf("%lld\n", sum);
		q.pop();
	}
	return 0;
}