#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>

using namespace std;

int n, k;

int binSearch(int l, int r) {
    if (l > r) {
        return l;
    }
    int m = (l + r) / 2;
    int count = 0;
    for (int i = 1; i <= n; i++) {
        count += (n > m/i) ? m / i : n;
    }
    if (count < k) {
        return binSearch(m + 1, r);
    }
    else {
        return binSearch(l, m - 1);
    }
}

int main(void) {
    scanf("%d %d", &n, &k);
    printf("%d", binSearch(1, k));
    return 0;
}
