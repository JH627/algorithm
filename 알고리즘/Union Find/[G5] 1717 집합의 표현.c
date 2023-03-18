#include <stdio.h>

#define MAX_VERTEX 1000001

int parent[MAX_VERTEX];

void init(){
	for(int i = 0; i < MAX_VERTEX; i++){
		parent[i] = i;
	}
}

int find(int curr){
	if (curr == parent[curr]){
		return curr;		
	}
	else{
		return parent[curr] = find(parent[curr]);
	}
}

void set_union(int a, int b){
	a = find(a);
	b = find(b);
	if (a < b){parent[b] = a;}
	else {parent[a] = b;}
}

void check_union(int a, int b){
	a = find(a);
	b = find(b);
	printf("%s\n", (a == b) ? "YES" : "NO");	
}

int main(void){
	init();
	int n, m;
	int c, a, b;
	scanf("%d %d", &n, &m);
	for(int i = 0; i < m; i++){
		scanf("%d %d %d", &c, &a, &b);
		if (c == 0){
			set_union(a, b);
		}
		else {
			check_union(a, b);
		}
	}
	return 0;
}
