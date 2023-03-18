// Merge_sort
#include <stdio.h>
int tem[1000000];

void merge(int num[], int left, int mid, int right);

void mergeSort(int num[], int left, int right){
	if (left < right){
		int mid = (left + right) / 2;
		mergeSort(num, left, mid);
		mergeSort(num, mid + 1, right);
		merge(num, left, mid, right);
	}
}

void merge(int num[], int left, int mid, int right){
	int i, j, k, l;
	
	i = left;
	j = mid + 1;
	k = left;
	
	while (i <= mid && j <= right){
		if (num[i] < num[j]){
			tem[k++] = num[i++];
		}
		else {
			tem[k++] = num[j++];	
		}
	}
	if (i > mid){
		for(l = j; l <= right; l++){
			tem[k++] = num[l];
		}
	}
	else {
		for(l = i; l <= mid; l++){
			tem[k++] = num[l];
		}
	}
	for(l = left; l <= right; l++){
		num[l] = tem[l];
	}
}

int main(void){
	
	int n;
	int num[1000000];
	
	scanf("%d", &n);
	
	for(int i = 0; i < n; i++){
		scanf("%d", &num[i]);		
	}
	
	mergeSort(num, 0, n-1);
	
	for(int i = 0; i < n; i++){
		printf("%d\n", num[i]);
	}
	return 0;
}
