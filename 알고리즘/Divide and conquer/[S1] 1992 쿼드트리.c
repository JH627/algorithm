#include <stdio.h>
#include <stdbool.h>

char dot[65][65];

void quadTree(int x, int y, int len);

int main(void) {
	int n;
	scanf("%d", &n);
	
	for(int i = 0; i < n; i++) {
		scanf("%s", &dot[i]);
	}
	
	quadTree(0, 0, n);
	return 0;
}

void quadTree(int x, int y, int len) {
	if(len == 1) {
		printf("%c", dot[x][y]);
		return;
	}
	char num = dot[x][y];
	bool same = true;
	
	for(int i = x; i < x + len; i++) {
		if(!same) {
			break;
		}
		for(int j = y; j < y + len; j++) {
			if(dot[i][j] != num) {
				int quadlen = len/2;
				printf("(");
				quadTree(x, y, quadlen);
				quadTree(x, y + quadlen, quadlen);
				quadTree(x + quadlen, y, quadlen);
				quadTree(x + quadlen, y + quadlen, quadlen);
				printf(")");
				same = false;
				break;
			}
		}
	}
	if(same) {
		printf("%c", num);
	}
	return;
}

