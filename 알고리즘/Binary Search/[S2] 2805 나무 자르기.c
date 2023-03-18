#include <stdio.h>
#include <stdlib.h>

int upperBound(int num[], int n, int m){
	int front = 1;
	int lear = num[n-1];
	long long mid = (front + lear) / 2;
	long long sum;
	
	while (front <= lear){
		sum = 0;
		for(int i = 0; i < n; i++){
			if (num[i] - mid >= 0){
				sum += (num[i] - mid);
			}
		}
		if (sum >= m){
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
	
	int n, m, i;
	scanf("%d %d", &n, &m);
	int *num = (int*)malloc(sizeof(int)*n);
	for(i = 0; i < n; i++){
		scanf("%d", &num[i]);		
	}
	qsort(num, n, sizeof(int), mycompare);
	printf("%d", upperBound(num, n, m));
	
	free(num);
	return 0;
}
