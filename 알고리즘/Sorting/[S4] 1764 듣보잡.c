#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int n, m, d = 0;
typedef struct data{
	char name[21];
}data;

data same[500001];

void merge(data mem[], int a, int mid, int b) {
	int i = a, j = mid + 1, k = a;
	data* tmp = (data*)malloc(sizeof(data) * 500001);
	while (i <= mid && j <= b) {
		if (strcmp(mem[i].name, mem[j].name) <= 0) {
			tmp[k++] = mem[i];
			if (strcmp(mem[i].name, mem[j].name) == 0) {
				same[d++] = mem[i];
			}
			i++;
		}
		else {
			tmp[k++] = mem[j++];
		}
	}
	if (i > mid) {
		for (int t = j; t <= b; t++) {
			tmp[k++] = mem[t];
		}
	}
	else {
		for (int t = i; t <= mid; t++) {
			tmp[k++] = mem[t];
		}
	}
	for (int t = a; t <= b; t++) {
		mem[t] = tmp[t];
	}
	free(tmp);
}
void mergeSort(data mem[], int a, int b) {
	if (a < b) {
		int mid = (a + b) / 2;
		mergeSort(mem, a, mid);
		mergeSort(mem, mid + 1, b);
		merge(mem, a, mid, b);
	}
}
int main(void)
{
	scanf("%d %d", &n, &m);
	data* mem = (data*)malloc(sizeof(data) * (n + m));
	for (int i = 0; i < n + m; i++) {
		scanf("%s", mem[i].name);
	}
	mergeSort(mem, 0, n + m - 1);
	
	printf("%d\n",d);
	for (int i = 0; i < d; i++) {
		printf("%s\n", same[i].name);
	}
	
	free(mem);
	return 0;
}
