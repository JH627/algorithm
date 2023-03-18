#include <stdio.h>

int main(void){
    int n, count = 0;
    int x[50], y[50];
    scanf("%d", &n);
	for(int i = 0; i < n; i++){
		scanf("%d %d", &x[i], &y[i]);
	}
	for(int i = 0; i < n; i++){
		count = 1;
		for(int j = 0; j < n; j++){
			if((x[i] < x[j]) && (y[i] < y[j])){
				count++;
			}
		}
		printf("%d ", count);
	}
	return 0;
}
