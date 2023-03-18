#include <stdio.h>

int main(void){

	int n, count = 0;
	int tem = 0;
	int num[100];
	scanf("%d", &n);
	
	for (int i = 0; i < n; i++, tem = 0){
		scanf("%d", &num[i]);
		for (int j = num[i]; j > 0; j--){
			if (num[i] % j == 0){
				tem++;
			}
		}
		if (tem == 2){
			count++;
		}
	}
	printf("%d", count);
	return 0;
}
