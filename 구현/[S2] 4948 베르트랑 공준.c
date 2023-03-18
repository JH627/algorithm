#include <stdio.h>

int main(void){
	int num[246913] = {1,1,0,};
    for(int i = 2; i <= 246913; i++){
        for(int j = 2; i*j <= 246913; j++){
            num[i*j] = 1;
        }
    }
    int n, i, count;
    do{
    	count = 0;
    	scanf("%d", &n);
    	if(n){
    		for(i = n+1; i <= 2*n; i++){
    			if(!num[i]){
    				count++;
				}
			}
			printf("%d\n", count);
		}
	} while(n);
    return 0;
}
