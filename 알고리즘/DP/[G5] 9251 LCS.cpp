#include <iostream>
#include <string>
#define MAX 1001

using namespace std;

int dp[MAX][MAX]{};
string a, b;
int m;

int main(void) {
	cin >> a >> b;

	int alen, blen;
	alen = a.length();
	blen = b.length();

	for (int i = 0; i < alen; i++) {
		for (int j = 0; j < blen; j++) {
			if (a[i] == b[j]) {
				dp[i + 1][j + 1] = dp[i][j] + 1;
			}
			else {
				dp[i + 1][j + 1] = (dp[i + 1][j] > dp[i][j + 1]) ? dp[i + 1][j] : dp[i][j + 1];
			}
		}
	}

	printf("%d", dp[alen][blen]);

	return 0;
}