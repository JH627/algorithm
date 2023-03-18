#include <stdio.h>
#include <stdlib.h>

int lowerBound(int num[], int n, int k){
	int front = 0;
	int lear = n-1;
	int mid = (front + lear) / 2;
	
	while (front <= lear){
		if (num[mid] >= k){
			lear = mid - 1;
		}
		else {
			front = mid + 1;
		}
		mid = (front + lear) / 2;
	}
	return front;
}

int upperBound(int num[], int n, int k){
	int front = 0;
	int lear = n-1;
	int mid = (front + lear) / 2;
	
	while (front <= lear){
		if (num[mid] <= k){
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
	
	int n, m, k, i;
	scanf("%d", &n);
	int *num = (int*)malloc(sizeof(int)*n);
	for(i = 0; i < n; i++){
		scanf("%d", &num[i]);		
	}
	qsort(num, n, sizeof(int), mycompare);
	
	scanf("%d", &m);
	for(i = 0; i < m; i++){
		scanf("%d", &k);
		printf("%d ", upperBound(num, n, k) - lowerBound(num, n, k) + 1);
	}
	free(num);
	return 0;
}
