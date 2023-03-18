#include <stdio.h>
#define MAX 10001

int main(void){
    int t, n, x;
	int num[MAX] = {0,1,0,};
    scanf("%d", &t);
    for(int i = 2; i < MAX; i++){
        for(int j = 2; i*j < MAX; j++){
            num[i*j] = 1;
        }
    }
    
    for(int i = 0; i < t; i++){
    	scanf("%d", &n);
    	x = n/2;
		while(x > 1){
			if(!num[x]){
				if(!num[n-x]){
					printf("%d %d\n", x, n-x);
					break;
				}
			}
			x--;
	    }
	}
    return 0;
}
