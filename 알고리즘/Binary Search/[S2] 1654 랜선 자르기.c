#include <stdio.h>
#include <stdlib.h>

long long upperBound(int num[], int k, int n){
	long long front = 1;
	long long lear = num[k-1];
	long long mid = (front + lear) / 2;
	int sum;
	
	while (front <= lear){
		sum = 0;
		for(int i = 0; i < k; i++){
			sum += num[i] / mid;
		}
		if (sum >= n){
			front = mid + 1;
		}
		else {
			lear = mid - 1;
		}
		mid = (front + lear) / 2;
	}
	return lear;
}

int mycompare(const void *a, const void *b){
	if (*(int*)a > *(int*)b){
		return 1;
	}
	else{
		return (*(int*)a < *(int*)b) ? -1 : 0;
	}
}

int main(void){
	
	int k, n, i;
	scanf("%d %d", &k, &n);
	int *num = (int*)malloc(sizeof(int)*k);
	for(i = 0; i < k; i++){
		scanf("%d", &num[i]);		
	}
	qsort(num, k, sizeof(int), mycompare);
	printf("%lld", upperBound(num, k, n));
	
	free(num);
	return 0;
}
