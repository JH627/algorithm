#include <stdio.h>
#include <stdlib.h>

char dot[6200][3100];

void make_star(int x, int y, int len);

int main(void) {
	int n;
	scanf("%d", &n);
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < 2*n; j++) {
			dot[j][i] = ' ';
		}
	}
	
	make_star(0, 0, n);
	
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < 2*n; j++) {
			printf("%c", dot[j][i]);
		}
		printf("\n");
	}
	return 0;
}

void make_star(int x, int y, int len) {
	if(len == 3) {
		dot[x+2][y] = '*';
		dot[x+1][y+1] = '*';
		dot[x+3][y+1] = '*';
		dot[x][y+2] = '*';
		dot[x+1][y+2] = '*';
		dot[x+2][y+2] = '*';
		dot[x+3][y+2] = '*';
		dot[x+4][y+2] = '*';
	}
	else {
		len /= 2;
		make_star(x+len, y, len);
		make_star(x, y+len, len);
		make_star(x+2*len, y+len, len);
	}
}
