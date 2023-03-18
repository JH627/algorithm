#include <stdio.h>

int n;
int arr[100001];

int min(int a, int b);
int max(int a, int b);
int his(int l, int r);

int main(void) {
	scanf("%d", &n);
	for(int i = 1; i <=	 n; i++) {
		scanf("%d", &arr[i]);
	}

	printf("%d", his(1, n));
	return 0;
}

int his(int l, int r) {
	if(l == r){
		return arr[l];
	}
	int mid = (l + r) / 2;
	int temp = max(his(l, mid), his(mid + 1, r));
	int fl = mid;
	int fr = mid + 1;
	int fh = min(arr[fl], arr[fr]);
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

int min(int a, int b) {
	return (a > b) ? b : a;
}

int max(int a, int b) {
	return (a > b) ? a : b;
}
