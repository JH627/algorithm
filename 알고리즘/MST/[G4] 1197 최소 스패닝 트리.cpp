#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int getParent(int set[], int x) {
	if (set[x] == x)
		return x;
	else
		return set[x] = getParent(set, set[x]);
}

void unionParent(int set[], int a, int b) {
	a = getParent(set, a);
	b = getParent(set, b);
	if (a < b)
		set[b] = a;
	else
		set[a] = b;
}

int find(int set[], int a, int b) {
	a = getParent(set, a);
	b = getParent(set, b);
	if (a == b)
		return 1;
	else
		return 0;
}

class Edge {
public :
	int node[2];
	int distance;
	Edge(int a, int b, int distance) {
		this->node[0] = a;
		this->node[1] = b;
		this->distance = distance;
	}
	bool operator <(Edge& edge) {
		return this->distance < edge.distance;
	}
};
int main(void) {
	vector<Edge> v;
	int n, m;
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		v.push_back(Edge(a, b, c));
	}

	sort(v.begin(), v.end());

	int* set = new int[n + 1];
	for (int i = 0; i < n; i++)
		set[i] = i;

	int sum = 0;
	for (int i = 0; i < v.size(); i++) {
		if (!find(set, v[i].node[0] - 1, v[i].node[1] - 1)) {
			sum += v[i].distance;
			unionParent(set, v[i].node[0] - 1, v[i].node[1] - 1);
		}
	}
	cout << sum;
    return 0;
}