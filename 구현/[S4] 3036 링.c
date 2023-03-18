#include <stdio.h>
#include <stdlib.h>

int gcd(int a, int b){
	return (b == 0) ? a : gcd(b, a%b);
}

int main(void){
	int n;
	scanf("%d", &n);
	int* ring = (int*)malloc(sizeof(int)*n);
	scanf("%d", &ring[0]);
	int gcdNum = ring[0];
    
	for(int i = 1; i < n; i++){
		scanf("%d", &ring[i]);
		printf("%d/%d\n", gcdNum / gcd(gcdNum, ring[i]), ring[i] / gcd(gcdNum, ring[i]));
	}
	free(ring);	
	return 0;
}
