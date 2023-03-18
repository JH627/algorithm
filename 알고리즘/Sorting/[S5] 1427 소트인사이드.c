#include <stdio.h>
#include <string.h>

void swap(char* a, char* b);

int partition(char num[], int left, int right){
	
	int pivot = num[left];	
	int low = left;
	int high = right + 1;
	
	do{
		do{
			low++;
		} while (low <= right && num[low] > pivot);
		do {
			high--;
		} while (left <= high && num[high] < pivot);
		if (low < high){
			swap(&num[low], &num[high]);
		}
	} while (low < high);
	
	swap(&num[left], &num[high]);
		
	return high;
}

void myqsort(char num[], int left, int right){
	if (left < right){
		int q = partition(num, left, right);
		myqsort(num, left, q - 1);
		myqsort(num, q + 1, right);
	}
}

void swap(char* a, char* b){
	char tem = *a;
	*a = *b;
	*b = tem;	
}

int main(void){
	
	char num[11];
	scanf("%s", &num);
	
	myqsort(num, 0, strlen(num) - 1);
	
	printf("%s", num);
	
	return 0;
}
