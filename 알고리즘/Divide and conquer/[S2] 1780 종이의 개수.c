#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int **num;
int neg = 0;
int zero = 0;
int pos = 0;

void paper(int x, int y, int len);

int main(void) {
	int n;
	scanf("%d", &n);
	
	num = (int**)malloc(sizeof(int*)*n);
	for(int i = 0; i < n; i++) {
		num[i] = (int*)malloc(sizeof(int)*n);
	}
	
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
			scanf("%d", &num[i][j]);
		}
	}

	paper(0, 0, n);
	printf("%d\n%d\n%d", neg, zero, pos);
	 
	for(int i = 0; i < n; i++) {
		free(num[i]);
	}
	free(num);
	return 0;
}

void paper(int x, int y, int len) {
	int temp = num[x][y];
	bool same = true;
	
	for(int i = x; i < x + len; i++) {
		if(!same) {
			break;
		}
		for(int j = y; j < y + len; j++) {
			if(num[i][j] != temp) {
				int temlen = len/3;
				paper(x, y, temlen);
				paper(x, y + temlen, temlen);
				paper(x, y + 2*temlen, temlen);
				paper(x + temlen, y, temlen);
				paper(x + temlen, y + temlen, temlen);
				paper(x + temlen, y + 2*temlen, temlen);
				paper(x + 2*temlen, y, temlen);
				paper(x + 2*temlen, y + temlen, temlen);
				paper(x + 2*temlen, y + 2*temlen, temlen);
				same = false;
				break;
			}
		}
	}
	if(same) {
		if(temp == 0) {
			zero++;
		}
		else{
			(temp == -1) ? neg++ : pos++;
		}
	}
	return;
}

