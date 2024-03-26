#define _CRT_SECURE_NO_WARNINGS
#define INT_MAX 2147483647
#define MAX 501

#include <cstdio>
#include <algorithm>

using namespace std;

int dp[MAX][MAX]{};
int sum[MAX]{};

int main(void) {
    int t, k;
    scanf("%d", &t);
    for (int c = 0; c < t; c++) {
        scanf("%d", &k);
        for (int i = 1; i <= k; i++) {
            scanf("%d", &sum[i]);
            sum[i] += sum[i - 1];
        }

        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= k - i; j++) {
                dp[j][i + j] = INT_MAX;
                for (int p = j; p < i + j; p++) {
                    dp[j][i + j] = min(dp[j][i + j], dp[j][p] + dp[p + 1][i + j] + sum[i + j] - sum[j - 1]);
                }
            }
        }


        printf("%d\n", dp[1][k]);
    }
    return 0;
}