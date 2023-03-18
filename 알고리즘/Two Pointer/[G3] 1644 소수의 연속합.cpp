#define _CRT_SECURE_NO_WARNINGS
#define NUM 4000001

#include <cstdio>
#include <vector>

using namespace std;

int prime[NUM]{};

vector<int> v;

int main(void) {

	for (int i = 2; i < NUM; i++) {
		if (prime[i] != 1) {
			v.push_back(i);
			for (int j = 2; i * j < NUM; j++) {
				prime[i * j] = 1;
			}
		}
	}

	int n;
	scanf("%d", &n);

	int l = 0;	int r = 0;
	int size = v.size() - 1;
	int sum = v[0];	int count = 0;
	while (l <= size && r <= size) {
		if (sum > n) {sum -= v[l++];}
		else {
			if (sum == n) {count++;}
			sum += v[++r];
		}
	}

	printf("%d", count);
	return 0;
}