#include <stdio.h>
#include <stdlib.h>

int **star;

void make(int x, int y, int len);

int main(void) {
	int n;
	scanf("%d", &n);
	
	star = (int**)malloc(sizeof(int*)*n);
	for(int i = 0; i < n; i++) {
		star[i] = (int*)malloc(sizeof(int)*n);
	}
	
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
			 	star[i][j] = 1;
		}
	}
	
	make(0, 0, n);

	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
				printf("%c", (star[i][j]) ? '*' : ' ');
		}
		printf("\n");
	}
	
	for(int i = 0; i < n; i++) {
		free(star[i]);
	}
	free(star);
	return 0;
}

void make(int x, int y, int len) {
	if(len == 3){
		star[x+1][y+1] = 0;
		return;
	}
	for(int i = x + len/3; i < x + len/3*2; i++) {
		for(int j = y + len/3; j < y + len/3*2; j++) {
			star[i][j] = 0;
		} 
	}
	make(x, y, len/3);
	make(x + len/3, y, len/3);
	make(x + 2*len/3, y, len/3);
	
	make(x, y+len/3, len/3);
	make(x + 2*len/3, y+len/3, len/3);
		
	make(x ,y + 2*len/3,len/3);
	make(x + len/3, y + 2*len/3, len/3);
	make(x + 2*len/3 ,y + 2*len/3, len/3);
	return;
}
