#include <iostream>
#define MAX 101

using namespace std;

int a[MAX][MAX];
int b[MAX][MAX];
int ab[MAX][MAX];

int main(void) {
	int n, m, k;
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> a[i][j];
		}
	}
	cin >> m >> k;
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < k; j++) {
			cin >> b[i][j];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < k; j++) {
			for (int k = 0; k < m; k++) {
				ab[i][j] += a[i][k] * b[k][j];
			}
			cout << ab[i][j] << " ";
		}
		cout << "\n";
	}
	
	return 0;
}
