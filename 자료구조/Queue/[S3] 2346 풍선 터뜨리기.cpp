#include <iostream>
#include <deque>
#include <cstdlib>

using namespace std;

deque<pair<int, int>> q;

int main(void) {
	int n, k;
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		cin >> k;
		q.push_back(make_pair(k, i+1));
	}
	
	pair<int, int> now;
	int move;
	for (int i = 0; i < n; i++) {
		now = q.front();
		q.pop_front();
		cout << now.second << " ";
		move = (now.first > 0) ? now.first - 1 : -now.first;
		for (int j = 0; j < move; j++) {
			if (now.first > 0) {
				q.push_back(q.front());
				q.pop_front();
			}
			else {
				q.push_front(q.back());
				q.pop_back();
			}
		}
	}
		
	return 0;
}
