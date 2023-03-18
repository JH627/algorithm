#include <stdio.h>

int main(void){

	int m, n;
	int min = 0;
	int sum = 0;
	int tem = 0;
	scanf("%d %d", &m, &n);
	
	for(; m <= n; m++, tem = 0){
		for(int i = m; i > 0; i--){
			if(m % i == 0){
				tem++;
			}
			if (tem == 3){
				break;
			}
		}
		if(tem == 2){
			sum += m;
				if(!min){
				min = m;
			}
		}
	}
	if (!min){
		printf("-1\n");
	}
	else{
		printf("%d\n%d", sum, min);
	}
	return 0;
}
