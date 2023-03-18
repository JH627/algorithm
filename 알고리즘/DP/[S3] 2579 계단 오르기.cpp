#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <algorithm>

using namespace std;

int score[301]{};
int dp[301]{};

int main(void) {
	int n;
	scanf("%d", &n);

	for (int i = 1; i <= n; i++) {
		scanf("%d", &score[i]);
	}

	dp[1] = score[1];
	dp[2] = dp[1] + score[2];
	for (int i = 3; i <= n; i++) {
		dp[i] = max(dp[i - 2] + score[i], dp[i - 3] + score[i - 1] + score[i]);
	}

	printf("%d", dp[n]);
	return 0;
}
