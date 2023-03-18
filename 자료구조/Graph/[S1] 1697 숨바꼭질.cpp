#define _CRT_SECURE_NO_WARNINGS
#include <cstdio>
#include <queue>

using namespace std;

queue<int> q;
int visited[100001] = { 0 };

int main(void) {
    int n, k;

    scanf("%d %d", &n, &k);
    q.push(n);
    while (!q.empty()) {
        int front = q.front();
        visited[front]++;
        if (front == k)
            break;
        q.pop();
        if (front - 1 >= 0 && !visited[front - 1]) {
            visited[front - 1] = visited[front];
            q.push(front - 1);
        }
        if (front + 1 < 100001 && !visited[front + 1]) {
            visited[front + 1] = visited[front];
            q.push(front + 1);
        }
        if (front * 2 < 100001 && !visited[front * 2]) {
            visited[front * 2] = visited[front];
            q.push(front * 2);
        }
    }
    printf("%d", visited[k] - 1);
}