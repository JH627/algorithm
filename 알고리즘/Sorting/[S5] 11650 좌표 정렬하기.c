#include <stdio.h>

#define POINT_NUM 100000

typedef struct{
	int x, y;
} Point;

Point tem[POINT_NUM];

void merge(Point num[], int left, int mid, int right);

void mergeSort(Point num[], int left, int right){
	if (left < right){
		int mid = (left + right) / 2;
		mergeSort(num, left, mid);
		mergeSort(num, mid + 1, right);
		merge(num, left, mid, right);
	}
}

void merge(Point num[], int left, int mid, int right){
	int i, j, k, l;
	
	i = left;
	j = mid + 1;
	k = left;

	while (i <= mid && j <= right){
		if(num[i].x < num[j].x){
			tem[k++] = num[i++];
		}
		else if(num[i].x > num[j].x){
			tem[k++] = num[j++];
		}
		else{
			if(num[i].y < num[j].y){
				tem[k++] = num[i++];
			}
			else{
				tem[k++] = num[j++];
			}
		}
	}
	if (i > mid){
		for(l = j; l <= right; l++){
			tem[k++] = num[l];
		}
	}
	else{
		for(l = i; l <= mid; l++){
			tem[k++] = num[l];
		}
	}
	for(l = left; l <= right; l++){
		num[l] = tem[l];
	}
}

int main(void){
	
	int n, i;
	Point p1[POINT_NUM];
	scanf("%d", &n);
	for(i = 0; i < n; i++){
		scanf("%d %d", &p1[i].x, &p1[i].y);
	}
	mergeSort(p1, 0, n-1);
	
	for(i = 0; i < n; i++){
		printf("%d %d\n", p1[i].x, p1[i].y);
	}
	return 0;
}
