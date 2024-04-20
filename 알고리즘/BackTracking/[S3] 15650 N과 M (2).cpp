#define _CRT_SECURE_NO_WARNINGS

#include <cstdio>
#include <vector>

using namespace std;

int n, m;
int a[10], vis[10];
vector<int> v;

void go(int here, int cnt) {
    if (cnt == m) {
        for (int i = 0; i < v.size(); i++) {
            printf("%d ", v[i]);
        }
        printf("\n");
        return;
    }

    for (int i = here; i <= n; i++) {
        if (vis[i] == 0) {
            vis[i] = 1;
            v.push_back(i);
            go(i + 1, cnt + 1);
            vis[i] = 0;
            v.pop_back();
        }
    }
}

int main(void){
    scanf("%d %d", &n, &m);

    for(int i=1; i<=n; i++) {
        a[i] = i;
    }
    
    go(1,0);
    return 0;
}