#include <stdio.h>
#include <stdlib.h>

#define MAX_VERTEX 200

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

int check_union(int a, int b){
	return (find(a) == find(b));
}

int main(void){
	init();
	int n, m, e , i;
	scanf("%d %d", &n, &m);
	int *num = (int*)malloc(sizeof(int)*m);
	
	for(i = 0; i < n; i++){
		for(int j = 0; j < n; j++){
			scanf("%d", &e);
			if (e){
				set_union(i, j);
			}
		}
	}
	
	for(i = 0; i < m; i++){
		scanf("%d", &num[i]);
	}
	
	int k = 0;
	int visit = 1;
	while (k < m-1){
		if (check_union(num[k]-1, num[k+1]-1)){
			k++;
		}
		else{
			visit = 0;
			break;
		}
	}
	
	printf("%s", (visit) ? "YES" : "NO");
	free(num);
	return 0;
}
