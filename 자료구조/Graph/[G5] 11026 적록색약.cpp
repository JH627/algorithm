#include <iostream>
#include <vector>
#include <queue>

#define MAX 101

using namespace std;

int n;
char color[MAX][MAX];
int visit[MAX * MAX];

int add_x[4] = {0, 0, -1, 1};
int add_y[4] = {1, -1, 0, 0};

int bfs(vector<int>* v){
	for (int i = 0; i < MAX * MAX; i++) {
		visit[i] = 0;
	}
	int now, next;
	int area = 0;
	queue<int> q;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			now = i * n + j;
			if (!visit[now]) {
				area++;
				visit[now] = 1;
				q.push(now);
				while(!q.empty()) {
					now = q.front();
					q.pop();
					for (int k = 0; k < v[now].size(); k++) {
						next = v[now][k];
						if (!visit[next]) {
							visit[next] = 1;
							q.push(next);
						}
					}
				}
			}
		}
	}
	return area;
}

int main(void) {
	vector<int> v1[MAX * MAX];
	vector<int> v2[MAX * MAX];
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> color[i][j];
		}
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int now = i * n + j;
			for (int k = 0; k < 4; k++) {
				int next_x = i + add_x[k];
				int next_y = j + add_y[k];
				if (next_x < 0 || next_y < 0 || next_x >= n || next_y >= n) {
					continue;
				}
				if (color[i][j] == color[next_x][next_y]) {
					v1[now].push_back(next_x * n + next_y);
					v2[now].push_back(next_x * n + next_y);
				}
				else if ((color[i][j] == 'R' && color[next_x][next_y] == 'G') || (color[i][j] == 'G' && color[next_x][next_y] == 'R')) {
					v2[now].push_back(next_x * n + next_y);
				}
			} 
		}
	}
	
	int area1 = bfs(v1);
	int area2 = bfs(v2);
	
	cout << area1 << " " << area2;
	return 0;
}
