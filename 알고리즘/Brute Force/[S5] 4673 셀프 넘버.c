#include <stdio.h>
#define NUM 10001

int selfnum(int n){
	int ans = n;
	while (n){
		ans += n%10;
		n /= 10;
	}
	return ans;
}

int main(void){
	
	int num[NUM] = {0,};
	int tem;
	for(int i = 1; i < NUM; i++){
		tem = selfnum(i);
		if(tem < NUM){
			num[tem] = 1;
		}
	}
	
	for(int i = 1; i < NUM; i++){
		if(num[i] == 0){
			printf("%d\n", i);
		}
	}
	return 0;
}
