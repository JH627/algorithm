#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>

using namespace std;

int main(void) {
    int t, n;
    int fib[41];
    fib[0] = 0;
    fib[1] = fib[2] = 1;
    for (int i = 3; i < 41; i++) {
        fib[i] = fib[i - 1] + fib[i - 2];
    }

    scanf("%d", &t);
    for (int i = 0; i < t; i++) {
        scanf("%d", &n);
        if (n == 0) {
            printf("%d %d\n", fib[1], fib[0]);
        }
        else {
            printf("%d %d\n", fib[n - 1], fib[n]);
        }
    }
    return 0;
}