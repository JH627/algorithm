#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int **color;
int white = 0;
int blue = 0;

void paper(int x, int y, int len);

int main(void) {
	int n;
	scanf("%d", &n);
	
	color = (int**)malloc(sizeof(int*)*n);
	for(int i = 0; i < n; i++) {
		color[i] = (int*)malloc(sizeof(int)*n);
	}
	
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
			scanf("%d", &color[i][j]);
		}
	}

	paper(0, 0, n);
	printf("%d\n%d", white, blue);
	 
	for(int i = 0; i < n; i++) {
		free(color[i]);
	}
	free(color);
	return 0;
}

void paper(int x, int y, int len) {
	int temp = color[x][y];
	bool same = true;
	
	for(int i = x; i < x + len; i++) {
		if(!same) {
			break;
		}
		for(int j = y; j < y + len; j++) {
			if(color[i][j] != temp) {
				int quadlen = len/2;
				paper(x, y, quadlen);
				paper(x, y + quadlen, quadlen);
				paper(x + quadlen, y, quadlen);
				paper(x + quadlen, y + quadlen, quadlen);
				same = false;
				break;
			}
		}
	}
	if(same) {
		(temp == 1) ? blue++ : white++;
	}
	return;
}

