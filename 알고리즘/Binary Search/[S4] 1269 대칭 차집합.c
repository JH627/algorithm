#include <stdio.h>
#include <stdlib.h>

int binsearch(int num[], int n, int k){
	int front = 0;
	int rear = n-1;
	int mid = (front+rear) / 2;
	while (front <= rear){
		if (num[mid] > k){
			rear = mid - 1;
		}
		else if (num[mid] < k){
			front = mid + 1;
		}
		else{
			return 1;
		}
		mid = (front + rear) / 2;
	}
	return 0;
}

int mycompare(const void *a, const void *b){
	return *(int*)a - *(int*)b;
}

int main(void){
	int a[200000], b[200000];
	int x, y, i;
	int count = 0;
	scanf("%d %d", &x, &y);
	for(i = 0; i < x; i++){
		scanf("%d", &a[i]);
	}
	for(i = 0; i < y; i++){
		scanf("%d", &b[i]);
	}
	qsort(a, x, sizeof(int), mycompare);
	qsort(b, y, sizeof(int), mycompare);
	for(i = 0; i < y; i++){
		count += binsearch(a, x, b[i]);
	}
	printf("%d", x + y - 2*count);
	return 0;
}
