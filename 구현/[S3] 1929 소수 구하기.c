#include <stdio.h>

int main(void){
	
    int m, n;
	int num[1000001] = {0,};
    num[1] = 1;
    scanf("%d %d", &m, &n);
    
    for(int i = 2; i <= n; i++){
        for(int j = 2; i*j <= n; j++){
            num[i*j] = 1;
        }
    }
    
    for(int i = m; i <= n; i++){
        if(num[i] == 0)
            printf("%d\n", i);
    }
    return 0;
}
