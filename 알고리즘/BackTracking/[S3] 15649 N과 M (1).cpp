#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>

using namespace std;

vector<int> v;
int visit[10];

int n, m;

void dfs(int cnt) {
    if (cnt == m) {
        int size = v.size();
        for (int i = 0; i < size; i++) {
            printf("%d ", v[i]);
        }
        printf("\n");
        return;
    }

    for (int i = 1; i <= n; i++) {
        if (visit[i] == 0) {
            visit[i] = 1;
            v.push_back(i); 
            dfs(cnt + 1);
            v.pop_back();
            visit[i] = 0;
        }
    }
}

int main(void) {
    scanf("%d %d", &n, &m);

    dfs(0);

	return 0;
}