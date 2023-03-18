#define _CRT_SECURE_NO_WARNINGS
#define MAX 100000

#include <cstdio>
#include <queue>

using namespace std;

int dist[MAX + 1];
int add[2] = { 1 , -1};

int main(void) {

	int n, k;
	scanf("%d %d", &n, &k);

	for (int i = 0; i <= MAX; i++) {
		dist[i] = MAX + 1;
	}

	queue<int> q;
	q.push(n);
	dist[n] = 0;

	int now, next;
	while (!q.empty()) {
		now = q.front();
		q.pop();

		if (now == k) {	
			break; 
		}
		
		for (int i = 0; i < 2; i++) {
			next = now + add[i];
			if (next <= MAX && next > -1) {
				if (dist[now] + 1 < dist[next]) {
					dist[next] = dist[now] + 1;
					q.push(next);
				}
			}
		}

		next = now * 2;
		if (next <= MAX) {
			if (dist[now] < dist[next]) {
				dist[next] = dist[now];
				q.push(next);
			}
		}
	}

	printf("%d", dist[k]);
	return 0;
}