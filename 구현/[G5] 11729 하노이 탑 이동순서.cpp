#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <math.h>

using namespace std;

void hanoi(int n, int start, int middle, int end) {
    if (n==1) {
        printf("%d %d\n", start, end);
        return;
    }
    hanoi(n-1, start, end, middle);
    hanoi(1, start, middle, end);
    hanoi(n-1, middle, start, end);
}

int main(void) {
    int n;
    scanf("%d", &n);
    
    printf("%d\n", int(pow(2,n))-1);
    hanoi(n, 1, 2, 3);
    
    return 0;
}
