#include <stdio.h>

int main(void){
	
	int n;
	scanf("%d", &n);
	long long distance[n-1], cost, ans = 0;
	long long min = 1000000001;
	for(int i = 0; i < n-1; i++){
		scanf("%lld", &distance[i]);
	}
	for(int i = 0; i < n-1; i++){
		scanf("%lld", &cost);
		if (cost < min){
			min = cost;
		}
		ans += min * distance[i];
	}
	printf("%lld\n", ans);
	return 0;
}
