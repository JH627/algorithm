#include <stdio.h>

int n;
long long arr[100001];

long long min(long long a, long long b);
long long max(long long a, long long b);
long long his(int l, int r);

int main(void) {
	while(1) {
		scanf("%d", &n);
		if(!n) {
			break;
		}
		else {
			for(int i = 1; i <=	n; i++) {
				scanf("%lld", &arr[i]);
			}
			printf("%lld\n", his(1, n));
		}
	}
	return 0;
}

long long his(int l, int r) {
	if(l == r){
		return arr[l];
	}
	int mid = (l + r) / 2;
	long long temp = max(his(l, mid), his(mid + 1, r));
	int fl = mid;
	int fr = mid + 1;
	long long fh = min(arr[fl], arr[fr]);
	temp = max(temp, 2 * fh);
	
	while (l < fl || fr < r) {
		if (fr < r && (arr[fl - 1] < arr[fr + 1] || fl == l)) {
			fh = min(fh, arr[++fr]);
		}
		else {
			fh = min(fh, arr[--fl]);
		}
		temp = max(temp, (fr - fl+1)*fh);
	}
	return temp;
}

long long min(long long a, long long b) {
	return (a > b) ? b : a;
}

long long max(long long a, long long b) {
	return (a > b) ? a : b;
}
