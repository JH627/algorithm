#include <iostream>
#include <algorithm>

#define MAX 10004

using namespace std;

int dp[MAX]{};
int cost[MAX]{};

int main(void) {
	ios_base::sync_with_stdio(false);
	cout.tie(NULL);
	cin.tie(NULL);
	int n, m;
	cin >> n;
	
	for (int i = 3; i < n + 3; i++) {
		cin >> cost[i];
	}

	for (int i = 3; i < n + 3; i++) {
		dp[i] = max(dp[i-3] + cost[i-1] + cost[i], dp[i-2] + cost[i]);
		dp[i] = max(dp[i-1], dp[i]);
	}
	
	cout << dp[n + 2];
	return 0;
}
