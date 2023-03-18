#include <stdio.h>

long long n, b;
long long num[5][5];
long long tem[5][5];
long long ans[5][5];

void multi(long long m1[5][5], long long m2[5][5]);

int main(void) {
	scanf("%lld %lld", &n, &b);
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
			scanf("%lld", &num[i][j]);
		}
		ans[i][i] = 1;
	}
	
	while(b > 0) {
		if(b % 2 == 1){
			multi(ans, num);
			b--;
		}
		else{
			multi(num, num);
			b /= 2;
		}
	}
	
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < n; j++) {
			printf("%lld ", ans[i][j]);
		}
		printf("\n");
	}
	return 0;
}

void multi(long long m1[5][5], long long m2[5][5]) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			tem[i][j] = 0;
			for (int k = 0; k < n; k++) {
				tem[i][j] += (m1[i][k] * m2[k][j]);
			}
			tem[i][j] %= 1000;
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			m1[i][j] = tem[i][j];
		}
	}
}
