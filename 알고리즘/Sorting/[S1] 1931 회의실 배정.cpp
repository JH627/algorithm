#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <vector>
#include <algorithm>
#include <stack>

using namespace std;

int main(void) {
	int n, s, e;
	scanf("%d", &n);

	vector<pair<int, int>> time;
	stack<pair<int, int>> table;

	for (int i = 0; i < n; i++) {
		scanf("%d %d", &s, &e);
		time.push_back(make_pair(s, e));
	}
	
	sort(time.begin(), time.end());

	table.push(time[0]);

	for (int i = 1; i < n; i++) {
		if (time[i].first >= table.top().second) {
			table.push(time[i]);
			continue;
		}

		if (time[i].second <= table.top().second) {
			table.pop();
			table.push(time[i]);
		}
	}

	printf("%d", table.size());

	return 0;
}
