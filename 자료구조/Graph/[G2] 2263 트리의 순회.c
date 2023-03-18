#include <stdio.h>
#include <stdlib.h>

int n;
int inorder[100000];
int postorder[100000];

int find_index(int s_index, int key);
void preorder(int in_s, int in_e, int post_s, int post_e);

int main(void) {
	scanf("%d", &n);
	for(int i = 0; i < n; i++) {
		scanf("%d", &inorder[i]);
	}
	for(int i = 0; i < n; i++) {
		scanf("%d", &postorder[i]);
	}
	preorder(0, n-1, 0, n-1);
	return 0;
}

int find_index(int s_index, int key) {
	while(inorder[s_index] != key) {
		s_index++;
	}
	return s_index;
}

void preorder(int in_s, int in_e, int post_s, int post_e) {
	if(post_s <= post_e) {
		int root = postorder[post_e];
		printf("%d ", root);
		int index = find_index(in_s, root);
		preorder(in_s, index - 1, post_s, post_s + index - in_s - 1);
		preorder(index + 1, in_e, post_e - in_e + index, post_e - 1);
	}
}
