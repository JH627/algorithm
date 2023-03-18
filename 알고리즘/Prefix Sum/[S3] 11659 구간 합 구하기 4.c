#include <stdio.h>

int main(void){
	
	int arr[100001] = {0,};
	int n, m, m1, m2;
	int sum = 0;
	
	scanf("%d %d", &n, &m);
	for(int i = 1; i <= n; i++){
		scanf("%d", &m1);
		arr[i] = arr[i-1] + m1;
	}
	
	for(int i = 0; i < m; i++){
		scanf("%d %d", &m1, &m2);
		printf("%d\n", arr[m2] - arr[m1-1]);
	}
	return 0;
}
