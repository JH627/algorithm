#include <stdio.h>

int cal(int n){
	if (n < 100){return 1;}
	int a = n % 10;
	int b = n / 10 % 10;
	int c = n / 10 / 10 % 10;
	if((a-b) != (b-c) || n == 1000){
		return 0;
	}
	return 1;
}

int main(void){
	int n, count = 0;
	scanf("%d", &n);
	for(int i = 1; i <= n; i++){
		if(cal(i)){
			count++;
		}
	}
	printf("%d", count);
	return 0;
}
