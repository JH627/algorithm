#define _CRT_SECURE_NO_WARNINGS
#define SIZE 101

#include <cstdio>
#include <vector>
#include <queue>

using namespace std;

vector<int> v[SIZE * SIZE * SIZE];
int tomato[SIZE][SIZE][SIZE];
int dist[SIZE * SIZE * SIZE];
queue<int> q;

int maxDist = -1;
int tomatospace = 0;
int blockedspace = 0;
int updatedspace = 0;

int add_x[6] = {1, -1, 0, 0, 0, 0};
int add_y[6] = {0, 0, 1, -1, 0, 0};
int add_z[6] = {0, 0, 0, 0, 1, -1};

int m, n, h;

void initDist() {
	for (int k = 0; k < h; k++) {
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				dist[(n * m) * k + n * j + i] = -1;
			}
		}
	}
}

int main(void) {
	scanf("%d %d %d", &n, &m, &h);

	initDist();

	for (int k = 0; k < h; k++) {
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				scanf("%d", &tomato[i][j][k]);
				if (tomato[i][j][k] == 1) {
					tomatospace++;
					q.push((n * m) * k + n * j + i);
					dist[(n * m) * k + n * j + i] = 0;
				}
				if (tomato[i][j][k] == -1) {
					blockedspace++;
				}
			}
		}
	}

	if (tomatospace + blockedspace == n * m * h) {
		printf("0");
		return 0;
	}

	int now, next;
	for (int k = 0; k < h; k++) {
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				if (tomato[i][j][k] == -1) {
					continue;
				}
				now = (n * m) * k + n * j + i;
				for (int t = 0; t < 6; t++) {
					int x = i + add_x[t];
					int y = j + add_y[t];
					int z = k + add_z[t];
					if (x >= n || x < 0 || y >= m || y < 0 || z >= h || z < 0) {
						continue;
					}
					if (tomato[x][y][z] == -1) {
						continue;
					}
					next = (n * m) * z + n * y + x;
					v[now].push_back(next);
				}
			}
		}
	}

	while (!q.empty()) {
		now = q.front();
		q.pop();
		for (int i = 0; i < v[now].size(); i++) {
			next = v[now][i];
			if (dist[next] == -1) {
				updatedspace++;
				dist[next] = dist[now] + 1;
				q.push(next);
				maxDist = (dist[next] > maxDist) ? dist[next] : maxDist;
			}
		}
	}
	
	printf("%d", (tomatospace + updatedspace + blockedspace != n * m * h) ? -1 : maxDist);
	return 0;
}