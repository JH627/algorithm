#include <stdio.h>
#include <stdlib.h>

int search(int num[], int n, int k){
	int front = 0;
	int end = n;
	int mid = (front + end) / 2;
	while (front <= end){
		if (num[mid] > k){
			end = mid - 1;
		}
		else if (num[mid] < k){
			front = mid + 1;
		}
		else{
			return 1;
		}
		mid = (front + end) / 2;
	} 
	return 0;
}

int mycompare(const void *a, const void *b){
	return *(int*)a - *(int*)b;
}

int main(void){
	
	int n, m, k, i;
	scanf("%d", &n);
	int num[n];
	for(i = 0; i < n; i++){
		scanf("%d", &num[i]);
	}
	qsort(num, n, sizeof(int), mycompare);
	
	scanf("%d", &m);
	for(i = 0; i < m; i++){
		scanf("%d", &k);
		printf("%d ", search(num, n, k));
	}
	return 0;
}
