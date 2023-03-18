#include <stdio.h>
#include <stdlib.h>
#define HEAP_SIZE 100000 

typedef struct{
	int curidx;
	int data[HEAP_SIZE + 1];
} HeapType;

void insert_max_heap(HeapType* h, int k){
	int i = ++h->curidx;
	while ((i != 1) && k > h->data[i/2]){
		h->data[i] = h->data[i/2];
		i /= 2;
	}
	h->data[i] = k;
}

int delete_max_heap(HeapType* h){
	if (h->curidx == 0){
		return 0;
	}
	else{
		int item = h->data[1];
		int temp = h->data[h->curidx--];
		int parent = 1;
		int child = 2;
		while (child <= h->curidx){
			if ((child < h->curidx) && (h->data[child] < h->data[child+1])){
				child++;
			}
			if (temp >= h->data[child]){
				break;
			}
			h->data[parent] = h->data[child];
			parent = child;
			child *= 2;
		}
		h->data[parent] = temp;
		return item;
	}
}

int main(void){
	
	int n, k;
	scanf("%d", &n);
	HeapType* h = (HeapType*)malloc(sizeof(HeapType));
	h->curidx = 0;
	
	for (int i = 0; i < n; i++){
		scanf("%d", &k);
		if(k == 0){
			printf("%d\n", delete_max_heap(h));
		}
		else{
			insert_max_heap(h, k);
		}
	}
	free(h);	
	return 0;
}
