#include <iostream>
#include <queue>
#include <vector>

#define MAX 100001

using namespace std;

vector<long long> v;
queue<long long> q;
int type[MAX];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	int n, m;
	long long k, tmp;
	
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> type[i];
	}
	for (int i = 0; i < n; i++) {
		cin >> k;
		if (type[i] == 0) {
			v.push_back(k);
		}
	}
	
	int size = v.size();
	for (int i = size - 1; i >= 0; i--) {
		q.push(v[i]);
	}
	
	cin >> m;
	for (int i = 0; i < m; i++) {
		cin >> k;
		q.push(k);
		cout << q.front();
		if (i != (m - 1)) {
			cout << " ";
		}
		q.pop();
	}
	
	return 0;
}
