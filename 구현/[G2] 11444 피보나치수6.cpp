#include <iostream>
#include <string>
#include <vector>

using namespace std;

typedef unsigned long long type;

vector<vector<type>> unitarr = { {1,1},{1,0} };

#define MOD 1000000007

vector<vector<type>> arrMult(vector<vector<type>> a, vector<vector<type>> b) {

	vector<vector<type>> arr(2,vector<type>(2,0));

	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < 2; j++) {
			type result = 0;
			for (int k = 0; k < 2; k++) {
				result += (a[i][k] * b[k][j]) % MOD;
			}
			arr[i][j] = result % MOD;
		}
	}

	return arr;
}

vector<vector<type>> fibo(type n) {
	
	if (n == 1)
		return unitarr;
	
	vector<vector<type>> temp = fibo(n / 2);

	if (n % 2 == 0) {
		return arrMult(temp, temp);
	}
	else {
		return arrMult(arrMult(temp, temp), unitarr);
	}

}

int main(void) {

	type n;

	cin >> n;

	if (n == 0) {
		cout << "0";
		return 0;
	}

	vector<vector<type>> ans = fibo(n);

	cout << ans[1][0];
}
