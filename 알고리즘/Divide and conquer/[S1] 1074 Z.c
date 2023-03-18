#include <stdio.h>
#include <math.h>

int count = 0;

void zsquad(int x, int y, int len);

int main(void) {
	int n, r, c;
	scanf("%d%d%d", &n, &r, &c);
	n = pow(2, n);
	zsquad(c, r, n);
	//2 1 4
	printf("%d", count);
	return 0;
}

void zsquad(int x, int y, int len) {
	if(len == 2) {
		if(x == 0) {
			if(y == 1) {
				count += 2;
			}
		}
		else {
			if(y == 0) {
				count ++;
			}
			else {
				count += 3;
			}
		}
	}
	else {
		len /= 2;
		if(x < len) {
			if(y < len) {
				zsquad(x, y, len);
			}
			else {
				count += pow(len,2)*2;
				zsquad(x, y%len, len);
			}
		}
		else {
			if(y < len) {
				count += pow(len,2)*1;				
				zsquad(x%len, y, len);
			}
			else {
				count += pow(len,2)*3;
				zsquad(x%len, y%len, len);
			}
		}
	}
	return;
}
