#include <stdio.h>

typedef struct{
	int n;
	char name[101];
} Judge;

Judge tem[100000];

void merge(Judge list[], int left, int mid, int right);

void mergeSort(Judge list[], int left, int right){
	if (left < right){
		int mid = (left + right) / 2;
		mergeSort(list, left, mid);
		mergeSort(list, mid + 1, right);
		merge(list, left, mid, right);
	}
}

void merge(Judge list[], int left, int mid, int right){
	int i, j, k, l;
	
	i = left;
	j = mid + 1;
	k = left;

	while (i <= mid && j <= right){
		if(list[i].n <= list[j].n){
			tem[k++] = list[i++];
		}
		else{
			tem[k++] = list[j++];
		}
	}
	if (i > mid){
		for(l = j; l <= right; l++){
			tem[k++] = list[l];
		}
	}
	else{
		for(l = i; l <= mid; l++){
			tem[k++] = list[l];
		}
	}
	for(l = left; l <= right; l++){
		list[l] = tem[l];
	}
}

int main(void){
	
	int n, i;
	scanf("%d\n", &n);
	Judge list[n];
	for(i = 0; i < n; i++){
		scanf("%d %s", &list[i].n, list[i].name);
	}
	mergeSort(list, 0, n-1);
	
	for(i = 0; i < n; i++){
		printf("%d %s\n", list[i].n, list[i].name);
	}
	return 0;
}
