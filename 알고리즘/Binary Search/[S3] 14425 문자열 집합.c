#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define STR_LEN 501

int find(char (*text)[STR_LEN], char tem[STR_LEN], int n){
	int front = 0;
	int rear = n-1;
	int mid = (front + rear) / 2;
	int cmp;
	while (front <= rear){
		cmp = strcmp(text[mid], tem);
		if (cmp > 0){
			rear = mid - 1;
		}
		else if (cmp < 0){
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
	return strcmp((char*)a, (char*)b); 
}

int main(void){
	int n, m, i;
	int count = 0;
	scanf("%d %d", &n, &m);
	char text[n][STR_LEN];
	char tem[STR_LEN];
	for(i = 0; i < n; i++){
		scanf("%s", &text[i]);	
	}
	qsort(text, n, sizeof(char)*STR_LEN, mycompare);
	for(i = 0; i < m; i++){
		scanf("%s", &tem);
		count += find(text, tem, n);
	}
	printf("%d\n", count);
    return 0;
}
